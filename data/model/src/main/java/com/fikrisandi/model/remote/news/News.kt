package com.fikrisandi.model.remote.news


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    @SerializedName("author")
    @Expose
    var author: String? = "",
    @SerializedName("content")
    @Expose
    var content: String? = "",
    @SerializedName("description")
    @Expose
    var description: String? = "",
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = "",
    @SerializedName("source")
    @Expose
    var source: Source? = Source(),
    @SerializedName("title")
    @Expose
    var title: String? = "",
    @SerializedName("url")
    @Expose
    var url: String? = "",
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = ""
): Parcelable {

    @Parcelize
    data class Source(
        @SerializedName("id")
        @Expose
        var id: String? = "",
        @SerializedName("name")
        @Expose
        var name: String? = ""
    ): Parcelable
}

data class NewsResponse(
    @SerializedName("status")
    @Expose
    var status: String? = "",
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = 0,
    @SerializedName("articles")
    @Expose
    var articles: List<News>? = emptyList(),
)