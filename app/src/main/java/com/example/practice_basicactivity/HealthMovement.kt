package com.example.practice_basicactivity

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HealthMovement : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.health_movement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.act_healthToHome)
        }

        view.findViewById<Button>(R.id.waterEnter).setOnClickListener {
            val a = view.findViewById<TextView>(R.id.health_notes)
            val b = view.findViewById<TextView>(R.id.health_notes).text.toString() + "\n" + view.findViewById<TextView>(R.id.healthType).text + " goal: " + view.findViewById<TextView>(R.id.healthNum).text + " " + view.findViewById<TextView>(R.id.healthUnits).text
            a.setText(b)
            view.findViewById<TextView>(R.id.healthType).setText("")
            view.findViewById<TextView>(R.id.healthNum).setText("")
            view.findViewById<TextView>(R.id.healthUnits).setText("")
        }

    }
}
