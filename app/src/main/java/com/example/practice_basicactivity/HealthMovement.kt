package com.example.practice_basicactivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.util.Log

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HealthMovement : Fragment() {
    val MyPREFERENCES = "MyPrefs"
    var sharedpreferences: SharedPreferences? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.health_movement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpreferences = activity?.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        val typeList = sharedpreferences?.getStringSet("healthtype", null)
        var numList = sharedpreferences?.getString("healthnum", "")
        val numListTemp = numList?.split(",")?.toMutableList()
        val unitList = sharedpreferences?.getStringSet("healthunites", null)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.act_healthToHome)
        }

        view.findViewById<Button>(R.id.waterEnter).setOnClickListener {
            val type = view.findViewById<TextView>(R.id.healthType).text.toString()
            val num = view.findViewById<TextView>(R.id.healthNum).text.toString()
            val units = view.findViewById<TextView>(R.id.healthUnits).text.toString()
            var goalString = ""

            if (typeList != null) {
                if (units == "" && typeList.contains(type)){
                    val i = typeList.indexOf(type)
                    val newNum = (numList?.get(i+1)?.toInt()?.minus(num.toInt()))
                    if (numListTemp != null) {
                        numListTemp.set(i, newNum.toString())
                    }
                }
            }

            if (typeList != null) {
                for (i in 1..typeList.size) {
                    var type = typeList?.elementAt(i)
                    var num = numList?.get(i)
                    var unit = unitList?.elementAt(i)
                    if (type != null && num != null && unit != null) {
                        goalString += type + " goal: " + num + " " + unit + "\n"
                    } else if (typeList != null) {
                        if (!typeList.contains(type)){
                            if (typeList != null) {
                                typeList.add(type)
                            }
                            if (numList != null) {
                                numList += num
                            }
                            if (unitList != null) {
                                unitList.add(unit)
                            }
                            goalString += type + " goal: " + num + " " + units + "\n"
                        }
                    }
                }
            }
            goalString = sharedpreferences?.getStringSet("health", null)!!.elementAt(0)

            view.findViewById<TextView>(R.id.health_notes).text = goalString
            view.findViewById<TextView>(R.id.healthType).text = ""
            view.findViewById<TextView>(R.id.healthNum).text = ""
            view.findViewById<TextView>(R.id.healthUnits).text = ""

            val editor = sharedpreferences!!.edit()
            editor.putStringSet("health", typeList);
            editor.commit()
        }

    }
}