package com.example.pokeapp.di

import com.example.pokeapp.BuildConfig.BASE_URL
import com.example.pokeapp.BuildConfig.DEBUG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    private fun buildRetrofitClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)

        if (DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().build()
                chain.proceed(newRequest)
            }
        }
        return builder.build()
    }
}