package com.fikrisandi.domain.user

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.usecase.NetworkUseCase
import com.fikrisandi.model.remote.user.User
import com.fikrisandi.repository.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetListUser @Inject constructor(private val repository: UserRepository): NetworkUseCase<Unit, List<User>>() {
    override suspend fun FlowCollector<ApiResult<List<User>>>.execute(params: Unit) {
        emit(repository.getAll())
    }
}