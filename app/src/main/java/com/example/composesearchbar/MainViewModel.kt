package com.example.composesearchbar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Name: MainViewModel
 * Author: lloydfinch
 * Function: MainViewModel
 * Date: 2021/6/8 3:23 下午
 * Modify: lloydfinch 2021/6/8 3:23 下午
 */
class MainViewModel : ViewModel(), ContentsListData {
    // the total data
    var totalDatas = MutableLiveData<MutableList<String>>()

    // display data
    val displayDatas = MutableLiveData<MutableList<String>>()

    init {
        // add test data
        val totalList = mutableListOf<String>()
        for (index in 1..100) {
            totalList.add("content $index")
        }
        val displayList = mutableListOf<String>()
        totalList.forEach { displayList.add(it) }


        totalDatas.value = totalList
        displayDatas.value = displayList

    }

    fun filter(keyword: String) {

        val list: MutableList<String> = mutableListOf()
        if (keyword.isEmpty()) {
            // show all data
            totalDatas.value?.forEach { list.add(it) }
        } else {
            // only show data which contains keyword
            totalDatas.value?.forEach { if (it.contains(keyword)) list.add(it) }
        }
        displayDatas.postValue(list)
    }

    override fun getDisplayData(): MutableLiveData<MutableList<String>> {
        return displayDatas
    }
}