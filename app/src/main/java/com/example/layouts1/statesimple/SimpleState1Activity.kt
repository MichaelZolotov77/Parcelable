package com.example.layouts1.statesimple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.layouts1.databinding.ActivityCounterBinding
import kotlin.random.Random

class SimpleState1Activity : AppCompatActivity() {

    lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { increment() }
        binding.randomColorButton.setOnClickListener { setRandomColor() }
        binding.switchVisibilityButton.setOnClickListener { switchVisibility() }
    }

    private fun increment() {
        var counter = binding.counterTextView.text.toString().toInt()
        counter++
        binding.counterTextView.setText(counter.toString())
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.counterTextView.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding.counterTextView){
        visibility = if (visibility == VISIBLE)
            INVISIBLE
        else
            VISIBLE
    }
}