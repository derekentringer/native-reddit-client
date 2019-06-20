package com.derekentringer.nativeredditclient.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.derekentringer.nativeredditclient.R
import com.derekentringer.nativeredditclient.databinding.ItemPostBinding
import com.derekentringer.nativeredditclient.model.subreddit.post.PostList
import com.derekentringer.nativeredditclient.model.subreddit.post.PostDetails

class PostListAdapter(val onPostClicked: (postDetails: PostDetails) -> Unit): RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private lateinit var postList: List<PostList>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position].data)
        holder.itemView.setOnClickListener { onPostClicked(postList[position].data) }
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<PostList>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val item: ItemPostBinding): RecyclerView.ViewHolder(item.root){
        private val viewModel = PostItemViewModel()

        fun bind(post: PostDetails){
            viewModel.bind(post)
            item.viewModel = viewModel
        }
    }
}