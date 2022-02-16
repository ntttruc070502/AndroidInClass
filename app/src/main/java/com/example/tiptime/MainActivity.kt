package com.example.tiptime

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding// Sẽ được sử dụng trên nhiều phương thức trong main Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)//Khởi tạo đối tượng mà bạn sẽ truy cập vào view
        setContentView(binding.root)
        binding.caculate.setOnClickListener { calculateTip() }
    }
    fun calculateTip() {
        val stringInTextField=binding.CostOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost==null) {
            binding.tipResult.text=""
            return
        }
        val selectedId=binding.tipOption.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.radioButton4->0.20
            R.id.radioButton2->0.18
            else->0.15
        }
        var tip = tipPercentage*cost
        val roundUp=binding.tip.isChecked
        if(roundUp) {
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.app_name, formattedTip)
    }
}