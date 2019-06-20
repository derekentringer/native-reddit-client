package com.derekentringer.nativeredditclient.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derekentringer.nativeredditclient.R
import com.derekentringer.nativeredditclient.databinding.ActivityPostDetailsBinding
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails

class PostDetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailsBinding
    private lateinit var viewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var postDetailsFromIntent = intent.getSerializableExtra("POST_DETAILS") as PostDetails

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_details)
        binding.commentListRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.bind(postDetailsFromIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
