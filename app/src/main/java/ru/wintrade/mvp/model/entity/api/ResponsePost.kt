package ru.wintrade.mvp.model.entity.api

import com.google.gson.annotations.Expose

data class ResponsePost(
    @Expose
    val id: Int,
    @Expose
    val trader_id: String,
    @Expose
    val text: String,
    @Expose
    val post_status: String,
    @Expose
    val date_create: String,
    @Expose
    val date_update: String?,
    @Expose
    val pinned: Boolean,
    @Expose
    val images: List<ResponseImage>,
    @Expose
    val like_count: Int,
    @Expose
    val dislike_count: Int,
    @Expose
    val is_liked: Boolean,
    @Expose
    val is_disliked: Boolean
)

