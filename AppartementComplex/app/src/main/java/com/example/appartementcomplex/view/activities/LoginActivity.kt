package com.example.appartementcomplex.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.AppPreferenceHelper
import com.example.appartementcomplex.db.LocalDb
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var tvCreateAct: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //check is user loggedIn already
        if (AppPreferenceHelper(this).isLoggedIn) {
            navigateToFacilitiesActivity()
        } else {
            initView()
        }

    }

    private fun initView() {

        tvCreateAct = findViewById(R.id.tvNewAccount)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnLogin = findViewById(R.id.btnLogin)
        tvCreateAct.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val stringEmail = etEmail.text.toString()
            val stringPassword = etPassword.text.toString()
            val userDao = LocalDb.getInstance(this).userDao()
            if (stringEmail.isEmpty() && stringPassword.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity, "Please fill the all the details", Toast.LENGTH_SHORT
                ).show()

            } else {
                lifecycleScope.launch {
                    val user = userDao.getUserByName(stringEmail, stringPassword)
                    if (user != null) {
                        AppPreferenceHelper(this@LoginActivity).isLoggedIn = true
                        navigateToFacilitiesActivity()
                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }

        }
    }

    private fun navigateToFacilitiesActivity() {
        val intent = Intent(this@LoginActivity, FacilitiesActivity::class.java)
        startActivity(intent)
        finish()
    }
}