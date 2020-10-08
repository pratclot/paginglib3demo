package com.pratclot.paginglib3demo.repo

import androidx.paging.rxjava2.RxPagingSource
import com.pratclot.paginglib3demo.data.CatImage
import com.pratclot.paginglib3demo.service.CatApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CatsPagingSource(val catApi: CatApi, val limit: Int, val order: String) : RxPagingSource<Int, CatImage>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CatImage>> {
        val nextPageNumber = params.key ?: 1
        var body: List<CatImage>
        var headers: okhttp3.Headers
        return catApi.imagesSearch(limit, nextPageNumber, order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                body = it.body()!!
                headers = it.headers()
                LoadResult.Page(
                    data = body,
                    prevKey = null,
                    nextKey = headers.get("Pagination-Page")?.toInt()?.plus(1)
                )
            }
    }
}