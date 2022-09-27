package com.reyndev.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.reyndev.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.reyndev.tipcalculator.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculateTip.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringCost = binding.tfInput.text.toString()
        if (stringCost.isEmpty()) {
            binding.tfInput.error = "Ini tidak boleh kosong"
            return
        }

        val cost = stringCost.toDouble()

        val selectedTip = binding.rgTipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedTip) {
            R.id.rbTipOne -> 0.20
            R.id.rbTipTwo -> 0.18
            else -> 0.10
        }

        var tip = tipPercentage * cost
        val roundUp = binding.swRoundTip.isChecked
        if (roundUp) tip = kotlin.math.ceil(tip)
        val formatted = NumberFormat.getCurrencyInstance().format(tip)

        binding.tvTipAmount.text = "Tip amount: ${formatted}"
    }
}