package com.echo.feature.boot.client;

import cn.hutool.json.JSONUtil;
import com.echo.feature.boot.BaseTest;
import com.echo.feature.boot.entity.DTO.SzyhResultDTO;
import com.echo.feature.boot.entity.DTO.bill.SopAuthCallBackDTO;
import com.echo.feature.boot.entity.DTO.bill.SopAuthCallBackResDTO;
import com.echo.feature.boot.utils.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
public class SzyhBillClientTest extends BaseTest {

    /*static SzyhBillClient service = null;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8003/")
                .addConverterFactory(ScalarsConverterFactory.create()) // 仅当您需要处理String响应时添加
                .addConverterFactory(GsonConverterFactory.create()) // 仅当您需要处理JSON响应时添加
                .build();

        service = retrofit.create(SzyhBillClient.class);
    }*/

    @Autowired
    private SzyhBillClient client;


    @Test
    public void testViewBillPageIds() throws Exception {
        String paramJson = "{\"serialNum\":\"Q647bafd9fa5e471cac01\",\"xsfnsrsbh\":\"500102201007206608\",\"companyName\":\"500102201007206608\",\"account\":\"500102201007206608\",\"name\":\"张东杰\",\"loginStatus\":\"00\",\"authMethod\":\"01\",\"operType\":\"02\",\"latestDate\":\"2024-01-10 14:06:21\",\"qrcodeContent\":\"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\",\"qrcodeGenTime\":\"2024-01-10 14:39:57\",\"expireTime\":\"2024-01-10 14:49:57\",\"createTime\":\"2024-01-10 14:39:57\",\"authType\":\"0\"}";

        AESUtil.AesSkParam aesSkParam = new AESUtil.AesSkParam();
        aesSkParam.setSecretKey("I66AndrK+Wrw/XjtBLaiZQ==");
        aesSkParam.setCipherAlgorithm("AES/ECB/PKCS5Padding");
        String encrypt = AESUtil.encrypt(paramJson, aesSkParam);
        log.info("加密后的内容: " + encrypt);

        Response<String> execute = client.test(encrypt).execute();
        encrypt = execute.body();


        String decrypt = AESUtil.decrypt(encrypt, aesSkParam);
        System.out.println(decrypt);

    }

    @Test
    public void testAuthCallBack() throws IOException {
        String paramJson = "{\"serialNum\":\"Q647bafd9fa5e471cac01\",\"xsfnsrsbh\":\"500102201007206608\",\"companyName\":\"500102201007206608\",\"account\":\"500102201007206608\",\"name\":\"张东杰\",\"loginStatus\":\"00\",\"authMethod\":\"01\",\"operType\":\"02\",\"desc\":\"测试\",\"qrcodeContent\":\"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\",\"authId\":\"4ec310e181d2\",\"qrcodeType\":\"01\",\"qrcodeGenTime\":\"2024-01-10 14:39:57\",\"createTime\":\"2024-01-10 14:39:57\",\"expireTime\":\"2024-01-10 14:49:57\",\"lastAuthSuccTime\":\"2024-01-10 14:29:57\",\"authType\":\"0\",\"qrCodeGenerationValid\":true}";
        SopAuthCallBackDTO param = JSONUtil.toBean(paramJson, SopAuthCallBackDTO.class);

        Response<SzyhResultDTO<SopAuthCallBackResDTO>> execute = client.authCallBack(param).execute();
        SzyhResultDTO<SopAuthCallBackResDTO> body = execute.body();
        System.out.println(body);
    }
}