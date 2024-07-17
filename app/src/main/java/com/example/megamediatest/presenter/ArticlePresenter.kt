package com.example.megamediatest.presenter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megamediatest.MainActivity
import com.example.megamediatest.R
import com.example.megamediatest.adapters.MyRecyclerViewAdapter
import com.example.megamediatest.data.filePojo
import com.example.megamediatest.interfaces.ArticleInterface
import com.example.megamediatest.utils.Functions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue

class ArticlePresenter(context: MainActivity, recycler: RecyclerView) : ArticleInterface.Presenter {
    private lateinit var util : Functions

    private  val mContext: Context = context
    private  val rvFiles: RecyclerView = recycler

    override fun initMyDataList(listOfPath: List<String>, myDataList: ArrayList<filePojo>): Boolean{
        var database: DatabaseReference
        for (path in listOfPath) {
            database = FirebaseDatabase.getInstance().getReference(path)
            database.get().addOnCompleteListener(OnCompleteListener<DataSnapshot> { task ->
                if (task.isSuccessful) {
                    val result = task.result.getValue<filePojo>()
                    if (result != null) {
                        myDataList.add(result)
                        initTemplate(myDataList)
                    }

                } else {
                    Log.d("TAG", task.exception!!.message!!)
                }
            })
        }
        return true
    }
    override fun initTemplate(myDataList: ArrayList<filePojo>) : Boolean {
        if(myDataList.isNotEmpty()) {
            rvFiles.layoutManager = LinearLayoutManager(mContext)

            val adapter = MyRecyclerViewAdapter(mContext, myDataList)

            rvFiles.adapter = adapter

            return true
        }else{
            util.showErrorDialog(R.string.noRecord.toString())
            return false
        }
    }
}