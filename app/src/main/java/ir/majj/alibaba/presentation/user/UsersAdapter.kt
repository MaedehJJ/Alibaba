package ir.majj.alibaba.presentation.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.majj.alibaba.R
import ir.majj.alibaba.databinding.UserListItemBinding
import ir.majj.alibaba.models.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private val users: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)

        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    fun addItems(users: List<User>) {
        this.users.addAll(users)
        notifyItemInserted(users.size)
    }

    inner class UsersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = UserListItemBinding.bind(itemView)
        fun bind(user: User) {
            binding.apply {
                fullName.text = itemView.context.getString(
                    R.string.user_fullName,
                    user.firstName,
                    user.lastName
                )
                email.text = user.email
                Glide.with(itemView.context).load(user.avatar).fitCenter().into(avatar)
            }
        }
    }
}
