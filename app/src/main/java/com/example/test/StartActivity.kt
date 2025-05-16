package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.ui.login.LoginActivity
import com.example.test.ui.register.RegistrationActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonLogin: Button = findViewById(R.id.auth_but)
        Log.d("MainActivity", "Авторизация кнопка нажата") // Логируем нажатие кнопки
        buttonLogin.setOnClickListener {
            val intent = Intent(this@StartActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val buttonRegistr: Button = findViewById(R.id.reg_but)
        Log.d("MainActivity", "Регистрация кнопка нажата") // Логируем нажатие кнопки
        buttonRegistr.setOnClickListener {
            val intent = Intent(this@StartActivity, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}