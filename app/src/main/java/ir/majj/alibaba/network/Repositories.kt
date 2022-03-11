package ir.majj.alibaba.network

import ir.majj.alibaba.models.Page
import ir.majj.alibaba.models.User

class UserRepository {
    private val api: UserApi = NetworkFramework.createService()

    fun getUsers(success: (Page<User>) -> Unit, failure: () -> Unit) {
        val callback = APICallback(success, failure)
        api.getUsers().enqueue(callback)
    }
}
