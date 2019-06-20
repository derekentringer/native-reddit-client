package com.derekentringer.nativeredditclient.ui

import androidx.lifecycle.ViewModel
import com.derekentringer.nativeredditclient.injection.component.DaggerViewModelInjector
import com.derekentringer.nativeredditclient.injection.component.ViewModelInjector
import com.derekentringer.nativeredditclient.injection.module.NetworkModule
import com.derekentringer.nativeredditclient.ui.home.HomeViewModel
import com.derekentringer.nativeredditclient.ui.post.PostDetailsViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is HomeViewModel -> injector.inject(this)
            is PostDetailsViewModel -> injector.inject(this)
        }
    }
}