package com.example.kanazawaapp_2019


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_shopping_site.*
import kotlinx.android.synthetic.main.fragment_shopping_site.view.*

/**
 * A simple [Fragment] subclass.
 */
class ShoppingSiteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_site, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.amazonButton.setOnClickListener{
            val uri = Uri.parse("https://www.amazon.co.jp/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        view!!.rakutenButton.setOnClickListener{
            val uri = Uri.parse("https://www.rakuten.co.jp/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        view!!.yahooButton.setOnClickListener{
            val uri = Uri.parse("https://shopping.yahoo.co.jp/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        view!!.constraintLayout.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}