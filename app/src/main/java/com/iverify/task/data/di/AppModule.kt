package com.iverify.task.data.di

import android.content.Context
import com.iverify.task.data.remote.CheckNetworkInterceptor
import com.iverify.task.data.remote.TokenInterceptor
import com.iverify.task.data.util.tokenmanager.TokenManager
import com.iverify.task.data.util.tokenmanager.SecureTokenManager
import com.iverify.task.data.remote.device.DeviceApi
import com.iverify.task.data.repository.DeviceRepositoryImpl
import com.iverify.task.domain.repository.DeviceRepository
import com.iverify.task.domain.usecase.GetDevicesUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://hiring.iverify.io"

    @Singleton
    @Provides
    fun provideCheckNetworkInterceptor(@ApplicationContext context: Context) =
        CheckNetworkInterceptor(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        checkNetworkInterceptor: CheckNetworkInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(checkNetworkInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideDeviceApi(retrofit: Retrofit): DeviceApi =
        retrofit.create(DeviceApi::class.java)

    @Singleton
    @Provides
    fun provideAuthTokenProvider(
        @ApplicationContext appContext: Context
    ): TokenManager = SecureTokenManager(appContext)

    @Singleton
    @Provides
    fun provideDeviceRepository(
        api: DeviceApi,
    ): DeviceRepository = DeviceRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideGetDevicesUseCase(repository: DeviceRepository) = GetDevicesUseCase(repository)
}