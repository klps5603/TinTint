package com.bonge.tintint.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.bonge.tintint.pagingData.PagingFlow
import com.bonge.tintint.web.PhotosResponse
import kotlinx.coroutines.flow.Flow

data class PhotosState(
    val photosList: List<PhotosResponse> = listOf()
) : MvRxState

class PhotosViewModel(state: PhotosState) : BaseMvRxViewModel<PhotosState>(state, false) {
    fun getPagingData(): Flow<PagingData<PhotosResponse>> {
        return PagingFlow.getPagingData().cachedIn(viewModelScope)
    }

}