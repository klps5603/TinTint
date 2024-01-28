package com.bonge.tintint.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.BaseMvRxFragment
import com.bonge.tintint.R
import com.bonge.tintint.view.MainActivity
import com.bonge.tintint.view.dialog.CommonDialog
import com.bonge.tintint.view.dialog.LoadingDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseFragment<T : ViewBinding>(
    private val clazz: Class<T>,
    private val layoutResId: Int
) : BaseMvRxFragment() {

    var binding: T? = null
    private val loadingDialog by lazy {
        context?.let {
            LoadingDialog(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            val view = inflater.inflate(layoutResId, container, false)
            binding = bind(view)
        }
        return binding?.root
    }

    fun push(fragment: Fragment) {
        (activity as? MainActivity)?.push(fragment)
    }

    fun pop() {
        (activity as? MainActivity)?.pop()
    }

    fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.setPagingDataAdapterLoading() {
        lifecycleScope.launch {
            loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        loadingDialog?.show()
                    }
                    is LoadState.Error -> {
                        context?.let { context ->
                            CommonDialog(context)
                                .setCancelable(false)
                                .setMessage(getString(R.string.please_check_network))
                                .setPositiveButton(getString(R.string.retry_connect)) {
                                    refresh()
                                }
                                .show()
                        }
                    }
                    else -> {
                        loadingDialog?.dismiss()
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : ViewBinding> bind(view: View): T {
        val bindMethod = clazz.getDeclaredMethod("bind", View::class.java)
        return bindMethod.invoke(null, view) as T
    }

}