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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.palmare.R
import com.example.palmare.ui.adapter.ListItemAdapter
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

        val recyclerView: RecyclerView = view.findViewById(R.id.list_recyclerview)

        recyclerView.apply {
            adapter = ListItemAdapter(requireContext(), viewModel.items)
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        val decline = view.findViewById<Button>(R.id.decline)
        decline.setOnClickListener {
            findNavController().navigateUp()
            if (viewModel.items.size != viewModel.totalScans) {
                Log.d(ArticleFragment.TAG, "this is the item size :${viewModel.items.size}")
                Log.d(ArticleFragment.TAG, "this is the scan quantity: ${viewModel.scanQty}")
                Log.d(ArticleFragment.TAG, "change")
                viewModel.onResumeQty()
            }
            //Filter list and remove
            viewModel.filterList()
        }

        val confirm = view.findViewById<Button>(R.id.confirm)
        confirm.setOnClickListener {
            viewModel.filterList()
            viewModel.sendAuth()
            if (viewModel.status.value != null) {
                viewModel.operationComplete()
                findNavController().navigate(R.id.action_dialogConfirmation_to_taskOperationFragment)
            } else Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show()
        }
    }
}