package com.example.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var nameText : EditText
    private lateinit var ageText : EditText
    private lateinit var sf : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor // need this editor instance to add and edit values for shared references


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Intiliazing instance variables
        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)//mysf is name of the file to store shared refereences of this app
        editor = sf.edit()

    }

    override fun onPause() {
        super.onPause()
        // now code for getting user input
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        // using editor to save these values to the shared preferences we do that as name value by
        // us we need to provide a preference name for each value
        editor.apply{
            putString("sf_name", name)
            putInt("sf_name", age)
            commit()
        }
    }
    // to display saved data ,override the onResume function
    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)
        if(age != 0){
            ageText.setText(age.toString())
        }
    }
}