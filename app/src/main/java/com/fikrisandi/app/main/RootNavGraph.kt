package com.fikrisandi.app.main

import com.fikrisandi.home.HomeNavGraph
import com.fikrisandi.news.detail.NewsNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph: NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = emptyMap()
    override val route: String
        get() = "root"
    override val startRoute: Route
        get() = HomeNavGraph

    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        HomeNavGraph,
        NewsNavGraph
    )
}