package com.echo.feature.client;

import com.echo.feature.entity.DTO.SzyhResultDTO;
import com.echo.feature.entity.DTO.bill.ViewBillPageIdsDTO;
import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import okhttp3.OkHttpClient;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/27 10:21
 */
@RetrofitClient(baseUrl = "${retrofit.szyh.baseurl}/xxApi/api/v1/bill/")
public interface SzyhBillClient {

    // @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);

    }


    @GET("view/BillPageIds/{billId}")
    SzyhResultDTO<ViewBillPageIdsDTO> viewBillPageIds(@Path("billId") Long billId,
                                                      @Query("billPageFlag") Integer billPageFlag);
}
