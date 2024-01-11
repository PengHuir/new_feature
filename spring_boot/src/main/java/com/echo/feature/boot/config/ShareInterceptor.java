package com.echo.feature.boot.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * 分表拦截器
 *
 * @author ph.zhang
 * Created by on 2023/12/19 13:49
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "queryCursor", args = {MappedStatement.class, Object.class,
                RowBounds.class})
})
public class ShareInterceptor implements Interceptor {

    private static final String TABLE_NAME_PLACEHOLDER = ".*\\b(%s)\\b.*";

    private static Map<String, ShareTableConfig> shareTableConfigMap;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];

        Object parameter = args[1];
        String sql = mappedStatement.getSqlSource().getBoundSql(parameter).getSql();
        log.info("拦截到sql：{}", sql);

        // 判断执行SQL中是否存在需要分表名称
        ShareTableConfig shareConfig = getShareConfig(sql);
        if (Objects.isNull(shareConfig)) {
            return invocation.proceed();
        }

        // 判断参数是否为空，为空则全表扫描
        if (Objects.isNull(parameter)) {

        }


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 设置插件配置属性，如需要的话
    }

    /**
     * 通过SQL语句判断是否需要分表查询
     *
     * @param sql 执行SQL
     * @return 是否需要分表
     */
    private ShareTableConfig getShareConfig(String sql) {
        if (StrUtil.isBlank(sql) || MapUtil.isEmpty(ShareInterceptor.shareTableConfigMap)) {
            return null;
        }

        for (Map.Entry<String, ShareTableConfig> entry :
                ShareInterceptor.shareTableConfigMap.entrySet()) {
            String regex = String.format(TABLE_NAME_PLACEHOLDER, entry.getKey());
            boolean matches = sql.matches(regex);
            if (matches) {
                return entry.getValue();
            }
        }

        return null;
    }

    /**
     * 注入分表配置信心
     *
     * @param configs 分表配置
     */
    @Value("${share.table.configs:}")
    public void setShareTableConfigs(String configs) {
        if (StrUtil.isBlank(configs)) {
            return;
        }
        List<ShareTableConfig> shareTableConfigs = JSONUtil.toList(configs, ShareTableConfig.class);
        if (CollUtil.isEmpty(shareTableConfigs)) {
            return;
        }

        // 添加映射
        shareTableConfigs.stream().forEach(config -> {
            if (Objects.isNull(ShareInterceptor.shareTableConfigMap)) {
                ShareInterceptor.shareTableConfigMap = MapUtil.newHashMap();
            }
            ShareInterceptor.shareTableConfigMap.put(config.getShareTableName(), config);
        });
    }

    /**
     * 分表配置
     */
    @Data
    private static class ShareTableConfig implements Serializable {

        /**
         * 分表
         */
        private String shareTableName;

        /**
         * 分表key
         */
        private String shareTableKey = "id";

        /**
         * 分表策略
         */
        private String shareTaleStrategy = "hash";
    }

    public static void main(String[] args) {
        int a = 1;
        Integer b = 1;
        double c = 124.2343;
        Double d = 10.101;
        String e = "123";
        ShareTableConfig f = new ShareTableConfig();
        List<ShareTableConfig> g = new ArrayList<>();
        g.add(f);

        System.out.println(JSONUtil.toJsonStr(a));
        System.out.println(JSONUtil.toJsonStr(b));
        System.out.println(JSONUtil.toJsonStr(c));
        System.out.println(JSONUtil.toJsonStr(d));
        System.out.println(JSONUtil.toJsonStr(e));
        System.out.println(JSONUtil.toJsonStr(f));
        System.out.println(JSONUtil.toJsonStr(g));
    }
}