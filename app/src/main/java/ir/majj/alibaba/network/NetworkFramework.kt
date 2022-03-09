package ir.majj.alibaba.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkFramework {
    private val loggingInterceptor = HttpLoggingInterceptor { Timber.tag("OkHttp").d(it) }
        .apply { level = HttpLoggingInterceptor.Level.BODY }
    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    inline fun <reified S : Any> createService(): S = ApiProvider.retrofit.create(S::class.java)
}
