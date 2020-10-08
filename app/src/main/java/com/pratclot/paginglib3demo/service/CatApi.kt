package com.pratclot.paginglib3demo.service

import com.pratclot.paginglib3demo.data.CatImage
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("images/search")
    fun imagesSearch(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Single<Response<List<CatImage>>>
}