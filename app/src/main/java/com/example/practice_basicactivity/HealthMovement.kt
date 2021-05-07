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

        val goalList = arrayOfNulls<String>(21) //can only have 7 items

        view.findViewById<Button>(R.id.waterEnter).setOnClickListener {
            val type = view.findViewById<TextView>(R.id.healthType).text.toString()
            val num = view.findViewById<TextView>(R.id.healthNum).text.toString()
            val units = view.findViewById<TextView>(R.id.healthUnits).text.toString()
            var goalString = ""

            if (units == "" && goalList.contains(type)){
                val i = goalList.indexOf(type)
                val newNum = (goalList.get(i+1)?.toInt()?.minus(num.toInt()))
                goalList.set(i+1, newNum.toString())
            }

            for (i in 0..6) {
                var a = goalList.get(3*i)
                var b = goalList.get(3*i +1)
                var c = goalList.get(3*i +2)
                if (a != null && b != null && c != null) {
                    goalString += a + " goal: " + b + " " + c + "\n"
                }

                else if (!goalList.contains(type)){
                    goalList.set(3*i, type)
                    goalList.set(3*i +1, num)
                    goalList.set(3*i +2, units)
                    goalString += type + " goal: " + num + " " + units + "\n"
                }
            }
            view.findViewById<TextView>(R.id.health_notes).text = goalString
            view.findViewById<TextView>(R.id.healthType).text = ""
            view.findViewById<TextView>(R.id.healthNum).text = ""
            view.findViewById<TextView>(R.id.healthUnits).text = ""
        }

    }
}
