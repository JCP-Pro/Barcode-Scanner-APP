package com.example.palmare.ui.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.palmare.R
import com.example.palmare.model.ItemModel

private const val TAGADAPTER = "ListItemAdapter"

class ListItemAdapter(
    private val context: Context,
    private val items: MutableList<ItemModel>
) : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    class ListItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val summary: TextView? = view.findViewById<TextView>(R.id.summary)
        val divider: View? = view.findViewById<View>(R.id.divider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = items[position]

        fun View?.removeSelf() {
            this ?: return
            val parent = parent as? ViewGroup ?: return
            parent.removeView(this)
        }

        fun confirmDeletion() {
            items[position] = ItemModel(0, "", "", "")
            holder.summary.removeSelf()
            holder.divider.removeSelf()
        }

        val resources = context.resources
        holder.summary?.text = resources?.getString(R.string.riepilogo, item.mat, item.qty)

        fun openDeleteDialog() {
            //How to create a dialog:
            // https://www.geeksforgeeks.org/how-to-create-an-alert-dialog-box-in-android/
            // Create the object of AlertDialog Builder class
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)

            // Set Alert Title
            builder.setTitle(R.string.remove_title)

            // Set the message show for the Alert time
            builder.setMessage(item.toString())

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false)

            builder.setPositiveButton(
                R.string.remove_confirm_btn
            ) { _, _ ->
                confirmDeletion()
            }

            builder.setNegativeButton(
                R.string.btn_indietro
            ) { dialog: DialogInterface, _ ->
                // If user click no then dialog box is canceled.
                dialog.cancel()
            }

            // Create the Alert dialog
            val alertDialog: android.app.AlertDialog? = builder.create()
            // Show the Alert Dialog box
            alertDialog?.show()
        }


        holder.summary?.setOnClickListener {
            openDeleteDialog()
        }
    }

    override fun getItemCount() = items.size
}