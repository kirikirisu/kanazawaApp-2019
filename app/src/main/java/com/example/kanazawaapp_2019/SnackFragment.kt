import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.R

class SnackFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainFrame = inflater!!.inflate(R.layout.fragment_snack, container, false)
        val listview = mainFrame!!.findViewById(R.id.snackListViewID) as ListView
        val dataArray =  arrayOf("スナックa", "スナックb", "スナックc")
        val adapter =  this.context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, dataArray)}
        listview.adapter = adapter
        return mainFrame
    }
}