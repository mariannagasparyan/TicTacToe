package com.usmaan.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startNewGameButton = findViewById<Button>(R.id.startNewGameButton)
      var nameTextView=findViewById<TextView>(R.id.editTextName)
        var nameTextView1=findViewById<TextView>(R.id.editTextName2)

        startNewGameButton.setOnClickListener {
            if (TextUtils.isEmpty(editTextName.getText().toString())||
                TextUtils.isEmpty(editTextName2.getText().toString()))
            {
                Toast.makeText(applicationContext, "Լրացնել մասնակցի անունը", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, GameActivity::class.java)
                var name=nameTextView.text.toString()
                intent.putExtra("Name",name)
                var name1=nameTextView1.text.toString()
                intent.putExtra("Name1",name1)
                startActivity(intent)
            }



        }
    }
}