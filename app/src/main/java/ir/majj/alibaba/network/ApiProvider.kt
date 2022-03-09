package ir.majj.alibaba.network

import retrofit2.Retrofit

object ApiProvider {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .client(NetworkFramework.client)
            .addConverterFactory(Serializer.getConverterFactory())
            .build()
    }
}
