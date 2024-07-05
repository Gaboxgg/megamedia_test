package com.example.megamediatest


import com.example.megamediatest.data.filePojo
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class MainActivityKtTest {

    lateinit var myDataListEmpty : ArrayList<filePojo>
    lateinit var sortNamedFiles : ArrayList<String>
    lateinit var fileList : ArrayList<filePojo>

    @Before
    fun setUp(){
        myDataListEmpty = arrayListOf()
        sortNamedFiles = arrayListOf("movie_1","movie_2","movie_3")
        fileList = arrayListOf()
        lateinit var pojo: filePojo

        for(name in sortNamedFiles)
            pojo = filePojo(title = "name", description = "desc", url = "", img="")
            fileList.add(pojo)
    }

    @Test
    fun initDataEmptyTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. initMyDataList(listOf())).thenReturn(true)
        assertEquals(myDataListEmpty,mainActivity.myDataList)
    }

    @Test
    fun isOnlineTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. isOnline(mainActivity.mContext)).thenReturn(true)
    }

    @Test
    fun initTemplateEmptyTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. initTemplate(arrayListOf())).thenReturn(false)
    }

    @Test
    fun initTemplateTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. initTemplate(fileList)).thenReturn(true)
    }
}


