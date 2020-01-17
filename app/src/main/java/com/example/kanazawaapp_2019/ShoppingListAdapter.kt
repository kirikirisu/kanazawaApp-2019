package com.example.kanazawaapp_2019

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ShoppingListAdapter(private var activity: Activity, private var items: ArrayList<ShoppingItem>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var shoppingListItemImageView: ImageView? = null
        var shoppingListItemTextView: TextView? = null
        var shoppingListCountTextView: TextView? = null

        init {
            this.shoppingListItemTextView = row?.findViewById<TextView>(R.id.shoppingListItemNameText)
            this.shoppingListCountTextView = row?.findViewById<TextView>(R.id.shoppingListItemCountText)
            this.shoppingListItemImageView = row?.findViewById<ImageView>(R.id.shoppingListItemImageView)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.shoping_list_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var shoppingItem = items[position]
        viewHolder.shoppingListItemTextView?.text = shoppingItem.item
        viewHolder.shoppingListCountTextView?.text = shoppingItem.count

        return view as View
    }

    override fun getItem(i: Int): ShoppingItem {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}