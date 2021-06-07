package com.example.practice_basicactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.DateFormat
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Homepage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_toCalendar).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        view.findViewById<Button>(R.id.button_toHealth).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_ThirdFragment)
        }
        view.findViewById<Button>(R.id.button_toGrades).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_FourthFragment)
        }
        view.findViewById<Button>(R.id.button_toTimer).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_FifthFragment)
        }
        view.findViewById<Button>(R.id.button_toTodo).setOnClickListener {
            findNavController().navigate(R.id.act_homeToList)
        }

        val currentDateString = DateFormat.getDateInstance().format(Date())

        view?.findViewById<TextView>(R.id.textView6)?.setText(currentDateString)

    }


}