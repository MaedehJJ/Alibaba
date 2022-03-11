package ir.majj.alibaba.presentation.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ir.majj.alibaba.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    lateinit var viewModel: UserViewModel
    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, UserViewModelFactory())[UserViewModel::class.java]
        binding.usersList.adapter = adapter

        setUpData()
        viewModel.loadUsers()
    }

    private fun setUpData() {
        viewModel.users.observe(this) {
            adapter.addItems(it)
        }
    }
}
