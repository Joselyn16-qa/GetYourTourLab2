package com.example.gyt

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    var year=0
    var month=0
    var day=0
    var savedDay=0
    var savedMonth=0
    var savedYear= 0
    private var param1: String? = null
    private var param2: String? = null
    private var layoutManager : RecyclerView.LayoutManager? =null
    private var adapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recyclerViewTours.apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = RecyclerViewAdapter()
        }
        pickDate()
    }

    private fun getDateCalendar(){
        val cal : Calendar = Calendar.getInstance()
        year = cal.get(Calendar.YEAR)
        month=cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)
    }
    private fun pickDate(){


        button.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
          //   DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        textDate.text="$savedDay-$savedMonth-$savedYear"

    }


}