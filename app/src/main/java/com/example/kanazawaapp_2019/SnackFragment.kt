import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.Model.SnackModel
import com.example.kanazawaapp_2019.R
import com.example.kanazawaapp_2019.adapters.SnackListAdapter


class SnackFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainFrame = inflater!!.inflate(R.layout.fragment_snack, container, false)
        val listview = mainFrame.findViewById(R.id.snackListViewID) as ListView
        val dataArray = generateData()
        val adapter = SnackListAdapter(context!!, generateData())

        listview.adapter = adapter
        return mainFrame
    }

    private fun generateData(): ArrayList<SnackModel>{
        var result = ArrayList<SnackModel>()

        for (i in 0..9){
            var user = SnackModel("name","deadline")
            result.add(user)
        }
        return result
    }
}