package com.example.kanazawaapp_2019


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_shopping_site.view.*

/**
 * A simple [Fragment] subclass.
 */

private const val ARG_PARAM1 = "shoppingName"

class ShoppingSiteFragment() : Fragment() {
    private var shoppingName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shoppingName = it.getString(ARG_PARAM1)
        }
    }

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
            val uri = Uri.parse("https://www.amazon.co.jp/s?k=" + shoppingName)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        view!!.rakutenButton.setOnClickListener{
            val uri = Uri.parse("https://search.rakuten.co.jp/search/mall/" + shoppingName)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        view!!.yahooButton.setOnClickListener{
            val uri = Uri.parse("https://shopping.yahoo.co.jp/search?first=1&ss_first=1&ts=1581502771&mcr=ed0c8520fb575c1c52b4c89988e1f7f5&tab_ex=commerce&sretry=1&area=17&dlv=&aq=&oq=&p=" + shoppingName)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        view!!.constraintLayout.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ShoppingSiteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}