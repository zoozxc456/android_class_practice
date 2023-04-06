package com.example.classpractice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.classpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var accountInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button
    private lateinit var screenLightBtn :Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountInput = binding.accInput
        passwordInput = binding.pwdInput
        loginBtn = binding.btnLogin
        screenLightBtn=binding.btnScreenLight

        loginBtn.setOnClickListener {
            val account = binding.accInput.text.toString()
            val password = binding.pwdInput.text.toString()

            val loginService = LoginService(this)
            loginService.login(account, password);
        }

        screenLightBtn.setOnClickListener{
            val intent = Intent(this,MainMenu::class.java)
            this.startActivity(intent)
        }
    }
}

class LoginService(context: Context) {
    private val isValidAccount: (String) -> Boolean = { account -> account == "123" }
    private val isValidPassword: (String) -> Boolean = { password -> password == "456" }
    private val _context: Context;

    init {
        _context = context
    }

    fun login(account: String, password: String) {
        if (isValidAccount(account) && isValidPassword(password)) {
            val intent = Intent(_context, MainMenu::class.java)
            intent.putExtra("username", "jrong")
            _context.startActivity(intent)
        } else {
            val errorMsg = "帳號或密碼輸入錯誤"
            val duration = Toast.LENGTH_LONG
            Toast.makeText(_context, errorMsg, duration).show()
        }
    }
}