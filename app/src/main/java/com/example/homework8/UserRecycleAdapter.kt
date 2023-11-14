package com.example.homework8
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8.databinding.UserLayoutBinding

class UserRecycleAdapter(private val userList:MutableList<User>):RecyclerView.Adapter<UserRecycleAdapter.UserViewHolder>(){

    inner class UserViewHolder(val binding:UserLayoutBinding):RecyclerView.ViewHolder(binding.root){
        val tvFirstName = binding.firstName
        val tvLastName = binding.lastName
        val tvEmail = binding.email
        val updateButton = binding.updateUser
        val deleteButton = binding.deleteUser
    }

    companion object {
        const val EDIT_REQUEST_CODE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UserRecycleAdapter.UserViewHolder {
        return UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserRecycleAdapter.UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.tvFirstName.text = "First Name: ${currentUser.firstName}"
        holder.tvLastName.text = "Last Name: ${currentUser.lastName}"
        holder.tvEmail.text = "Email: ${currentUser.email}"

        holder.deleteButton.setOnClickListener(){
            userList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
        holder.updateButton.setOnClickListener(){
            val intent = Intent(holder.itemView.context, UserActivity::class.java)
            intent.putExtra("position", position)  // Pass the position to EditUserActivity
            (holder.itemView.context as Activity).startActivityForResult(intent, EDIT_REQUEST_CODE)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}