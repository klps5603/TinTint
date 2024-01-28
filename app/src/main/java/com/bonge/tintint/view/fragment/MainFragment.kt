package com.bonge.tintint.view.fragment

import android.os.Bundle
import android.view.View
import com.bonge.tintint.R
import com.bonge.tintint.databinding.FragmentMainBinding

class MainFragment :
    BaseFragment<FragmentMainBinding>(FragmentMainBinding::class.java, R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.enterButton?.setOnClickListener {
            push(PhotosFragment())
        }
    }

    override fun invalidate() {

    }
}