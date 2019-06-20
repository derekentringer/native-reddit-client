package com.derekentringer.nativeredditclient.model.subreddit.post

import java.io.Serializable

data class PostDetails (
    val author: String,
    val title: String,
    val id: String,
    val thumbnail: String,
    val url: String
): Serializable