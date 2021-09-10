package com.example.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
//    var diceImage : ImageView? = null
    lateinit var diceImage : ImageView
    lateinit var diceImage2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice_image)
        diceImage2 = findViewById(R.id.dice_image2)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        val clearButton: Button = findViewById(R.id.clear_button)
        clearButton.setOnClickListener { clearDice() }
//        val countUpButton: Button = findViewById(R.id.count_up)
//        countUpButton.setOnClickListener { countUp() }
//        val resetButton: Button = findViewById(R.id.reset)
//        resetButton.setOnClickListener { reset() }
    }

    private fun clearDice() {
        diceImage.setImageResource(R.drawable.empty_dice)
        diceImage2.setImageResource((R.drawable.empty_dice))
    }
    private fun rollDice() {
        diceImage.setImageResource(getRandomDiceImage())
        diceImage2.setImageResource(getRandomDiceImage())
    }

    private fun getRandomDiceImage() : Int {
        val randomInt = (1..6).random()
        return when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

//    private fun rollDice() {
//        val randomInt = (1..6).random()
////        Toast.makeText(this, "button clicked",
////            Toast.LENGTH_SHORT).show()
////        val resultText: TextView = findViewById(R.id.result_text)
//////        resultText.text = "Dice Rolled!"
////        resultText.text = randomInt.toString()
//
////        val diceImage: ImageView = findViewById(R.id.dice_image)
//        val drawableResource = when (randomInt) {
//            1 -> R.drawable.dice_1
//            2 -> R.drawable.dice_2
//            3 -> R.drawable.dice_3
//            4 -> R.drawable.dice_4
//            5 -> R.drawable.dice_5
//            else -> R.drawable.dice_6
//        }
//        diceImage.setImageResource(drawableResource)
//    }

//    private fun countUp() {
//        val resultText: TextView = findViewById(R.id.result_text)
//        if (resultText.text == "Hello World!") {
//            resultText.text = "1"
//        } else if (resultText.text == "6") {
//            resultText.text = "6"
//        } else {
//            var originalInt = resultText.text.toString().toInt()
//            originalInt += 1
//            resultText.text = originalInt.toString()
//        }
//    }
//
//    private fun reset() {
//        val resultText: TextView = findViewById(R.id.result_text)
//        resultText.text = "0"
//    }
}