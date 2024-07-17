package com.example.megamediatest

import com.example.megamediatest.data.filePojo
import com.example.megamediatest.presenter.ArticlePresenter
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
        MockitoAnnotations.openMocks(this)
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
        val articlePresenter = mock(ArticlePresenter::class.java)
        Mockito.`when`(articlePresenter.initMyDataList(listOf(), arrayListOf())).thenReturn(true)
        assertTrue(articlePresenter. initMyDataList(listOf(), arrayListOf()))
    }

    @Test
    fun initTemplateTest() {
        val articlePresenter = mock(ArticlePresenter::class.java)
        `when`(articlePresenter. initTemplate(fileList)).thenReturn(true)
        assertTrue(articlePresenter. initTemplate(fileList))
    }

    @Test
    fun initTemplateEmptyTest() {
        val articlePresenter = mock(ArticlePresenter::class.java)
        Mockito.`when`(articlePresenter.initTemplate(arrayListOf())).thenReturn(false)
        assertTrue(!articlePresenter.initTemplate(arrayListOf()))
    }

    @Test
    fun isOnlineTest() {
        val functions = mock(Functions::class.java)
        Mockito.`when`(functions.isOnline()).thenReturn(true)
        assertTrue(functions.isOnline())
    }

}


