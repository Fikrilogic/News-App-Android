package com.fikrisandi.app.provider

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.remote.user.User
import com.fikrisandi.provider.NavigationProvider


class AppNavigationProvider(private val navController: NavController) : NavigationProvider {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToDetail(user: User, option: NavOptionsBuilder.() -> Unit) {
//        navController.navigate(NewsDetailScreenDestination.invoke(news = news))
    }


}