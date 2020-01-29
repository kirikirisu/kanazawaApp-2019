import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.Model.StapleFoodModel
import com.example.kanazawaapp_2019.R
import com.example.kanazawaapp_2019.adapters.StapleFoodListAdapter

class StapleFoodFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainFrame = inflater!!.inflate(R.layout.fragment_staple_food, container, false)
        val listview = mainFrame.findViewById(R.id.stapleFoodListViewID

        ) as ListView
        val dataArray = generateData()
        val adapter = StapleFoodListAdapter(context!!, generateData())

        listview.adapter = adapter
        return mainFrame
    }

    private fun generateData(): ArrayList<StapleFoodModel>{
        var result = ArrayList<StapleFoodModel>()

        for (i in 0..9){
            var user = StapleFoodModel("name","deadline")
            result.add(user)
        }
        return result
    }
}