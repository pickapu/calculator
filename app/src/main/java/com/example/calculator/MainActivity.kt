package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var lastNumeric=false
    var lastDot=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onDigit(view: View){

            binding.tvInput.append((view as Button).text)
lastNumeric=true
        binding.tvInput.text.contains("1")

    }
    fun onClear(view: View){

        binding.tvInput.text=""
        lastNumeric=false
        lastDot=false


    }
    fun onDecimalPoint(view: View){
        if (lastNumeric&& !lastDot){
            binding.tvInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        if (lastNumeric&& !isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastDot=false
            lastNumeric=false
        }
    }
    fun onEqual(view: View){
           if(lastNumeric){
                   var tvValue=binding.tvInput.text.toString()
               var prefix=""

                   try{
                       if (tvValue.startsWith("-")){
                           prefix="-"
                           tvValue=tvValue.substring(1)
                       }
                     if (tvValue.contains("-")){
                          val splitValue=tvValue.split("-")
                         var one=splitValue[0]
                         var two=splitValue[1]
                         if(!prefix.isEmpty()){
                             one=prefix+one
                         }
                         binding.tvInput.text=(one.toDouble() - two.toDouble()).toString()
                     }else if (tvValue.contains("*")){
                         val splitValue=tvValue.split("*")
                         var one=splitValue[0]
                         var two=splitValue[1]
                         if(!prefix.isEmpty()){
                             one=prefix+one
                         }
                         binding.tvInput.text=(one.toDouble() * two.toDouble()).toString()
                     } else if (tvValue.contains("/")){
                         val splitValue=tvValue.split("/")
                         var one=splitValue[0]
                         var two=splitValue[1]
                         if(!prefix.isEmpty()){
                             one=prefix+one
                         }
                         binding.tvInput.text=(one.toDouble() / two.toDouble()).toString()
                     }else if (tvValue.contains("+")){
                         val splitValue=tvValue.split("+")
                         var one=splitValue[0]
                         var two=splitValue[1]
                         if(!prefix.isEmpty()){
                             one=prefix+one
                         }
                         binding.tvInput.text=(one.toDouble() + two.toDouble()).toString()
                     }



                   }catch (e:ArithmeticException){
                          e.printStackTrace()

                   }
           }

    }

   private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else {
            value.contains("/")||value.contains("*")||value.contains("-")||value.contains("+")

        }
    }

}