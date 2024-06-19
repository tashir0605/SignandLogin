package com.example.hope
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hope.adapter.EventAdapter
import com.example.hope.model.Event

class EventActivity : AppCompatActivity() {
    lateinit var itemAdapter: EventAdapter
    lateinit var dataList: ArrayList<Event>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        dataList=ArrayList()

        dataList.add(Event("21-25\n JUNE","Prometeo","https://unstop.com/college-fests/prometeo24-indian-institute-of-technology-iit-jodhpur-176818", R.drawable.img ))
        dataList.add(Event("21-25\n JUNE","IGNUS","https://www.ignus.co.in/", R.drawable.ignus ))
        dataList.add(Event("21-25\n JUNE","Aaftaab","https://www.instagram.com/aaftaab_iitj/?hl=en", R.drawable.aaftaab))
        dataList.add(Event("21-25\n JUNE","Varchas","https://www.varchas23.in/", R.drawable.varchas ))
        dataList.add(Event("21-25\n JUNE","Spandan","https://www.instagram.com/spandan_iitj/", R.drawable.spandan ))
        dataList.add(Event("21-25\n JUNE","Intellia","https://intellia-website.vercel.app/", R.drawable.intellia ))
        dataList.add(Event("21-25\n JUNE","Edificio","https://www.linkedin.com/company/edificio-iit-jodhpur/?originalSubdomain=in", R.drawable.edificio ))
        dataList.add(Event("21-25\n JUNE","Kridansh","https://www.instagram.com/bss.iitj/p/B9eEkuaJTV0/", R.drawable.kridansh ))






        itemAdapter = EventAdapter(dataList, this )
        recyclerView.layoutManager = LinearLayoutManager(this)




        recyclerView.adapter = itemAdapter

        // Add a text watcher to the search EditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                itemAdapter.filterList(s.toString())
                if (itemAdapter.itemCount == 0) {
                    Toast.makeText(this@EventActivity, "No match found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}