package com.mvieira.quizhunter

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(){
    private var posicaoAtual: Int = 1
    private var listaPerguntas: List<Pergunta>? = null
    private var respCertas: Int = 0
    private var posOpcaoSelecionada: Int = 0
    private var alerta: Dialog? = null
    private var pontosTempo: Int = 0
    private lateinit var timer: CountDownTimer
    private var tempo:Int = 20
    private var rank:Int = 0
    //private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

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
        rank = dados.getInt("rank")

        listaPerguntas = BancoPergunta.obterPerguntas(rank)

        timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempo--
                if (tempo < 0){tempo = 0}
            }
            override fun onFinish() {
                timer.cancel()
                pontosTempo += tempo
                tempo = 20
            }
        }.start()

        setarPergunta()

        var respSelec: Int = 0

        progressBar.max = 15

        botao_a.setOnClickListener {
            verOpcaoSelecionada(botao_a, 1)
            botao_ok.setVisibility(View.VISIBLE)
            respSelec = 1
        }
        botao_b.setOnClickListener {
            verOpcaoSelecionada(botao_b, 2)
            botao_ok.setVisibility(View.VISIBLE)
            respSelec = 2
        }
        botao_c.setOnClickListener {
            verOpcaoSelecionada(botao_c, 3)
            botao_ok.setVisibility(View.VISIBLE)
            respSelec = 3
        }
        botao_d.setOnClickListener {
            verOpcaoSelecionada(botao_d, 4)
            botao_ok.setVisibility(View.VISIBLE)
            respSelec = 4
        }
        botao_ok.setOnClickListener {
            verificaResposta(respSelec)
        }
    }

    private fun finalizaJogo(){
        val intent = Intent(this, ResultadoActivity::class.java)
        var pontosFinais = (respCertas*20)+pontosTempo
        if (pontosFinais < 0){pontosFinais = 0}
        intent.putExtra(BancoPergunta.PONTOS_TOTAIS, pontosFinais)
        intent.putExtra(BancoPergunta.RESPOSTAS_CERTAS, respCertas)
        intent.putExtra("rank", rank)
        startActivity(intent)
        finish()
    }

    private fun verificaResposta(opcao:Int){
        val pergunta = listaPerguntas?.get(posicaoAtual - 1)
        if (pergunta!!.resposta != opcao) {
            posicaoAtual++
            timer.cancel()
            mostraAlerta(0)
        }
        else{
            posicaoAtual++
            respCertas++
            timer.onFinish()
            mostraAlerta(1)
        }
    }

    private fun setarPergunta() {
        val pergunta = listaPerguntas!!.get(posicaoAtual - 1)
        limpaBotoes()

        ObjectAnimator.ofInt(progressBar,"progress",posicaoAtual).setDuration(1000).start()

        imagem.setImageResource(pergunta.imagem)
        botao_a.text = pergunta.opcA
        botao_b.text = pergunta.opcB
        botao_c.text = pergunta.opcC
        botao_d.text = pergunta.opcD

        timer.start()
    }

    private fun verOpcaoSelecionada(txt: TextView, opcaoSelecionada: Int) {
        limpaBotoes()
        posOpcaoSelecionada = opcaoSelecionada
        txt.setTextColor(Color.parseColor("#FFFFFF"))
        when (posOpcaoSelecionada) {
            1 -> botao_a.setBackgroundResource(R.drawable.botao_quiz_selec)
            2 -> botao_b.setBackgroundResource(R.drawable.botao_quiz_selec)
            3 -> botao_c.setBackgroundResource(R.drawable.botao_quiz_selec)
            4 -> botao_d.setBackgroundResource(R.drawable.botao_quiz_selec)
        }
    }

    private fun limpaBotoes() {
        val options = ArrayList<TextView>()
        options.add(0, botao_a)
        options.add(1, botao_b)
        options.add(2, botao_c)
        options.add(3, botao_d)
        botao_ok.setVisibility(View.INVISIBLE)

        for (option in options) {
            option.setBackgroundResource(R.drawable.botao_quiz)
            option.setTextColor(Color.parseColor("#000000"))
        }
    }

    private fun mostraAlerta(num: Int){
        alerta = Dialog(this,R.style.Tema_Alerta)
        alerta!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
		alerta!!.setCancelable(false)

		if(num==1){
			alerta!!.setContentView(R.layout.alerta_acerto)
		}
		else{
			alerta!!.setContentView(R.layout.alerta_erro)
		}
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (posicaoAtual <= 15) {
                    setarPergunta()
                } else {
                    finalizaJogo()
                }
                alerta!!.dismiss()
            }
        }.start()
		alerta!!.show()
    }
}

