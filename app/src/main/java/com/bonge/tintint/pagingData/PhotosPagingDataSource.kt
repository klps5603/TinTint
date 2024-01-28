package com.bonge.tintint.pagingData

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bonge.tintint.utils.LogUtil
import com.bonge.tintint.web.Api
import com.bonge.tintint.web.PhotosResponse

class PhotosPagingDataSource(
    private val api: Api
) : PagingSource<Int, PhotosResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PhotosResponse>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosResponse> {
        return try {
            val albumId = params.key ?: 1

            val list = api.getPhotos(albumId)

            LogUtil.d("[API Request] photos/albumId=$albumId")
            LogUtil.d("[API Response] $list")

            val prefPageIndex = if (albumId > 1) albumId - 1 else null
            val nextPageIndex = if (list.isNotEmpty()) albumId + 1 else null
            LoadResult.Page(list, prefPageIndex, nextPageIndex)
        } catch (ex: Exception) {
            LogUtil.e(ex.message.toString(), ex)
            LoadResult.Error(ex)
        }
    }
}