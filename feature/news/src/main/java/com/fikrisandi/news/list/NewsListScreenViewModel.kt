package com.fikrisandi.news.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fikrisandi.domain.news.GetListNews
import com.fikrisandi.framework.base.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsListScreenViewModel @Inject constructor(val getListNews: GetListNews) : MvvmViewModel() {

    private val _uiState = MutableStateFlow(NewsListScreenState())
    val uiState = _uiState.asStateFlow()

    private val config = PagingConfig(pageSize = 5, initialLoadSize = 10, prefetchDistance = 10)

    var refresh by mutableStateOf(false)
        private set

    init {
        getNews()
    }

    fun toggleRefresh(show: Boolean) {
        refresh = show
    }

    fun getNews() = safeLaunch {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        val params = GetListNews.Params(
            pagingConfig = config,
            options = mapOf(
                "limit" to config.pageSize
            )
        )
        val data = getListNews(params).cachedIn(viewModelScope)
        delay(2000)
        _uiState.update {
            it.copy(
                loading = false,
                exception = null,
                listNews = data
            )
        }.also {
            toggleRefresh(false)
        }
    }
}