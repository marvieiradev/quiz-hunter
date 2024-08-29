package com.mvieira.quizhunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
//import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity() {

    private var pontos: Int = 0
    private var certas: Int = 0
    private var rank: Int = 0
    private var aux: Int = 0
    //private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        /********* INICIO FACEBOOK ADS  *********/
        //TESTE
        //adView = AdView(this, "IMG_16_9_APP_INSTALL#393813996256947_393828486255498", AdSize.BANNER_HEIGHT_50)

        //REAL
        /*adView = AdView(this, "393813996256947_393828486255498", AdSize.BANNER_HEIGHT_50)

        val adContainer = findViewById<View>(R.id.banner_container) as LinearLayout
        adContainer.addView(adView)
        adView.loadAd()*/
        /********* FIM FACEBOOK ADS  *********/

        val dados: Bundle= intent.extras!!
        val intent = Intent(applicationContext, MainActivity::class.java)
        aux = dados.getInt("pontos")
        certas = dados.getInt("certas")
        rank = dados.getInt("rank")

        mostraResultado(rank)

        botao_voltar.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }

    private fun mostraResultado(int:Int) {
        if (int == 1){
            pontos = aux
            if (pontos >= 0 && pontos < 250) {
                txt_titulo.setText("${getString(R.string.novato)}")
                stars.setImageResource(R.drawable.star_low1)
            }else if (pontos >= 250 && pontos < 500){
                txt_titulo.setText("${getString(R.string.inexperiente)}")
                stars.setImageResource(R.drawable.star_low2)
            }else{
                txt_titulo.setText("${getString(R.string.experiente)}")
                stars.setImageResource(R.drawable.star_low3)
            }

        }else{
            pontos = aux*2
            if (pontos >= 0 && pontos < 300){
                txt_titulo.setText("${getString(R.string.novato)}")
                stars.setImageResource(R.drawable.star_high1)
            }else if (pontos >= 300 && pontos < 500){
                txt_titulo.setText("${getString(R.string.inexperiente)}")
                stars.setImageResource(R.drawable.star_high2)
            }else if (pontos >= 500 && pontos < 800){
                txt_titulo.setText("${getString(R.string.experiente)}")
                stars.setImageResource(R.drawable.star_high3)
            }else if (pontos >= 800 && pontos < 1040){
                txt_titulo.setText("${getString(R.string.veterano)}")
                stars.setImageResource(R.drawable.star_high4)
            }else{
                txt_titulo.setText("${getString(R.string.mestre)}")
                stars.setImageResource(R.drawable.star_high5)
            }
        }
        txt_pontos.setText("${getString(R.string.pontos)} $pontos")
        txt_certas.setText("${getString(R.string.certas)} $certas")
    }
}
