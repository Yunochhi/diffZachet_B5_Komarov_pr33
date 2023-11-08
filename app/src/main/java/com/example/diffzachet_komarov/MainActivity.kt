package com.example.diffzachet_komarov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var enterBook: TextInputEditText
    lateinit var getBookButton: MaterialButton

    lateinit var text1:MaterialTextView
    lateinit var text2:MaterialTextView
    lateinit var text3:MaterialTextView
    lateinit var text4:MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterBook = findViewById(R.id.enterBook)
        getBookButton = findViewById(R.id.getBookButton)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        getBookButton.setOnClickListener()
        {
            getBook(enterBook.text.toString())
        }
    }
    fun getBook( book:String ){
        if(book.toString().isNotEmpty()){
            val url = "https://openlibrary.org/api/books?bibkeys=$book"

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                GET,url,{ response->
                    val jsonObject  = JSONObject(response)

                    val bib_key = jsonObject.getString("bib_key").toString()
                    val info_url = jsonObject.getString("info_url").toString()
                    val preview = jsonObject.getString("preview").toString()
                    val thumbnail_url = jsonObject.getString("thumbnail_url").toString()

                    text1.text = bib_key
                    text2.text = preview
                },
                {
                    Log.d("MyLog","Volley error: $it")
                }
            )
            queue.add(stringRequest)
        }
        else{
            var snackBar = Snackbar.make(findViewById(android.R.id.content),"Invalid input", Snackbar.LENGTH_LONG).show()
        }
    }

}

