package com.mvieira.quizhunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(applicationContext, MainActivity::class.java)
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
