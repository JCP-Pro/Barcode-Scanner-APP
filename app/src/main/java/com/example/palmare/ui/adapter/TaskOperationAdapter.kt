package com.example.palmare.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.palmare.R
import com.example.palmare.data.Operations
import com.example.palmare.ui.fragments.HeadFragmentDirections
import com.example.palmare.ui.fragments.TaskOperationFragmentDirections

const val TAG = "TaskOperationAdapter"
class TaskOperationAdapter(private val context: Context): RecyclerView.Adapter<TaskOperationAdapter.TaskOperationViewHolder>() {
    private val datasource = Operations()
    private val options = datasource.operationOptions

    class TaskOperationViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.operation_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOperationViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_task_operation, parent, false)
        return TaskOperationViewHolder(layout)
    }

    override fun getItemCount() = options.size

    override fun onBindViewHolder(holder: TaskOperationViewHolder, position: Int) {
        val item = options[position]
        holder.button.text = context.resources.getString(item.stringResourceId)
        holder.button.setOnClickListener {
            Log.d("TaskOperationAdapter", "Clicked")
            when(item.type) {
                1 -> {
                    Log.d(TAG, "Type 1.")
                    val action = TaskOperationFragmentDirections.actionTaskOperationFragmentToHeadFragment(item.type)
                    holder.view.findNavController().navigate(action)
                }
                2 -> {
                    Log.d(TAG, "Type 2.")
                    val action = TaskOperationFragmentDirections.actionTaskOperationFragmentToHeadFragment(item.type)
                    holder.view.findNavController().navigate(action)
                }
                3 -> {
                    Log.d(TAG, "Type 3")
                    val action = TaskOperationFragmentDirections.actionTaskOperationFragmentToHeadFragment(item.type)
                    holder.view.findNavController().navigate(action)
                }
                4 -> {
                    Log.d(TAG, "Type 4.")
                    val action = TaskOperationFragmentDirections.actionTaskOperationFragmentToHeadFragment(item.type)
                    holder.view.findNavController().navigate(action)
                }
                else -> Log.d(TAG, "Nothing occurred.")
            }
        }
    }

}