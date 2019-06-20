package com.derekentringer.nativeredditclient.ui.home

import androidx.lifecycle.MutableLiveData
import com.derekentringer.nativeredditclient.ui.BaseViewModel
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails

class PostItemViewModel: BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postThumbnail = MutableLiveData<String>()

    fun bind(post: PostDetails) {
        postTitle.value = post.title
        postThumbnail.value = post.thumbnail
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostThumbnail(): MutableLiveData<String> {
        return postThumbnail
    }
}