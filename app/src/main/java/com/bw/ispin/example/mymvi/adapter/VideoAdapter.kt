package com.bw.ispin.example.mymvi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bw.ispin.databinding.Item1Binding
import com.bw.ispin.example.mymvi.entity.VideoData

class VideoAdapter(val context:Context, var data:List<VideoData>,val clickListener: ClickListener):RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {
    lateinit var binding:Item1Binding
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title = binding.newsTitle
        val image = binding.newsIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      binding = Item1Binding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var video=data.get(position)
        holder.title.text = video.title
        Glide.with(context).load(video.videomainimag)
            .apply(RequestOptions().transform(CenterCrop(),RoundedCorners(20))).into(holder.image)
        holder.itemView.setOnClickListener{
            clickListener.onClick(position,video)

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
    interface ClickListener{
        fun onClick(position: Int,entity:VideoData)
    }

}