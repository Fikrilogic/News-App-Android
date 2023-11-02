package com.fikrisandi.news.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fikrisandi.theme.R
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.AppShape

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider = EmptyNavigationProvider,
    viewModel: NewsListScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()
    val data = state.listNews.collectAsLazyPagingItems()

    val refreshState = rememberPullRefreshState(
        refreshing = viewModel.refresh,
        onRefresh = {
            viewModel.toggleRefresh(true)
            viewModel.getNews()
        })

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text("News", style = MaterialTheme.typography.titleLarge)
            },
            colors = TopAppBarColors(
                containerColor = AppColors.secondaryContainer,
                scrolledContainerColor = AppColors.secondaryContainer,
                navigationIconContentColor = AppColors.primary,
                titleContentColor = AppColors.scrim,
                actionIconContentColor = AppColors.primary
            )
        )
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (state.loading || data.loadState.refresh is LoadState.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics(mergeDescendants = true) {}
                    )
                }

            }
            when {
                data.loadState.refresh is LoadState.Error || data.loadState.append is LoadState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                    }
                }

                data.loadState.refresh is LoadState.NotLoading || data.loadState.append is LoadState.NotLoading -> {
                    when (data.itemCount) {
                        0 -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(), contentAlignment = Alignment.Center
                            ) {
                                Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                            }
                        }

                        else -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .pullRefresh(refreshState)
                            ) {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    contentPadding = PaddingValues(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                                ) {
                                    items(data.itemCount) { index ->
                                        val data = data[index]

                                        Card(shape = RoundedCornerShape(16.dp)) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                                    .clickable {
                                                        data?.let { news ->
                                                            navigationProvider.navigateToDetail(news)
                                                        }
                                                    },
                                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                AsyncImage(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .heightIn(min = 70.dp, max = 120.dp)
                                                        .clip(MaterialTheme.shapes.small)
                                                        .border(
                                                            1.dp,
                                                            color = AppColors.onBackground,
                                                            shape = AppShape.small
                                                        )
                                                        .background(AppColors.background)
                                                        .weight(.4f),
                                                    model = ImageRequest.Builder(context)
                                                        .error(R.drawable.ic_news)
                                                        .data(data?.urlToImage.orEmpty())
                                                        .crossfade(true)
                                                        .build(),
                                                    contentDescription = data?.title.orEmpty(),
                                                    contentScale = ContentScale.FillBounds
                                                )
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(.6f)
                                                ) {
                                                    Text(
                                                        data?.title.orEmpty(),
                                                        style = MaterialTheme.typography.titleSmall
                                                    )
                                                    Spacer(modifier = Modifier.height(8.dp))
                                                }
                                            }
                                        }
                                    }
                                }

                                PullRefreshIndicator(
                                    refreshing = viewModel.refresh,
                                    state = refreshState,
                                    modifier = Modifier.align(Alignment.TopCenter)
                                )
                            }
                        }
                    }

                }
            }

        }
    }
}