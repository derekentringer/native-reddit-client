package com.derekentringer.nativeredditclient.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.derekentringer.nativeredditclient.util.extensions.getParentActivity
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?: "" })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String) {
    Glide
        .with(view.context)
        .load(url)
        .apply(
            RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(SIZE_ORIGINAL)
        )
        .into(view)
}