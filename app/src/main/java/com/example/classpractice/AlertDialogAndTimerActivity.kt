package com.example.classpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.classpractice.databinding.ActivityAlertDialogAndTimerBinding

class AlertDialogAndTimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogAndTimerBinding
    private lateinit var timerCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAlertDialogAndTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timerCount=binding.info

        val timer = object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                timerCount.setText("${p0/1000}")
            }

            override fun onFinish() {
                AlertDialog.Builder(this@AlertDialogAndTimerActivity)
                    .setMessage("倒數結束")
                    .setTitle("到數通知")
                    .setPositiveButton("ok",null)
                    .show()
            }

        }
        binding.startTimer.setOnClickListener { timer.start() }
    }
}