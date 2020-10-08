package com.pratclot.paginglib3demo.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.observable
import com.pratclot.paginglib3demo.repo.CatsPagingSource
import com.pratclot.paginglib3demo.service.CatApi

class MainViewModel @ViewModelInject constructor(
    private val catApi: CatApi
) : ViewModel() {

    val obs = Pager(
        PagingConfig(pageSize = 5)
    ) {
        CatsPagingSource(catApi, 5, "DESC")
    }.observable
}