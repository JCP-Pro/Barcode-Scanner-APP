package com.example.palmare.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.palmare.R
import com.example.palmare.model.ItemModel

// ListAdapter tutorial: https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-intro-room-flow#7

class ListItemAdapter(private val context: Context, private val items: MutableList<ItemModel>): RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>()  {

    class ListItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val mat = view.findViewById<TextView>(R.id.mat_label)
        val qty = view.findViewById<TextView>(R.id.qty_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = items[position]
        val resources = context.resources
        holder.mat.text = resources?.getString(R.string.riepilogo_mat, item.mat)
        holder.qty.text = resources?.getString(R.string.riepilogo_quantita, item.qty)

        holder.view.setOnClickListener {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = items.size
}