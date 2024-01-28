package com.bonge.tintint.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.mvrx.fragmentViewModel
import com.bonge.tintint.R
import com.bonge.tintint.adapter.PhotosAdapter
import com.bonge.tintint.databinding.FragmentPhotosBinding
import com.bonge.tintint.utils.launch
import com.bonge.tintint.viewModel.PhotosViewModel
import kotlinx.coroutines.flow.collect

class PhotosFragment : BaseFragment<FragmentPhotosBinding>(
    FragmentPhotosBinding::class.java,
    R.layout.fragment_photos
) {

    private val viewModel: PhotosViewModel by fragmentViewModel()
    private val adapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.setPagingDataAdapterLoading()

        launch {
            viewModel.getPagingData().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.photosRecyclerView?.apply {
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            layoutManager = staggeredGridLayoutManager
            adapter = this@PhotosFragment.adapter
        }
    }

    override fun invalidate() {

    }

}