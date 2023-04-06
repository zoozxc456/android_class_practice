package com.example.classpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.classpractice.databinding.ActivityScreenLightBinding

class ScreenLight : AppCompatActivity() {
    private lateinit var binding: ActivityScreenLightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val seekBar = binding.seekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                setScreenLight(p1.toFloat() / 100)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        }
        )
    }

    private fun setScreenLight(lightValue: Float) {
        val lp = window.attributes
        lp.screenBrightness = lightValue
        window.attributes = lp
    }
}