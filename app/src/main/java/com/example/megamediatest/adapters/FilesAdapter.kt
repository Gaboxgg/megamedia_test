package com.example.megamediatest.adapters

import android.content.Context
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.megamediatest.R
import com.example.megamediatest.data.filePojo

class MyRecyclerViewAdapter internal constructor( context: Context?,
                                            private val mFiles: List<filePojo>
            ) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_file, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = mFiles[position]

        holder.myTextView.text = "${file.title}  \n \n  ${file.description} \n \n"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.myTextView.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        Glide.with(holder.itemView)
            .load(file.img)
            .transition(withCrossFade())
            .into(holder.myImageView)


        val uri: Uri = Uri.parse(file.url)
        holder.myVideoView.setVideoURI(uri)

        val mediaController = MediaController(holder.itemView.context)
        mediaController.setAnchorView(holder.myVideoView)
        mediaController.setMediaPlayer(holder.myVideoView)

        holder.myVideoView.setMediaController(mediaController)
    }

    override fun getItemCount(): Int {
        return mFiles.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var myTextView: TextView = itemView.findViewById<TextView>(R.id.text)
        var myImageView: ImageView = itemView.findViewById<ImageView>(R.id.image)
        var myVideoView: VideoView = itemView.findViewById<VideoView>(R.id.videoView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}