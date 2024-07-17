package com.example.megamediatest

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.megamediatest.data.filePojo
import com.example.megamediatest.presenter.ArticlePresenter
import com.example.megamediatest.utils.Functions


class MainActivity : ComponentActivity() {
    private var listOfPath = listOf("movie_1","movie_2","movie_3")
    private var myDataList : ArrayList<filePojo> = arrayListOf()
    private lateinit var mContext: Context
    private lateinit var rvFiles: RecyclerView
    private lateinit var util : Functions
    private lateinit var presenter : ArticlePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFiles = findViewById<RecyclerView>(R.id.rvFiles)

        mContext = this

        util = Functions(mContext)

        presenter = ArticlePresenter(mContext as MainActivity,rvFiles)

        if(util.isOnline()){
            presenter.initMyDataList(listOfPath,myDataList)
        }else {
            util.showErrorDialog(R.string.tryAgain.toString())
        }
    }
}