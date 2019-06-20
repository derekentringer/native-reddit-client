package com.derekentringer.nativeredditclient.network

import com.derekentringer.nativeredditclient.model.subreddit.comments.Comments
import com.derekentringer.nativeredditclient.model.subreddit.post.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SubredditApi {
    @GET("/r/aww/.json")
    fun getPosts(): Observable<Post>

    @GET("r/aww/comments/{id}/.json")
    fun getComments(@Path("id") id: String?): Observable<List<Comments>>
}