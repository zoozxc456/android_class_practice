package com.example.classpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.example.classpractice.databinding.ActivityScreenLightBinding

class ScreenLightActivity : AppCompatActivity() {
    private lateinit var screenLightPercentTextView: TextView
    private lateinit var screenLightOptionGroup: RadioGroup
    private lateinit var binding: ActivityScreenLightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLightBinding.inflate(layoutInflater)
        setContentView(binding.root)


        screenLightPercentTextView = binding.textView2
        val initScreenLight = android.provider.Settings.System.getInt(
            contentResolver,
            android.provider.Settings.System.SCREEN_BRIGHTNESS,
            255
        ).toFloat() / 255;

        setScreenLight(initScreenLight)
        setScreenLightText((getScreenLight() * 100).toInt())

        val seekBar = binding.seekBar
        seekBar.progress = (getScreenLight() * 100).toInt()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentScreenLightValue = p1.toFloat() / 100
                setScreenLight(currentScreenLightValue)

                val screenLightPercent = (getScreenLight() * 100).toInt()
                setScreenLightText(screenLightPercent)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })
        screenLightOptionGroup = binding.options
        screenLightOptionGroup.setOnCheckedChangeListener { _, i ->
            val screenLightValue = when (i) {
                binding.opt25.id -> .25f
                binding.opt50.id -> .50f
                binding.opt75.id -> .75f
                binding.opt100.id -> 1.0f
                else -> 1.0f
            }
            setScreenLight(screenLightValue)
            val screenLightPercent = (getScreenLight()*100).toInt()
            setScreenLightText(screenLightPercent)
            seekBar.setProgress(screenLightPercent,true)
        }
    }

    private fun setScreenLightText(screenLightPercent: Int) {
        screenLightPercentTextView.text = "$screenLightPercent %"
    }

    private fun setScreenLight(lightValue: Float) {
        val lp = window.attributes
        lp.screenBrightness = lightValue
        window.attributes = lp
    }

    private fun getScreenLight(): Float = window.attributes.screenBrightness
}