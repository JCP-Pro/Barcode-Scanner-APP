package com.example.palmare.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.palmare.R
import com.example.palmare.viewmodel.ArticleViewModel
import kotlin.properties.Delegates

const val TAG = "DialogConfirmation"

class DialogConfirmation : DialogFragment() {

    private var typeId by Delegates.notNull<Int>()
    private val viewModel: ArticleViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeId = it.getInt(HeadFragment.argumentData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirmation_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decline = view.findViewById<Button>(R.id.decline)
        decline.setOnClickListener {
            findNavController().navigateUp()
        }

        val confirm = view.findViewById<Button>(R.id.confirm)
        confirm.setOnClickListener {
            Log.d(TAG, "This is the list with the data to send: ${viewModel.items}")
            viewModel.sendAuth()
            Log.d(TAG, "Status: ${viewModel.status.value}")
            if (viewModel.status.value != null) {
                viewModel.operationComplete()
                findNavController().navigate(R.id.action_dialogConfirmation_to_taskOperationFragment)
            } else Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show()
        }
    }
}