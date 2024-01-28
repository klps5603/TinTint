package com.bonge.tintint.pagingData

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bonge.tintint.web.PhotosResponse
import com.bonge.tintint.web.Server
import kotlinx.coroutines.flow.Flow

object PagingFlow {

    private val api = Server.api

    fun getPagingData(): Flow<PagingData<PhotosResponse>> {
        return Pager(
            PagingConfig(25),
            pagingSourceFactory = {
                PhotosPagingDataSource(api)
            }
        ).flow
    }

}