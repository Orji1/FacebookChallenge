package com.example.myapplication.data.dto

import com.example.myapplication.domain.model.Giphy
import com.google.gson.annotations.SerializedName

data class GiphyDto(
    @SerializedName("data")
    val data: List<Data>,
    val meta: Meta = Meta(),
    val pagination: Pagination = Pagination()
)

data class Data(
    val analytics: Analytics,
    @SerializedName("analytics_response_payload")
    val analyticsResponsePayload: String,
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    @SerializedName("content_url")
    val contentUrl: String,
    @SerializedName("embed_url")
    val embedUrl: String,
    val id: String,
    val images: Images,
    @SerializedName("import_datetime")
    val importDatetime: String,
    @SerializedName("is_sticker")
    val isSticker: Int,
    val rating: String,
    val slug: String,
    val source: String,
    @SerializedName("source_post_url")
    val sourcePostUrl: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    val title: String,
    @SerializedName("trending_datetime")
    val trendingDatetime: String,
    val type: String,
    val url: String,
    val user: User,
    val username: String
)

fun Data.toGiphy(): Giphy {
    return Giphy(
        title = title,
        url = url
    )
}