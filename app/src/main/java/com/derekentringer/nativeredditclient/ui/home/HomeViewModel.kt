package com.derekentringer.nativeredditclient.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.derekentringer.nativeredditclient.R
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails
import com.derekentringer.nativeredditclient.ui.BaseViewModel
import com.derekentringer.nativeredditclient.model.subreddit.post.Post
import com.derekentringer.nativeredditclient.network.SubredditApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel: BaseViewModel() {

    @Inject
    lateinit var subredditApi: SubredditApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostListAdapter = PostListAdapter(::onPostClicked)
    val selectedPost: MutableLiveData<PostDetails> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = subredditApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onGetPostsStart() }
            .doOnTerminate { onGetPostsFinish() }
            .doOnError {  error -> System.err.println("subredditApi error: " + error.message) }
            .subscribe(
                { result -> onGetPostsSuccess(result) },
                { onGetPostsError() }
            )
    }

    private fun onGetPostsStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onGetPostsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onGetPostsSuccess(post: Post){
        postListAdapter.updatePostList(post.data.children)
    }

    private fun onGetPostsError() {
        errorMessage.value = R.string.error
    }

    private fun onPostClicked(postDetails: PostDetails) {
        selectedPost.value = postDetails
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}