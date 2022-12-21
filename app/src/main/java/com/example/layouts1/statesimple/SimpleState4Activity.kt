package com.example.layouts1.statesimple

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.layouts1.R
import com.example.layouts1.databinding.ActivityCounterBinding
import com.example.layouts1.statesimple.SimpleState4ViewModel.State

class SimpleState4Activity: AppCompatActivity() {

    lateinit var binding: ActivityCounterBinding

    private val viewModel by viewModels<SimpleState4ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { viewModel.increment() }
        binding.randomColorButton.setOnClickListener { viewModel.setRandomColor() }
        binding.switchVisibilityButton.setOnClickListener { viewModel.switchVisibility() }

        if (viewModel.state.value == null) {
            viewModel.initState(State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true
            ))
        }

        viewModel.state.observe(this, Observer {
            renderState(it)
        })

    }

    private fun renderState(state : State) = with(binding){
        counterTextView.setText(state.counterValue.toString())
        counterTextView.setTextColor(state.counterTextColor)
        counterTextView.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

}