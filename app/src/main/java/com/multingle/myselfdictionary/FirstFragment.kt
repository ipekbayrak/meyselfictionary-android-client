package com.multingle.myselfdictionary

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.remove_dialog.view.*

class FirstFragment : Fragment() {



    public companion object{
        var language = mutableSetOf<String>()
        var description = mutableSetOf<String>()
        var id_set = mutableSetOf<String>()

        fun reloadListView(context: Context,list_view: ListView){
            var dh = DataHelper(context)

            dh.get()

            val myListAdapter = MyListAdapter(context as Activity,language.toTypedArray(),description.toTypedArray(),id_set.toTypedArray())
            list_view.adapter = myListAdapter

            list_view.setOnItemClickListener() { adapterView, view, position, id ->
                val itemAtPos = adapterView.getItemAtPosition(position)
                val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                val textView_id = adapterView.findViewById(R.id.textView_id) as TextView

                removeMessageBox(textView_id.text.toString(),context, list_view)
            }
        }

        fun removeMessageBox(id: String, context: Context,list_view : ListView){

            val messageBoxView = LayoutInflater.from(context).inflate(R.layout.remove_dialog, null)
            val messageBoxBuilder = AlertDialog.Builder(context).setView(messageBoxView)
            val messageBoxInstance = messageBoxBuilder.show()

            messageBoxView.button_yes.setOnClickListener { view ->

                var dh = DataHelper(context)
                dh.remove(id.toString())
                reloadListView(context,list_view)
                list_view.deferNotifyDataSetChanged()
                //Toast.makeText(this.requireContext(), "Removed", Toast.LENGTH_LONG).show()
                messageBoxInstance.dismiss();
            }

            messageBoxView.button_no.setOnClickListener { view ->
                messageBoxInstance.dismiss()
            }


            messageBoxView.setOnClickListener(){
                messageBoxInstance.dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reloadListView(this.requireContext(),list_view)
        list_view.deferNotifyDataSetChanged()
    }







}