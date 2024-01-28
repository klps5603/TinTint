package com.bonge.tintint.view

import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bonge.tintint.R
import com.bonge.tintint.databinding.ActivityMainBinding
import com.bonge.tintint.view.fragment.MainFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val fragmentList = mutableListOf<Fragment>()
    private var lastBackPressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.apply {
            supportActionBar?.hide()
            setSupportActionBar(toolBar)
            setContentView(root)
            replace(MainFragment())

            toolBar.setNavigationOnClickListener {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        if (fragmentList.size == 1) {
            if (System.currentTimeMillis() - lastBackPressTime > 2000) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.confirm_exit_app),
                    Toast.LENGTH_SHORT
                ).show()
                lastBackPressTime = System.currentTimeMillis()
            } else {
                exitProcess(0)
            }
        } else {
            pop()
        }
    }

    private fun replace(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.fragments.forEach {
            beginTransaction.remove(it)
        }
        fragmentList.clear()
        fragmentList.add(fragment)
        beginTransaction
            .add(R.id.frame_layout, fragment)
            .show(fragment)
            .commitAllowingStateLoss()
    }

    fun push(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.setCustomAnimations(
            R.anim.slide_right_in,
            R.anim.slide_left_out,
            R.anim.slide_left_in,
            R.anim.slide_right_out
        )
        if (fragmentList.size != 0) {
            beginTransaction.remove(fragmentList.last())
        }
        fragmentList.add(fragment)
        beginTransaction
            .add(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .show(fragment)
            .commitAllowingStateLoss()
        setToolbarNavigationIcon()
    }

    fun pop() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.setCustomAnimations(
            R.anim.slide_left_in,
            R.anim.slide_right_out,
            R.anim.slide_right_in,
            R.anim.slide_left_out
        )
        val fragment = fragmentList.removeAt(fragmentList.size - 1)
        beginTransaction.show(fragment).commitAllowingStateLoss()
        supportFragmentManager.popBackStackImmediate()
        setToolbarNavigationIcon()
    }

    private fun setToolbarNavigationIcon() {
        if (fragmentList.size > 1) {
            val typedValue = TypedValue().also {
                theme.resolveAttribute(
                    android.R.attr.homeAsUpIndicator,
                    it,
                    true
                )
            }
            binding.toolBar.navigationIcon = ContextCompat.getDrawable(this, typedValue.resourceId)
        } else {
            binding.toolBar.navigationIcon = null
        }
    }

    fun setTitle(title: String) {
        binding.toolBar.title = title
    }

}