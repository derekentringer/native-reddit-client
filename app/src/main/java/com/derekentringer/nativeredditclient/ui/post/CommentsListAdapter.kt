package com.derekentringer.nativeredditclient.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.derekentringer.nativeredditclient.R
import com.derekentringer.nativeredditclient.databinding.ItemCommentBinding
import com.derekentringer.nativeredditclient.model.subreddit.comments.CommentDetails
import com.derekentringer.nativeredditclient.model.subreddit.comments.CommentList

class CommentsListAdapter: RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {

    private lateinit var commentsList: List<CommentList>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCommentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_comment, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentsList[position].data)
    }

    override fun getItemCount(): Int {
        return if(::commentsList.isInitialized) commentsList.size else 0
    }

    fun updateCommentList(postList: List<CommentList>) {
        this.commentsList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val item: ItemCommentBinding): RecyclerView.ViewHolder(item.root){
        private val viewModel = CommentItemViewModel()

        fun bind(post: CommentDetails){
            viewModel.bind(post)
            item.viewModel = viewModel
        }
    }
}