package com.fikrisandi.domain.user

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.usecase.NetworkUseCase
import com.fikrisandi.model.remote.user.User
import com.fikrisandi.model.remote.user.UsersSearch
import com.fikrisandi.repository.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetUserByUsername @Inject constructor(private val repository: UserRepository): NetworkUseCase<GetUserByUsername.GetUserByUsernameInput, UsersSearch>() {

    data class GetUserByUsernameInput(
        val username: String
    )

    override suspend fun FlowCollector<ApiResult<UsersSearch>>.execute(params: GetUserByUsernameInput) {
        emit(repository.getByUsername(params.username))
    }
}