package com.shashank.gamify.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shashank.gamify.BuildConfig
import com.shashank.gamify.R
import com.shashank.gamify.databinding.FragmentSplashBinding
import com.shashank.gamify.ui.base.BaseFragment
import com.shashank.gamify.ui.viewmodels.SplashState
import com.shashank.gamify.ui.viewmodels.SplashViewModel
import com.shashank.gamify.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val mViewModel: SplashViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        mViewBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            tvAppVersion.text = getString(R.string.label_app_version, BuildConfig.VERSION_NAME)
        }
    }

    override fun initializeObserver() {
        mViewModel.splashStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is SplashState.SplashScreen -> {
                    navigate(SplashFragmentDirections.actionSplashFragmentToChatFragment())
                }
            }
        }
    }

}