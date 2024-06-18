package com.fikrisandi.provider

import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.remote.user.User

interface NavigationProvider {
    fun navigateBack()


    fun navigateToDetail(user: User, option: NavOptionsBuilder.() -> Unit = {})
}

object EmptyNavigationProvider: NavigationProvider{
    override fun navigateBack() {
        TODO("Not yet implemented")
    }

    override fun navigateToDetail(user: User, option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }


}