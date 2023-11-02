package com.fikrisandi.repository.repository.news

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.call
import com.fikrisandi.model.remote.news.NewsResponse
import com.fikrisandi.repository.BuildConfig
import io.ktor.http.HttpMethod
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(private val appHttpClient: AppHttpClient) :
    NewsRepository {
    override suspend fun getListNews(page: Int, pageSize: Int): ApiResult<NewsResponse> {
        return try {

            val result = appHttpClient.call<NewsResponse>(
                route = "top-headlines",
                method = HttpMethod.Get,
                params = mapOf(
                    "apiKey" to BuildConfig.API_KEY,
                    "page" to page.toString(),
                    "pageSize" to pageSize.toString(),
                    "country" to "us",
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            ApiResult.Failed(exception = e)
        }
    }
}