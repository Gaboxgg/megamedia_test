package com.example.megamediatest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megamediatest.adapters.MyRecyclerViewAdapter
import com.example.megamediatest.data.filePojo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue

class MainActivity : ComponentActivity() {
    private var listOfPath = listOf("movie_1","movie_2","movie_3")
    var myDataList : ArrayList<filePojo> = arrayListOf()
    lateinit var mContext: Context
    lateinit var rvFiles: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFiles = findViewById<RecyclerView>(R.id.rvFiles)

        mContext = this
        if(isOnline(mContext))
            initMyDataList(listOfPath)
        else
            showErrorDialog()
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

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }else{
                return false
            }
        }
        return false
    }

    private fun showErrorDialog() {
        Toast.makeText(mContext, R.string.tryAgain, Toast.LENGTH_LONG).show()
    }

    fun initTemplate(fileList: List<filePojo>) : Boolean {
        if(fileList.isNotEmpty()) {
            rvFiles.layoutManager = LinearLayoutManager(mContext)

            val adapter = MyRecyclerViewAdapter(mContext, fileList)

            rvFiles.adapter = adapter

            return true
        }else{
            Toast.makeText(mContext,R.string.noRecord,Toast.LENGTH_LONG).show()
            return false
        }
    }
}

