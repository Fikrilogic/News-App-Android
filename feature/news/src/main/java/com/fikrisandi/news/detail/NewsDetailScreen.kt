package com.fikrisandi.news.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import com.fikrisandi.framework.extension.format
import com.fikrisandi.model.remote.news.News
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider,
    news: News? = null
) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text("News", style = MaterialTheme.typography.titleLarge) },
            navigationIcon = {
                IconButton(onClick = { navigationProvider.navigateBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = AppColors.primary
                    )
                }
            },
            colors = TopAppBarColors(
                containerColor = AppColors.secondaryContainer,
                scrolledContainerColor = AppColors.secondaryContainer,
                navigationIconContentColor = AppColors.primary,
                titleContentColor = AppColors.scrim,
                actionIconContentColor = AppColors.primary
            )
        )

    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                news?.let { data ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                                .border(
                                    border = BorderStroke(
                                        2.dp,
                                        MaterialTheme.colorScheme.secondary
                                    ), shape = MaterialTheme.shapes.medium
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = MaterialTheme.shapes.medium),
                                model = data.urlToImage,
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                                error = {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            "Something Error or Image Not Found",
                                            style = MaterialTheme.typography.headlineSmall
                                        )
                                    }
                                },
                                loading = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            )
                        }
                    }
                    item {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                data.title.orEmpty(),
                                style = MaterialTheme.typography.titleLarge
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = "Author:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = data.author.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = "Publish At:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                if (data.publishedAt != null) {
                                    Text(
                                        modifier = Modifier.weight(.4f),
                                        text = data.publishedAt!!.toInstant()
                                            .format("dd/MM/YYYY mm:ss"),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                } else {
                                    Text(
                                        modifier = Modifier.weight(.4f),
                                        text = "-",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Column(
                                modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Description:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    text = data.description.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Column(
                                modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Content:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    text = data.content.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Row {
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = "Link:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(.4f)
                                        .clickable {
                                            val intent = Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(data.url)
                                            )
                                            context.startActivity(intent)
                                        },
                                    text = "CLICK HERE",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Blue
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}