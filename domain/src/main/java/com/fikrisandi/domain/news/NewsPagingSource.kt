package com.fikrisandi.domain.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.news.News
import com.fikrisandi.repository.repository.news.NewsRepository

class NewsPagingSource(val repository: NewsRepository, val options: Map<String, Any>) :
    PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val page = params.key ?: 1
        val limit = (options["limit"] as? Int) ?: 10
        return try {
            when (val response = repository.getListNews(page, limit)) {
                is ApiResult.Success -> {

                    val data = response.data.articles.orEmpty()

                    LoadResult.Page(
                        data = data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                }

                is ApiResult.Failed -> {
                    LoadResult.Error(response.exception)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}