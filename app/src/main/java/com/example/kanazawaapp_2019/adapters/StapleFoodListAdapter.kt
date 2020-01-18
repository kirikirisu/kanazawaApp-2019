package com.example.kanazawaapp_2019.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kanazawaapp_2019.Model.StapleFoodModel
import com.example.kanazawaapp_2019.R
import java.text.FieldPosition

class StapleFoodListAdapter(private var context: Context, private var items: ArrayList<StapleFoodModel>) :BaseAdapter(){

    private class ViewHolder(row: View?) {
        var txtName: TextView? = null
        var txtComment: TextView? = null

        init {
            this.txtName = row?.findViewById<TextView>(R.id.txtName)
            this.txtComment = row?.findViewById<TextView>(R.id.txtComment)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.drink_list_row,parent,false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.txtName?.text = userDto.name
        viewHolder.txtComment?.text = userDto.comment

        return view as View
    }

    override fun getItem( i: Int): Any {
        return items[i]
    }

    override fun getItemId(i : Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}