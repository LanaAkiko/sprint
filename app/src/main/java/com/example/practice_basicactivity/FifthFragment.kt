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
class FifthFragment : Fragment() {

    /*var starttime = "000000000000000000000000000000000000000000000000"
    var mstart = starttime.substring(14, 16)
    var sstart = starttime.substring(17, 19)*/
    var timeSelected = 0

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

        view.findViewById<Button>(R.id.timer5).setOnClickListener {
            view.findViewById<TextView>(R.id.timer).text = "Time selected: 5 minutes"
            timeSelected = 5
        }

        view.findViewById<Button>(R.id.timer10).setOnClickListener {
            view.findViewById<TextView>(R.id.timer).text = "Time selected: 10 minutes"
            timeSelected = 10
        }

        view.findViewById<Button>(R.id.timer15).setOnClickListener {
            view.findViewById<TextView>(R.id.timer).text = "Time selected: 15 minutes"
            timeSelected = 15
        }

        view.findViewById<Button>(R.id.timer_start).setOnClickListener {
            //5 min
            var sec = (timeSelected * 60 * 1000).toLong()
            timer(sec)
        }

        view.findViewById<Button>(R.id.timer_pause).setOnClickListener {
        }

        view.findViewById<Button>(R.id.timer_reset).setOnClickListener {
            view.findViewById<TextView>(R.id.timer).text = "00:00"
        }
    }

    fun timer(time: Long){
        object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var secRemaining = millisUntilFinished / 1000
                var min = (secRemaining / 60).toString()
                var sec = (secRemaining - min.toInt() * 60).toString()
                if (min.toInt() < 10){
                    min = "0" + min.toString()
                }
                if (sec.toInt() < 10){
                    sec = "0" + sec.toString()
                }
                view?.findViewById<TextView>(R.id.timer)?.setText("Remaining time " + min + ":" + sec)
            }

            override fun onFinish() {
                view?.findViewById<TextView>(R.id.timer)?.setText("done!")
            }
        }.start()
    }

    /*fun timer(go: Boolean){
        view.findViewById<TextView>(R.id.timer).text = "111"
        var min = view?.findViewById<TextView>(R.id.timer)?.text?.substring(0, 2)?.toInt()
        var sec = view?.findViewById<TextView>(R.id.timer)?.text?.substring(3, 5)?.toInt()
        if (min != null && sec != null) {
            min ++
            sec ++
            view?.findViewById<TextView>(R.id.timer)?.text = min.toString() + ":" + sec.toString()
        }
    }
     */
}
