package com.fikrisandi.provider

import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.remote.news.News

interface NavigationProvider {
    fun navigateBack()


    fun navigateToDetail(news: News, option: NavOptionsBuilder.() -> Unit = {})
}

object EmptyNavigationProvider: NavigationProvider{
    override fun navigateBack() {
        TODO("Not yet implemented")
    }

    override fun navigateToDetail(news: News, option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }


}