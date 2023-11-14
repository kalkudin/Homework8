package com.example.homework8

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.homework8.databinding.ActivityMainBinding
import com.example.homework8.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val userName = binding.updateFirstName
        val lastName = binding.updateLastName
        val email = binding.updateEmail
        val inputFieldList = listOf(userName, lastName, email)

        binding.saveButton.setOnClickListener {
            if(checkEmptyFields(inputFieldList)){
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!checkValidEmail(binding.updateEmail.text.toString())){
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val updatedUser = User(
                userName.text.toString(),
                lastName.text.toString(),
                email.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("updatedUser", updatedUser)
            val position = intent.getIntExtra("position", -1)
            if (position != -1) {
                resultIntent.putExtra("position", position)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun checkEmptyFields(inputList:List<EditText>):Boolean{
        return inputList.any{element -> element.text.isNullOrBlank()}
    }
    private fun checkValidEmail(email:String):Boolean{
        return email.trim().contains("@") && !email.trim().startsWith("@") && !email.trim().endsWith("@")
    }
}