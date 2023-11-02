package com.fikrisandi.repository.repository.news

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.news.NewsResponse

interface NewsRepository {
    suspend fun getListNews(page: Int, pageSize: Int): ApiResult<NewsResponse>
}