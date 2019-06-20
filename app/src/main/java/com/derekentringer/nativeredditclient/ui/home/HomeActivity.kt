package com.derekentringer.nativeredditclient.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derekentringer.nativeredditclient.R
import com.derekentringer.nativeredditclient.databinding.ActivityHomeBinding
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails
import com.derekentringer.nativeredditclient.ui.post.PostDetailsActivity
import com.google.android.material.snackbar.Snackbar

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    private var errorLoadingPostsSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.postListRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) displayError(errorMessage) else hideError()
        })

        viewModel.selectedPost.observe(this, Observer {
            selectedPost -> goToActivityPost(selectedPost)
        })
    }

    private fun displayError(@StringRes errorMessage: Int) {
        errorLoadingPostsSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorLoadingPostsSnackbar?.setAction(R.string.refresh, viewModel.errorClickListener)
        errorLoadingPostsSnackbar?.show()
    }

    private fun hideError() {
        errorLoadingPostsSnackbar?.dismiss()
    }

    private fun goToActivityPost(postDetails: PostDetails) {
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra("POST_DETAILS", postDetails)
        startActivity(intent)
    }
}