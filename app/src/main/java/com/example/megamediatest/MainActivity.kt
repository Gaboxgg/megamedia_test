package com.example.megamediatest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megamediatest.adapters.MyRecyclerViewAdapter
import com.example.megamediatest.data.filePojo
import com.example.megamediatest.utils.Functions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue

class MainActivity : ComponentActivity() {
    private var listOfPath = listOf("movie_1","movie_2","movie_3")
    private var myDataList : ArrayList<filePojo> = arrayListOf()
    private lateinit var mContext: Context
    private lateinit var rvFiles: RecyclerView
    private lateinit var util : Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        rvFiles = findViewById<RecyclerView>(R.id.rvFiles)

        mContext = this

        util = Functions(mContext)

        if(util.isOnline())
            initMyDataList(listOfPath)
        else
            util.showErrorDialog(R.string.tryAgain.toString())
    }

    fun initMyDataList(listOfPath: List<String>) : Boolean {
        myDataList = arrayListOf()
        var database: DatabaseReference
        for (path in listOfPath) {

            database = FirebaseDatabase.getInstance().getReference(path)
            database.get().addOnCompleteListener(OnCompleteListener<DataSnapshot> { task ->
                if (task.isSuccessful) {
                    val result = task.result.getValue<filePojo>()
                    if (result != null) {
                        myDataList.add(result)
                    }
                    Log.d("TAG", result.toString())
                    Log.d("TAG", myDataList.size.toString())
                    if (myDataList.isNotEmpty()){
                        initTemplate(myDataList)
                    }
                } else {
                    Log.d("TAG", task.exception!!.message!!)
                }
            })
        }
        return true
    }

    fun initTemplate(fileList: List<filePojo>) : Boolean {
        if(fileList.isNotEmpty()) {
            rvFiles.layoutManager = LinearLayoutManager(mContext)

            val adapter = MyRecyclerViewAdapter(mContext, fileList)

            rvFiles.adapter = adapter

            return true
        }else{
            util.showErrorDialog(R.string.noRecord.toString())
            return false
        }
    }
}