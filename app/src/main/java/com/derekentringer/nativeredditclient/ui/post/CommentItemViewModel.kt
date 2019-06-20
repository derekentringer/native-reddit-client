package com.derekentringer.nativeredditclient.ui.post

import androidx.lifecycle.MutableLiveData
import com.derekentringer.nativeredditclient.model.subreddit.comments.CommentDetails
import com.derekentringer.nativeredditclient.ui.BaseViewModel

class CommentItemViewModel: BaseViewModel() {

    private val commentAuthor = MutableLiveData<String>()
    private val commentBody = MutableLiveData<String>()

    fun bind(comment: CommentDetails) {
        commentAuthor.value = comment.author
        commentBody.value = comment.body
    }

    fun getAuthor(): MutableLiveData<String> {
        return commentAuthor
    }

    fun getComment(): MutableLiveData<String> {
        return commentBody
    }
}