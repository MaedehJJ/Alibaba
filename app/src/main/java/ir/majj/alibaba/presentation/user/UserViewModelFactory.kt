package ir.majj.alibaba.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
