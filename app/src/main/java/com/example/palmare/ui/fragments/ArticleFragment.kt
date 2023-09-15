package com.example.palmare.ui.fragments

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.palmare.R
import com.example.palmare.databinding.FragmentArticleBinding
import com.example.palmare.formatter.DecimalDigitsInputFilter
import com.example.palmare.ui.fragments.HeadFragment.Companion.argumentData
import com.example.palmare.viewmodel.ArticleViewModel
import java.text.DecimalFormat
import kotlin.properties.Delegates


class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private var typeId by Delegates.notNull<Int>()
    private lateinit var typeCommessa: String
    private val viewModel: ArticleViewModel by activityViewModels()

    companion object {
        const val COMMESSA = "commessa"
        const val TAG = "ArticleFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeId = it.getInt(argumentData)
            typeCommessa = it.getString(COMMESSA).toString()//Hold this data to pass it in the end
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated!")
        fun setAppTitle(id: Int) {
            when (id) {
                1 -> {
                    val task: String =
                        context?.resources?.getString(R.string.prelievo_produzione).toString()
                    (activity as AppCompatActivity).supportActionBar?.title =
                        getString(R.string.third_view_title, task).uppercase()
                }

                2 -> {
                    val task: String =
                        context?.resources?.getString(R.string.rientro_produzione).toString()
                    (activity as AppCompatActivity).supportActionBar?.title =
                        getString(R.string.third_view_title, task).uppercase()
                }

                3 -> {
                    val task: String =
                        context?.resources?.getString(R.string.prelievo_riparazione).toString()
                    (activity as AppCompatActivity).supportActionBar?.title =
                        getString(R.string.third_view_title, task).uppercase()
                }

                4 -> {
                    val task: String =
                        context?.resources?.getString(R.string.prelievo_conto_lavoro).toString()
                    (activity as AppCompatActivity).supportActionBar?.title =
                        getString(R.string.third_view_title, task).uppercase()
                }
            }
        }
        setAppTitle(typeId)


        binding.apply {
            //default
            sessionScan.text =
                context?.resources?.getString(
                    R.string.scan_session,
                    viewModel.scanQty,
                    viewModel.totalScans
                )

            indietro.setOnClickListener {
                findNavController().navigateUp()
            }

            /*
            15 digits before includes the possibility
            for a "." but the error remains that 14 int can be inserted.
            We let this error happen to handle it inside
            an if statement and make a toast that the quantity of int is invalid*/
            quantityInput.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(15, 3))


            fun scanOperation() {

                if (quantityInput.text!!.length > 17) {
                    Toast.makeText(
                        activity,
                        "Error. Invalid length. Max 13 Int digits.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.addToList(
                        typeId,
                        typeCommessa,
                        articoloInput.text.toString(),
                        quantityInput.text.toString()
                    )
                }

                sessionScan.text =
                    context?.resources?.getString(
                        R.string.scan_session,
                        viewModel.scanQty,
                        viewModel.totalScans
                    )

                //clear the input fields
                articoloInput.text?.clear()
                quantityInput.text?.clear()
            }

            quantityInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    if (quantityInput.text!!.isNotEmpty() && articoloInput.text!!.isNotEmpty()) {
                        scanOperation()
                        return@OnKeyListener true
                    } else {
                        Toast.makeText(activity, "Campo vuoto presente", Toast.LENGTH_SHORT).show()
                    }
                }
                false
            })

            finishOperation.setOnClickListener {
                findNavController().navigate(R.id.action_articleFragment_to_dialogConfirmation)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}