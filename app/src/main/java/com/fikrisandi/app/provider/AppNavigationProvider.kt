package com.fikrisandi.app.provider

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.remote.news.News
import com.fikrisandi.news.detail.destinations.NewsDetailScreenDestination
import com.fikrisandi.provider.NavigationProvider
import com.ramcosta.composedestinations.navigation.navigate


class AppNavigationProvider(private val navController: NavController) : NavigationProvider {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToDetail(news: News, option: NavOptionsBuilder.() -> Unit) {
        navController.navigate(NewsDetailScreenDestination.invoke(news = news))
    }


}