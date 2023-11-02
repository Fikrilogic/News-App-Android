package com.fikrisandi.home


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fikrisandi.news.list.NewsListScreen
import com.fikrisandi.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider
) {
    Scaffold {
        val modifier = modifier.padding(it)
        NewsListScreen(modifier = modifier, navigationProvider = navigationProvider)
    }
}