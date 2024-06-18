package com.fikrisandi.users.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fikrisandi.domain.user.GetListUser
import com.fikrisandi.domain.user.GetUserByUsername
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(val getListUser: GetListUser, val getUserByUsername: GetUserByUsername) : MvvmViewModel()  {

    private val  _userState = MutableStateFlow(emptyList<User>())
    val userState = _userState.asStateFlow()

    var refresh by mutableStateOf(false)
        private set

    var searchUsername by mutableStateOf("")
        private set

    fun toggleRefresh(show: Boolean) {
        refresh = show
    }

    fun setSearch(input: String) {
        searchUsername = input
    }

    init {
        getUsers()
    }

    fun getUsers() = safeLaunch {
        getListUser(Unit)
            .onStart {
                refresh = true
            }
            .onCompletion { refresh = false }
            .collect{ users ->
            when(users){
                is ApiResult.Success -> {
                    _userState.update { users.data }
                }
                else -> {
                    _userState.update { emptyList() }
                }
            }
        }
    }

    fun searchUser() = safeLaunch {
        val param = GetUserByUsername.GetUserByUsernameInput(
            username = searchUsername
        )

        getUserByUsername(param)
            .onStart {
                refresh = true
            }
            .onCompletion { refresh = false }
            .collect{ user ->
                when(user){
                    is ApiResult.Success -> {
                        _userState.update { user.data.items.orEmpty()}
                    }
                    else -> {
                        _userState.update { emptyList() }
                    }
                }
            }
    }
}