package com.my.muhammadaliftajudin.sqlitestorage

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.my.muhammadaliftajudin.sqlitestorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mUserData: EventDataSQLHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserData = EventDataSQLHelper(this)

        binding.savedBtn.setOnClickListener {
            enterData(binding.savedEditText.text.toString())
        }

        binding.retrievedBtn.setOnClickListener {
            val cursor = getEvents() // Retrieve Data from DB
            if (cursor != null) {
                val data = showData(cursor) // Show retrieved data in the UI
                binding.retrievedTextView.setText(data)
            }
        }
    }

    private fun showData(cursor: Cursor): String {
        val ret = StringBuilder("")
        while (cursor.moveToNext()){
            val id = cursor.getLong(0)
            val name = cursor.getString(1)
            ret.append(id).append(": ").append(name).append("\n")
        }
        return ret.toString()
    }

    private fun getEvents(): Cursor? {
        // Retrieve the data for reading purpose
        val db = mUserData?.readableDatabase
        val cursor = db?.query(EventDataSQLHelper.TABLE, null,
            null, null, null, null, null)
        startManagingCursor(cursor)
        return cursor
    }

    private fun enterData(username: String) {
        // Retrieve the data for write purpose
        val db = mUserData?.writableDatabase
        val values = ContentValues()
        values.put(EventDataSQLHelper.USERNAME, username)
        // INSERT Inside Table, values
        db?.insert(EventDataSQLHelper.TABLE, null, values)
    }
}