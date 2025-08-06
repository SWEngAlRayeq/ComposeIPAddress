package app.ip_address.di

import app.ip_address.data.remote.IpApi
import app.ip_address.data.repo_impl.IpRepositoryImpl
import app.ip_address.domain.repo.IpRepository
import app.ip_address.domain.usecase.GetIpInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://ipapi.co/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): IpApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpApi::class.java)

    @Provides
    @Singleton
    fun provideRepo(api: IpApi): IpRepository = IpRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUseCase(repo: IpRepository): GetIpInfoUseCase =
        GetIpInfoUseCase(repo)

}
