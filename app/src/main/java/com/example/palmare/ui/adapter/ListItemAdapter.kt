package com.example.palmare.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.palmare.R
import com.example.palmare.model.ItemModel
import com.example.palmare.ui.fragments.DialogConfirmation

// ListAdapter tutorial: https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-intro-room-flow#7
private const val TAGADAPTER = "ListItemAdapter"
class ListItemAdapter(private val context: Context, private val items: MutableList<ItemModel>): RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>()  {

    class ListItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val summary = view.findViewById<TextView>(R.id.summary)
//        val qty = view.findViewById<TextView>(R.id.qty_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = items[position]
        val resources = context.resources
        holder.summary.text = resources?.getString(R.string.riepilogo, item.mat, item.qty)

        holder.summary.setOnClickListener {
            //FIXME: make the display of the list dynamic. (Try ListAdapter or observe changes on the mutablelive data from the DialogFragment)
            Log.d(TAGADAPTER, "this is the item clicked: ${item}")
            Log.d(TAGADAPTER, "Before remove ${items}")
            items.remove(item)
            Log.d(TAGADAPTER, "AFTER REMOVE: $items")
        }
    }

    override fun getItemCount() = items.size
}