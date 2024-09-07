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
            pontos = aux
            if (pontos >= 0 && pontos < 150){
                txt_titulo.setText("${getString(R.string.novato)}")
                when(int){
                    1-> stars.setImageResource(R.drawable.star_low1)
                    2-> stars.setImageResource(R.drawable.star_high1)
                    else-> stars.setImageResource(R.drawable.star_mast1)
                }
            }else if (pontos >= 150 && pontos < 250){
                txt_titulo.setText("${getString(R.string.inexperiente)}")
                when(int){
                    1-> stars.setImageResource(R.drawable.star_low2)
                    2-> stars.setImageResource(R.drawable.star_high2)
                    else-> stars.setImageResource(R.drawable.star_mast2)
                }
            }else if (pontos >= 250 && pontos < 400){
                txt_titulo.setText("${getString(R.string.experiente)}")
                when(int){
                    1-> stars.setImageResource(R.drawable.star_low3)
                    2-> stars.setImageResource(R.drawable.star_high3)
                    else-> stars.setImageResource(R.drawable.star_mast3)
                }
            }else if (pontos >= 450 && pontos < 520){
                txt_titulo.setText("${getString(R.string.veterano)}")
                when(int){
                    1-> stars.setImageResource(R.drawable.star_low4)
                    2-> stars.setImageResource(R.drawable.star_high4)
                    else-> stars.setImageResource(R.drawable.star_mast4)
                }
            }else{
                txt_titulo.setText("${getString(R.string.mestre)}")
                when(int){
                    1-> stars.setImageResource(R.drawable.star_low5)
                    2-> stars.setImageResource(R.drawable.star_high5)
                    else-> stars.setImageResource(R.drawable.star_mast5)
                }
            }

        val total_pontos = when(int){
            1-> pontos
            2-> pontos*2
            else->pontos*3
        }
        txt_pontos.setText("${getString(R.string.pontos)} $total_pontos")
        txt_certas.setText("${getString(R.string.certas)} $certas")
    }
}
