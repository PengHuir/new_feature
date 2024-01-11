package com.echo.feature.boot.controller;

import com.echo.feature.boot.entity.VO.UserVO;
import com.echo.feature.boot.service.IUnifiedService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 17:30
 */
@RestController
@RequestMapping("/unified")
public class UnifiedController {

    @Autowired
    private IUnifiedService unifiedService;

    /**
     * 描述信息：@PathVariable 和 @RequestParam 参数进行校验，如果校验失败，会抛出 MethodArgumentNotValidException 异常
     *
     * @param num 数据
     * @return 计算结果
     */
    @GetMapping("/{num}")
    public Integer detail(@PathVariable("num") @Min(1) @Max(20) Integer num) {
        return num * num;
    }

    /**
     * 描述信息：@PathVariable 和 @RequestParam 参数进行校验，如果校验失败，会抛出 MethodArgumentNotValidException 异常
     *
     * @param email 邮箱
     * @return 用户信息
     */
    @GetMapping("/getByEmail")
    public UserVO getByAccount(@RequestParam @NotBlank @Email String email) {
        UserVO testDTO = new UserVO();
        testDTO.setEmail(email);
        return testDTO;
    }

    /**
     * Post、Put 请求的参数推荐使用 @RequestBody 请求体参数
     * RequestResponseBodyMethodProcessor用于解析 @RequestBody 标注的参数、处理 @ResponseBody 标注方法的返回值
     * 解析 @RequestBoyd 标注参数的方法是 resolveArgument
     * org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#resolveArgument
     * org.springframework.web.servlet.mvc.method.annotation
     * .AbstractMessageConverterMethodArgumentResolver#validateIfApplicable
     * org.springframework.validation.annotation.ValidationAnnotationUtils#determineValidationHints
     * 如果注解中包含了@Valid、@Validated或者是名字以Valid开头的注解就进行参数校验
     * org.springframework.validation.DataBinder#validate
     * 实际校验逻辑，最终会调用Hibernate Validator执行真正的校验
     * 所以Spring Validation是对Hibernate Validation的二次封装
     *
     * @param userVO
     */
    @PostMapping("/save")
    public void save(@RequestBody @Validated UserVO userVO) {
        this.unifiedService.save(userVO);
    }
}
