package com.fikrisandi.repository.repository.user

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.call
import com.fikrisandi.model.remote.user.User
import com.fikrisandi.model.remote.user.UsersSearch
import io.ktor.http.HttpMethod
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val appHttpClient: AppHttpClient): UserRepository{
    override suspend fun getAll(): ApiResult<List<User>> {
        return try {

            val result = appHttpClient.call<List<User>>(
                route = "users",
                method = HttpMethod.Get,
//                params = mapOf(
//                    "apiKey" to BuildConfig.API_KEY,
//                    "page" to page.toString(),
//                    "pageSize" to pageSize.toString(),
//                    "country" to "us",
//                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            ApiResult.Failed(exception = e)
        }
    }

    override suspend fun getByUsername(username: String): ApiResult<UsersSearch> {
        return try {

            val result = appHttpClient.call<UsersSearch>(
                route = "search/users",
                method = HttpMethod.Get,
                params = mapOf(
                    "q" to username,
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            ApiResult.Failed(exception = e)
        }
    }
}