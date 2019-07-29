package com.example.suma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.annotation.DrawableRes
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numeros
        tvuno.setOnClickListener { appendOnExpresstion("1", true) }
        tvdos.setOnClickListener { appendOnExpresstion("2", true) }
        tvtres.setOnClickListener { appendOnExpresstion("3", true) }
        tvcuatro.setOnClickListener { appendOnExpresstion("4", true) }
        tvcinco.setOnClickListener { appendOnExpresstion("5", true) }
        tvseis.setOnClickListener { appendOnExpresstion("6", true) }
        tvsiete.setOnClickListener { appendOnExpresstion("7", true) }
        tvocho.setOnClickListener { appendOnExpresstion("8", true) }
        tvnueve.setOnClickListener { appendOnExpresstion("9", true) }
        tvcero.setOnClickListener { appendOnExpresstion("0", true) }
        tvpunto.setOnClickListener { appendOnExpresstion(".", true) }

        //operadores
        tvmas.setOnClickListener{appendOnExpresstion("+", false)}
        tvmenos.setOnClickListener{appendOnExpresstion("-", false)}
        tvmultiplicacion.setOnClickListener{appendOnExpresstion("*", false)}
        tvdivision.setOnClickListener{appendOnExpresstion("/", false)}
        tvparentesisD.setOnClickListener{appendOnExpresstion(")", false)}
        tvparentesisI.setOnClickListener{appendOnExpresstion("(", false)}

        tvBorrar.setOnClickListener {
            tvExpr.text = ""
            tvResult.text = "  "
        }

        tvback.setOnClickListener {
            val string =  tvExpr.text.toString()
            if (string.isNotEmpty()){
                tvExpr.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }
        tvigual.setOnClickListener {
            try{
                val expression = ExpressionBuilder(tvExpr.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                }
                else{
                    tvResult.text = result.toString()
                }
            }
            catch(e:Exception){
                Log.d("Exception", "message: " + e.message)
            }
        }
    }

    fun appendOnExpresstion(string: String, canClear: Boolean){
        if(tvResult.text.isNotEmpty()){
            tvExpr.text = ""
        }
        if(canClear){
            tvResult.text = ""
            tvExpr.append(string)
        }
        else{
            tvExpr.append(tvResult.text)
            tvExpr.append(string)
            tvResult.text = ""
        }
    }
}
