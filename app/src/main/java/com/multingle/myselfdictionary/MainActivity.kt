package com.multingle.myselfdictionary

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.multingle.myselfdictionary.FirstFragment.Companion.reloadListView
import kotlinx.android.synthetic.main.custom_dialog.view.*

import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.remove_dialog.view.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            showMessageBox();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showMessageBox(){

        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        val  messageBoxInstance = messageBoxBuilder.show()

        val meaning = messageBoxView.editText_meaning.text
        val word = messageBoxView.editText_word.text

        messageBoxView.button_add.setOnClickListener { view ->

            var dh = DataHelper(this)
            dh.add(word.toString(), meaning.toString())
            dh.get()

            //Toast.makeText(this.requireContext(), "Eklendi", Toast.LENGTH_LONG).show()
            reloadListView(this,list_view)
            list_view.deferNotifyDataSetChanged()



            messageBoxInstance.dismiss();
        }

        messageBoxView.setOnClickListener(){
            messageBoxInstance.dismiss()
        }
    }



}