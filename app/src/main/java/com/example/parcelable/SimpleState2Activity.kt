package com.example.parcelable

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.parcelable.databinding.ActivityCounterBinding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class SimpleState2Activity: AppCompatActivity() {

    lateinit var binding: ActivityCounterBinding

    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { increment() }
        binding.randomColorButton.setOnClickListener { setRandomColor() }
        binding.switchVisibilityButton.setOnClickListener { switchVisibility() }

        if (savedInstanceState == null) {
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        }
        else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterTextColor = savedInstanceState.getInt(KEY_COLOR)
            counterIsVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterTextColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterIsVisible)
    }

    private fun increment() {
        counterValue++
       renderState()
    }

    private fun setRandomColor() {
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        counterIsVisible = !counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding){
        counterTextView.setText(counterValue.toString())
        counterTextView.setTextColor(counterTextColor)
        counterTextView.visibility = if (counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        @JvmStatic private val KEY_COUNTER = "COUNTER"
        @JvmStatic private val KEY_COLOR = "COLOR"
        @JvmStatic private val KEY_IS_VISIBLE = "IS_VISIBLE"
    }























}