package com.multingle.myselfdictionary
  
import android.app.Activity  
import android.view.View  
import android.view.ViewGroup  
import android.widget.*



class MyListAdapter(private val context: Activity, private val sozlukVerileri: Array<SozlukVeri>)
    : ArrayAdapter<String>(context, R.layout.custom_list, FirstFragment.getTitles(sozlukVerileri)) {
  
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {  
        val inflater = context.layoutInflater  
        val rowView = inflater.inflate(R.layout.custom_list, null, true)  
  
        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val textView_id = rowView.findViewById(R.id.textView_id) as TextView

        titleText.text = sozlukVerileri.elementAt(position).language
        subtitleText.text = sozlukVerileri.elementAt(position).description
        textView_id.text = sozlukVerileri.elementAt(position).id_set
        
        return rowView  
    }  
}