package com.example.palmare.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toUpperCase
import androidx.navigation.fragment.findNavController
import com.example.palmare.R
import com.example.palmare.databinding.FragmentHeadBinding
import java.time.LocalDate
import java.time.ZoneId
import kotlin.properties.Delegates

class HeadFragment : Fragment() {

    companion object {
        val argumentData = "opType"
        const val TAG = "HeadFragment"
    }

    private var _binding: FragmentHeadBinding? = null
    private val binding get() = _binding!!
    private var typeId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeId = it.getInt(argumentData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (typeId) {
            1 -> {
                val task: String = context?.resources?.getString(R.string.prelievo_produzione).toString()
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.second_view_title, task).uppercase()
            }
            2 -> {
                val task: String = context?.resources?.getString(R.string.rientro_produzione).toString()
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.second_view_title, task).uppercase()
            }
            3 -> {
                val task: String = context?.resources?.getString(R.string.prelievo_riparazione).toString()
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.second_view_title, task).uppercase()
            }
            4 -> {
                val task: String = context?.resources?.getString(R.string.prelievo_conto_lavoro).toString()
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.second_view_title, task).uppercase()
            }
        }

        binding.apply {

            //set date
            val dateData = LocalDate.now(ZoneId.of("Europe/Rome"))
            date.text = getString(R.string.date, dateData)

            commessaInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    if(commessaInput.text!!.isNotEmpty()) {
                    val action = HeadFragmentDirections.actionHeadFragmentToArticleFragment(typeId, commessaInput.text.toString())
                    findNavController().navigate(action)
                    } else{
                        Toast.makeText(activity, "Campo vuoto presente", Toast.LENGTH_SHORT).show()
                    }
                    return@OnKeyListener true
                }
                false
            })

            indietro.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}