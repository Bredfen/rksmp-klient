package com.example.test.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.RegisterRequest
import com.example.test.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextTextPassword_2)
        val registerButton = findViewById<Button>(R.id.button_register)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            val username = ""
            val numberphone = ""

            if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerRequest = RegisterRequest(email, password)

            RetrofitClient.instance.register(registerRequest).enqueue(object :
                Callback<Unit> {  // Используется Unit, так как ответ без данных
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegistrationActivity, "Регистрация успешна", Toast.LENGTH_LONG).show()

                        val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegistrationActivity, "Не удалось зарегистрироваться", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@RegistrationActivity, "Не удалось зарегистрироваться, ошибка сети: ${t.localizedMessage}", Toast.LENGTH_LONG).show();
                    Log.w("registration-failed","Не удалось зарегистрироваться, ошибка сети: ${t.localizedMessage}");
                }
            })
        }
    }
}
