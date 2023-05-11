package com.example.appartementcomplex.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.LocalDb
import com.example.appartementcomplex.db.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etRegEmail: EditText
    private lateinit var etRegPassword: EditText
    private lateinit var etRegcofPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnCancel: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUser = findViewById(R.id.etRegUserName)
        etRegEmail = findViewById(R.id.etRegEmail)
        etRegPassword = findViewById(R.id.etRegPassword)
        etRegcofPassword = findViewById(R.id.etRegConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnCancel = findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener {
           finish()
        }

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = etUser.text.toString()
        val email = etRegEmail.text.toString()
        val password = etRegPassword.text.toString()
        val confirmPassword = etRegcofPassword.text.toString()
        if (name.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {

            Toast.makeText(
                this@RegisterActivity, "Place fill all the details", Toast.LENGTH_LONG
            ).show()

        } else if (password != confirmPassword) {
            Toast.makeText(
                this@RegisterActivity,
                "Password not matched",
                Toast.LENGTH_LONG
            ).show()


        } else {
            LocalDb.getInstance(this).userDao()
                .insertUser(User(0, name, email, password, confirmPassword))
            Toast.makeText(this@RegisterActivity, "Registered", Toast.LENGTH_LONG).show()
            finish()

        }
    }
}