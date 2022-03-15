package android.ahmed.khaled.data.di

import android.ahmed.khaled.data.BuildConfig
import android.ahmed.khaled.data.restful.MoviesEndPoints
import android.ahmed.khaled.data.restful.UrlConfig
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
object NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("api_key", UrlConfig.API_KEY)
                    .build()
                val newRequest = request.newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(UrlConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getMoviesEndPoints(retrofit: Retrofit): MoviesEndPoints {
        return retrofit.create(MoviesEndPoints::class.java)
    }
}