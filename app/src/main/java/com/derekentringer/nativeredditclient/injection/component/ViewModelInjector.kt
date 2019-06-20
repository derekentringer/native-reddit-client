package com.derekentringer.nativeredditclient.injection.component

import com.derekentringer.nativeredditclient.injection.module.NetworkModule
import com.derekentringer.nativeredditclient.ui.home.HomeViewModel
import com.derekentringer.nativeredditclient.ui.post.PostDetailsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(homeViewModel: HomeViewModel)
    fun inject(postDetailsViewModel: PostDetailsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}