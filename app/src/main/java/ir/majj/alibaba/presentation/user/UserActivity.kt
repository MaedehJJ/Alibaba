package ir.majj.alibaba.presentation.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ir.majj.alibaba.databinding.ActivityUserBinding
import ir.majj.alibaba.intentFor
import ir.majj.alibaba.presentation.search.SearchActivity


class UserActivity : AppCompatActivity() {
    private var binding: ActivityUserBinding? = null
    private var viewModel: UserViewModel? = null
    private val adapter = UsersAdapter()

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            binding?.apply {
                val destination: String = descriptionField.text.toString()
                letsGo.isEnabled = destination.isNotEmpty()
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this, UserViewModelFactory())[UserViewModel::class.java]
        setUpDecorations()

        setUpData()
        viewModel?.loadUsers()
    }

    private fun setUpDecorations() {
        binding?.apply {
            val destination = intent.getStringExtra(EXTRA_DESTINATION)
            if (destination != null && destination.isNotEmpty()) {
                descriptionField.setText(destination)
            }
            usersList.adapter = adapter
            letsGo.setOnClickListener {
                val destinationText = descriptionField.text.toString()
                val intent = SearchActivity.getOpenIntent(this@UserActivity, destinationText)
                startActivity(intent)
            }
            descriptionField.addTextChangedListener(textWatcher)
        }
    }

    private fun setUpData() {
        viewModel?.users?.observe(this) {
            adapter.addItems(it)
        }
    }

    companion object {
        private const val EXTRA_DESTINATION = "UserActivity:Destination"

        fun getOpenIntent(context: Context, description: String): Intent {
            val intent = context.intentFor<UserActivity>()
            intent.putExtra(EXTRA_DESTINATION, description)
            return intent
        }
    }
}
