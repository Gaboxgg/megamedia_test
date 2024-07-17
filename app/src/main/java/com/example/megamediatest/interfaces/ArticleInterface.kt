package com.example.megamediatest.interfaces

import com.example.megamediatest.data.filePojo

 interface ArticleInterface{

    interface  Presenter{
        fun initMyDataList(listOfPath: List<String>, myDataList: ArrayList<filePojo>): Boolean
        fun initTemplate(myDataList: ArrayList<filePojo>): Boolean
    }
}