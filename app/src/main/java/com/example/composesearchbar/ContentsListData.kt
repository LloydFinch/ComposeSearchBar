package com.example.composesearchbar

import androidx.lifecycle.MutableLiveData

/**
 * Name: ContentsListData
 * Author: lloydfinch
 * Function: ContentsListData
 * Date: 2021/6/8 3:39 下午
 * Modify: lloydfinch 2021/6/8 3:39 下午
 */
interface ContentsListData {
    fun getDisplayData(): MutableLiveData<MutableList<String>>
}