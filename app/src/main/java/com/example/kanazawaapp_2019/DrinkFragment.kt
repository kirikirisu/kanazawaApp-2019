import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.R

class DrinkFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainFrame = inflater!!.inflate(R.layout.fragment_drink, container, false)
        val listview = mainFrame.findViewById(R.id.drinkListViewID) as ListView
        val dataArray = arrayOf("kotlin", "android", "swift","ios")
        val adapter = this.context?.let { ArrayAdapter<String>(it,  android.R.layout.simple_list_item_1, dataArray) }
        listview.adapter = adapter
        return mainFrame
    }
}