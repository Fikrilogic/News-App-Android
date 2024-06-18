package com.fikrisandi.repository.repository.user

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.user.User
import com.fikrisandi.model.remote.user.UsersSearch

interface UserRepository {

    suspend fun getAll(): ApiResult<List<User>>
    suspend fun getByUsername(username: String): ApiResult<UsersSearch>
}