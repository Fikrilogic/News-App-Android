package com.fikrisandi.framework.usecase

import com.fikrisandi.framework.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class NetworkUseCase <in Params, ReturnType> where ReturnType : Any? {

    protected abstract suspend fun FlowCollector<ApiResult<ReturnType>>.execute(params: Params)

    suspend operator fun invoke(params: Params) = flow {
        execute(params)
    }.flowOn(Dispatchers.IO)
}