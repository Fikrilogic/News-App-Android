package com.fikrisandi.domain.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.model.remote.news.News
import com.fikrisandi.repository.repository.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListNews @Inject constructor(val repository: NewsRepository) :
    FlowPagingUseCase<GetListNews.Params, News>() {
    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<News>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                NewsPagingSource(repository, params.options)
            }
        ).flow
    }
}