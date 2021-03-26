package com.example.practice_basicactivity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class Timer : Fragment() {
    private var running = false
    private var minutes = 0
    private var seconds = 0
    private var timeSelected = 0
    private var secRemaining = 0.toLong()
    private lateinit var countdown: CountDownTimer

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.timer_start).setOnClickListener {

            if (seconds >= 60) {
                minutes += seconds / 60
                seconds %= 60
            }
            if (!running) {
                running = true
                timer(timeSelected.toLong() * 1000)
            }
            countdown.start()

        }

        view.findViewById<Button>(R.id.timer_pause).setOnClickListener {
            countdown.cancel()
            running = false
        }

        view.findViewById<Button>(R.id.timer_reset).setOnClickListener {
            countdown.cancel()
            timeSelected = 0
            view.findViewById<TextView>(R.id.timer)?.setText("00:00")
        }

        view.findViewById<Button>(R.id.timeEnter).setOnClickListener {
            var min = view.findViewById<TextView>(R.id.minInput).text.toString()
            var minInt = min.toInt()
            var sec = view.findViewById<TextView>(R.id.secInput).text.toString()
            var secInt = sec.toInt()

            if (min.toInt() < 10) {
                min = "0" + min.toString()
            }
            if (sec.toInt() < 10) {
                sec = "0" + sec.toString()
            }

            if(!running){
                view?.findViewById<TextView>(R.id.timer)?.setText(min + ":" + sec)
            }

            timeSelected = minInt * 60 + secInt
        }

    }

    fun timer(time: Long) {
        countdown = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secRemaining = millisUntilFinished / 1000
                var min = (secRemaining / 60).toString()
                var sec = (secRemaining - min.toInt() * 60).toString()
                if (min.toInt() < 10) {
                    min = "0" + min.toString()
                }
                if (sec.toInt() < 10) {
                    sec = "0" + sec.toString()
                }

                timeSelected = min.toInt() * 60 + sec.toInt()
                view?.findViewById<TextView>(R.id.timer)?.setText(min + ":" + sec)
            }

            override fun onFinish() {
                view?.findViewById<TextView>(R.id.timer)?.setText("done!")
                running = false
            }
        }
    }
}
