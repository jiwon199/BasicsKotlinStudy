package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }

    }

    private fun calculateTip() {
        //사용자가 입력한 값의 텍스트를 가져와서 스트링으로 변환
        val stringInTextField = binding.costOfService.text.toString()
        //숫자로 변환
        val cost = stringInTextField.toDoubleOrNull()
        //예외처리
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        //라디오버튼 뭐골랐는지 가져온다.
        //그리고 뭐골랐는지에 따라 tipPercentage 설정정
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        //지금까지 가져온 값들로 tip 계산
        var tip = tipPercentage * cost
        //반올림 체크했으면 올려주기
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        //달러표시 등 돈 형식에 맞게..
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}