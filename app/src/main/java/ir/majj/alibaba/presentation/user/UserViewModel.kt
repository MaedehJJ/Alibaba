package ir.majj.alibaba.presentation.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.majj.alibaba.models.User
import ir.majj.alibaba.network.UserRepository
import timber.log.Timber

class UserViewModel : ViewModel() {
    val users = MutableLiveData<List<User>>()

    fun loadUsers() {
        UserRepository().getUsers(
            { users.postValue(it.data) },
            { Timber.e("Failed to get users list") }
        )
    }
}
