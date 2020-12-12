package com.multingle.myselfdictionary
  
import android.app.Activity  
import android.view.View  
import android.view.ViewGroup  
import android.widget.*  
class MyListAdapter(private val context: Activity,
                    private val title: Array<String>,
                    private val description: Array<String>,
                    private val id_set: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, title) {
  
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {  
        val inflater = context.layoutInflater  
        val rowView = inflater.inflate(R.layout.custom_list, null, true)  
  
        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val textView_id = rowView.findViewById(R.id.textView_id) as TextView

        titleText.text = title.elementAt(position)
        subtitleText.text = description.elementAt(position)
        textView_id.text = id_set.elementAt(position)
        
        return rowView  
    }  
}  