package com.mvieira.quizhunter

import com.mvieira.quizhunter.R
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
//import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var rank : Int = 0
    //private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /********* INICIO FACEBOOK ADS  *********/
        //TESTE
        //adView = AdView(this, "IMG_16_9_APP_INSTALL#393813996256947_393828486255498", AdSize.BANNER_HEIGHT_50)

        //REAL
        /*adView = AdView(this, "393813996256947_393828486255498", AdSize.BANNER_HEIGHT_50)

        val adContainer = findViewById<View>(R.id.banner_container) as LinearLayout
        adContainer.addView(adView)
        adView.loadAd()*/
        /********* FIM FACEBOOK ADS  *********/

        img_cat.setBackgroundResource(R.drawable.img_main)

        val intent = Intent(applicationContext, QuizActivity::class.java)

        btn_low.setOnClickListener(){
            img_cat.setBackgroundResource(R.drawable.img_main_click)
            object : CountDownTimer(600, 600) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    rank = 1
                    intent.putExtra("rank",rank)
                    startActivity(intent)
                    finish()
                }
            }.start()
        }

        btn_high.setOnClickListener(){
            img_cat.setBackgroundResource(R.drawable.img_main_click)
            object : CountDownTimer(600, 600) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    rank = 2
                    intent.putExtra("RANK",rank)
                    startActivity(intent)
                    finish()
                }
            }.start()
        }

        btn_master.setOnClickListener(){
            img_cat.setBackgroundResource(R.drawable.img_main_click)
            object : CountDownTimer(600, 600) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    rank = 3
                    intent.putExtra("RANK",rank)
                    startActivity(intent)
                    finish()
                }
            }.start()
        }
    }
    override fun onDestroy() {
        /*if (adView != null) {
            adView.destroy()
        }*/
        super.onDestroy()
    }
}
