package ir.majj.alibaba.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class APICallback<T>(
    private val success: (T) -> Unit,
    private val failure: () -> Unit
) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                success(body)
                return
            }
        }

        // Server side failure
        failure()
    }

    // Network failure
    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.d(t)
        failure()
    }
}
