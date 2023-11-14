package com.example.homework8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8.UserRecycleAdapter.Companion.EDIT_REQUEST_CODE
import com.example.homework8.databinding.ActivityMainBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserRecycleAdapter
    private val usersList = mutableListOf<User>(
        User("John", "Doe", "john_doe@gmail.com"),
        User("Jane", "Dane", "jane_dane@gmail.com"),
        User("Alice", "Smith", "alive_smith@gmail.com"),
        User("Jurika", "magarikaci", "jurika_chemi@gmail.com"),
        User("malxazi", "mdzgoli", "magari_kaci@gmail.com"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        createRecycleView(usersList)

        binding.addUserButton.setOnClickListener() {
            addNewUser(usersList)
        }
    }

    private fun createRecycleView(usersList: MutableList<User>) {
        userAdapter = UserRecycleAdapter(usersList)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }
    }

    private fun addNewUser(userList: MutableList<User>) {
        userList.add(
            User("", "", ""),
        )
        userAdapter.notifyItemInserted(userList.size - 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedUser = data?.getParcelableExtra<User>("updatedUser", User::class.java)
            val position: Int = data?.getIntExtra("position", -1) ?: -1

            if (updatedUser != null && position != -1) {
                usersList[position] = updatedUser
                userAdapter.notifyItemChanged(position)
            }
        }
    }
}