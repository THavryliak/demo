package com.example.pecode_test.view

import android.content.Context
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pecode_test.Connector
import com.example.pecode_test.R


class Home : Fragment() {

    private lateinit var connector: Connector

    companion object {
        fun newInstance(number: Int): Home {
            val fragment = Home()
            val bundle = Bundle(1)
            bundle.putInt(EXTRA_MESSAGE, number)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View? = inflater.inflate(R.layout.fragment_home, container, false)

        val message = arguments!!.getInt(EXTRA_MESSAGE)
        val pageNumber: TextView = view!!.findViewById(R.id.page_number)
        pageNumber.text = message.toString()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        connector = context as Connector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val plusBtn = view.findViewById<View>(R.id.plus)
        val delBtn = view.findViewById<View>(R.id.delete)
        val notificationBtn = view.findViewById<View>(R.id.notification_button)

        plusBtn.setOnClickListener {
            connector.onNextBtnClick()
        }

        delBtn.setOnClickListener {
            connector.onDeleteBtnClick()
        }

        notificationBtn.setOnClickListener {
            connector.notificationButtonClick()
        }
    }
}