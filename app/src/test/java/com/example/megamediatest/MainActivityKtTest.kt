package com.example.megamediatest

import com.example.megamediatest.data.filePojo
import com.example.megamediatest.utils.Functions
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainActivityKtTest {

    lateinit var myDataListEmpty : ArrayList<filePojo>
    lateinit var sortNamedFiles : List<String>
    lateinit var fileList : ArrayList<filePojo>

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this);
        myDataListEmpty = arrayListOf()
        sortNamedFiles = listOf("movie_1","movie_2","movie_3")
        fileList = arrayListOf()
        lateinit var pojo: filePojo

        for(name in sortNamedFiles) {
            pojo = filePojo(title = "name", description = "desc", url = "", img = "")
            pojo.title = name
            fileList.add(pojo)
        }
    }

    @Test
    fun initDataEmptyTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. initMyDataList(listOf())).thenReturn(true)
        assertTrue(mainActivity.initMyDataList(listOf()))
    }

    @Test
    fun initTemplateTest() {
        val mainActivity = mock(MainActivity::class.java)
        `when`(mainActivity. initTemplate(fileList)).thenReturn(true)
        assertTrue(mainActivity.initTemplate(fileList))
    }

    @Test
    fun initTemplateEmptyTest() {
        val mainActivity = mock(MainActivity::class.java)
        Mockito.`when`(mainActivity. initTemplate(arrayListOf())).thenReturn(false)
        assertTrue(!mainActivity.initMyDataList(listOf()))
    }

    @Test
    fun isOnlineTest() {
        val functions = mock(Functions::class.java)
        Mockito.`when`(functions.isOnline()).thenReturn(true)
        assertTrue(functions.isOnline())
    }

}


