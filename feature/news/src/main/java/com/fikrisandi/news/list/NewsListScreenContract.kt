package com.fikrisandi.news.list

import androidx.paging.PagingData
import com.fikrisandi.model.remote.news.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewsListScreenState(
    val listNews: Flow<PagingData<News>> = emptyFlow(),
    val loading: Boolean = false,
    val exception: Throwable? = null
)