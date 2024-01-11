package com.echo.feature.boot.client;

import com.echo.feature.boot.anno.RetrofitClient;
import com.echo.feature.boot.entity.DTO.SzyhResultDTO;
import com.echo.feature.boot.entity.DTO.bill.SopAuthCallBackDTO;
import com.echo.feature.boot.entity.DTO.bill.SopAuthCallBackResDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Name: SzyhBillClient
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 11:12
 */
@RetrofitClient(baseUrl = "http://127.0.0.1:8003/")
public interface SzyhBillClient {

    @POST("inv-xx-ports/test/test")
    Call<String> test(@Body String body);


    @POST("inv-xx-ports/sop/callBack/auth")
    Call<SzyhResultDTO<SopAuthCallBackResDTO>> authCallBack(@Body SopAuthCallBackDTO sopAuthCallBackDTO);
}
