package com.example.practice_basicactivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class ToDoList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.todo_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.ButtonToHome).setOnClickListener {
            findNavController().navigate(R.id.act_listToHome)
        }

        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter = activity?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_list_item_multiple_choice, itemlist)}
        val listView: ListView = view.findViewById(R.id.listView) as ListView
        listView.setAdapter(adapter)


        // Adding the items to the list when the add button is pressed
        view.findViewById<TextView>(R.id.add).setOnClickListener {
            itemlist.add(view.findViewById<TextView>(R.id.editText).text.toString())
            listView.adapter = adapter
            if (adapter != null) {
                adapter.notifyDataSetChanged()
            }
            // This is because every time when you add the item the input space or the edit text space will be cleared
            view.findViewById<TextView>(R.id.editText).setText("")
        }

        // Selecting and Deleting the items from the list when the delete button is pressed
        view.findViewById<TextView>(R.id.delete).setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    if (adapter != null) {
                        adapter.remove(itemlist.get(item))
                    }
                }
                item--
            }
            position.clear()
            if (adapter != null) {
                adapter.notifyDataSetChanged()
            }
        }

        // Clearing all the items in the list when the clear button is pressed
        view.findViewById<TextView>(R.id.clear).setOnClickListener {
            itemlist.clear()
            if (adapter != null) {
                adapter.notifyDataSetChanged()
            }
        }

        // Adding the toast message to the list when an item on the list is pressed
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(
                requireActivity(),
                "You Selected the item --> " + itemlist.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}