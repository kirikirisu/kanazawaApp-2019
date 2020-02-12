package com.example.kanazawaapp_2019.adapters

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kanazawaapp_2019.Model.DrinkModel
import com.example.kanazawaapp_2019.R
import java.util.*

class DrinkListAdapter(private var context: Context, private var items: ArrayList<DrinkModel>) :
    BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: TextView? = null
        var deadline: TextView? = null
        var datePicker: Button? = null

        init {
            this.txtName = row?.findViewById<TextView>(R.id.txtName)
            this.deadline = row?.findViewById<TextView>(R.id.deadline)
            this.datePicker = row?.findViewById((R.id.datePicker))
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.drink_list_row, parent, false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val getContext: Context = context

        viewHolder.datePicker?.setOnClickListener() {
            val dtp = DatePickerDialog(
                getContext, DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    Toast.makeText(getContext, "日付を選択しました" + y + m + d, Toast.LENGTH_LONG).show()

//                    deadline.setText()//WIP

                }, year, month, day
            )
            dtp.show()
        }

        var userDto = items[position]
        viewHolder.txtName?.text = userDto.name
        viewHolder.deadline?.text = userDto.comment

        return view as View
    }

    override fun getItem(i: Int): Any {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}
