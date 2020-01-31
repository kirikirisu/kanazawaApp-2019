import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.Model.DrinkModel
import com.example.kanazawaapp_2019.R
import com.example.kanazawaapp_2019.adapters.DrinkListAdapter

class DrinkFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainFrame = inflater!!.inflate(R.layout.fragment_drink, container, false)
        val listview = mainFrame.findViewById(R.id.drinkListViewID) as ListView
        val dataArray = generateData()
        val adapter = DrinkListAdapter(context!!, generateData())

        listview.adapter = adapter

        return mainFrame
    }

    private fun generateData(): ArrayList<DrinkModel>{
        var result = ArrayList<DrinkModel>()

        for (i in 0..9){
            var user = DrinkModel("name","deadline")
            result.add(user)
        }
        return result
    }
}