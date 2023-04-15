package com.example.classpractice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.classpractice.databinding.ActivitySensorLightBinding

class SensorLightActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivitySensorLightBinding
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private lateinit var lightText: TextView
    private lateinit var lightProgressbar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensorLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lightText = binding.textView4
        lightProgressbar = binding.lightProgressBar

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,light,SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onSensorChanged(p0: SensorEvent) {
        val lightValue = p0.values[0]
        lightText.text = lightValue.toString()
        lightProgressbar.setProgress(lightValue.toInt(),true)
        setScreenLight(lightValue/255.0f)
    }

    override fun onAccuracyChanged(p0: Sensor, p1: Int) {
    }

    private fun setScreenLight(lightValue: Float) {
        val lp = window.attributes
        //screenBrightness的值範圍為0~1 單位為float
        lp.screenBrightness = java.lang.Float.valueOf(lightValue)
        window.attributes = lp
    }
}