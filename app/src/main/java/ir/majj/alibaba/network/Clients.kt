package ir.majj.alibaba.network

import ir.majj.alibaba.models.User

class UserClient(private val api: UserApi = NetworkFramework.createService()) {
    fun getUsers(success: (List<User>) -> Unit, failure: () -> Unit) {
        val callback = APICallback(success, failure)
        api.getUsers().enqueue(callback)
    }
}
