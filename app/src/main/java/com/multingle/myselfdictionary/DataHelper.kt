package com.multingle.myselfdictionary
import android.content.Context
import android.content.SharedPreferences

class DataHelper(val context: Context)   {

    var PRIVATE_MODE = 0
    val PREF_NAME = "dict"



    fun add(word: String, meaning: String){
        val sharedPref: SharedPreferences =  context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()

        val set = sharedPref.getStringSet("IDSet", mutableSetOf<String>())

        var last = 0
        last = sharedPref.getInt("lastID",0)

        last = last+ 1
        set?.add(last.toString())

        editor.putStringSet("IDSet", set)
        editor.putInt("lastID",last)
        editor.putString("word"+last,word)
        editor.putString("meaning"+last,meaning)
        editor.commit()

        return
    }

    fun get()   {
        val sharedPref: SharedPreferences =  context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()

        FirstFragment.language = mutableSetOf<String>()
        FirstFragment.description = mutableSetOf<String>()
        FirstFragment.id_set = mutableSetOf<String>()

        var id_set = sharedPref.getStringSet("IDSet", mutableSetOf<String>())
        id_set?.iterator()?.forEach {
            FirstFragment.language.add(sharedPref.getString("word$it","").toString())
            FirstFragment.description.add(sharedPref.getString("meaning$it","").toString())
            FirstFragment.id_set.add(it)
        }

        return
    }

    fun remove(id: String){
        val sharedPref: SharedPreferences =  context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()

        val set = sharedPref.getStringSet("IDSet", mutableSetOf<String>())

        var last = 0
        last = sharedPref.getInt("lastID",0)

        var temp_id_set = mutableSetOf<String>()
        var id_set = sharedPref.getStringSet("IDSet", mutableSetOf<String>())
        id_set?.iterator()?.forEach {
            if(!id.equals(it))
            temp_id_set.add("$it")
        }

        editor.putStringSet("IDSet", temp_id_set)


        editor.commit()
        get()
        return
    }

}