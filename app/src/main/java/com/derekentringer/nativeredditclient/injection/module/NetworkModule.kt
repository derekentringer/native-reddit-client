package com.derekentringer.nativeredditclient.injection.module

import com.derekentringer.nativeredditclient.network.SubredditApi
import com.derekentringer.nativeredditclient.util.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideSubredditApi(retrofit: Retrofit): SubredditApi {
        return retrofit.create(SubredditApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitObject(): Retrofit {
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()
    }
}