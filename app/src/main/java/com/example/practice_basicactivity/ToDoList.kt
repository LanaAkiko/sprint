package com.example.practice_basicactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.fragment.findNavController


class ToDoList : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.ButtonToHome).setOnClickListener {
            findNavController().navigate(R.id.action_Todo_to_Home)
        }

        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        // Adding the items to the list when the add button is pressed
        findViewById<TextView>(R.id.add).setOnClickListener {
            itemlist.add(findViewById<TextView>(R.id.editText).text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input space or the edit text space will be cleared
            findViewById<TextView>(R.id.editText).setText("")
        }

        // Selecting and Deleting the items from the list when the delete button is pressed
        findViewById<TextView>(R.id.delete).setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        // Clearing all the items in the list when the clear button is pressed
        findViewById<TextView>(R.id.clear).setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        // Adding the toast message to the list when an item on the list is pressed
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(
                this,
                "You Selected the item --> " + itemlist.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}