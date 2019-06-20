package com.derekentringer.nativeredditclient.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.derekentringer.nativeredditclient.model.subreddit.comments.Comments
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails
import com.derekentringer.nativeredditclient.network.SubredditApi
import com.derekentringer.nativeredditclient.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostDetailsViewModel: BaseViewModel() {

    @Inject
    lateinit var subredditApi: SubredditApi

    private lateinit var subscription: Disposable

    private val postId = MutableLiveData<String>()
    private val postTitle = MutableLiveData<String>()
    private val postImage = MutableLiveData<String>()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val commentsListAdapter: CommentsListAdapter = CommentsListAdapter()

    fun bind(post: PostDetails) {
        postId.value = post.id
        postTitle.value = post.title
        postImage.value = post.url

        loadComments()
    }

    private fun loadComments() {
        subscription = subredditApi.getComments(postId.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onGetCommentsStart() }
            .doOnTerminate { onGetCommentsFinish() }
            .doOnError {  error -> System.err.println("subredditApi error: " + error.message) }
            .subscribe { result -> onGetCommentsSuccess(result) }
    }

    private fun onGetCommentsStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onGetCommentsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onGetCommentsSuccess(comments: List<Comments>){
        commentsListAdapter.updateCommentList(comments[1].data.children)
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostImage(): MutableLiveData<String> {
        return postImage
    }
}