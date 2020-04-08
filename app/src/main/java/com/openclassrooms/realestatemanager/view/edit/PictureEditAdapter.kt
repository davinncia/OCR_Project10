package com.openclassrooms.realestatemanager.view.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R

class PictureEditAdapter(private val listener: DeleteButtonClickListener) : RecyclerView.Adapter<PictureEditAdapter.PictureEditViewHolder>() {

    // TODO LUCAS listOf() suffit !
    private var picUris = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureEditViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_picture_edit, parent, false)
        return PictureEditViewHolder(itemView)
    }

    override fun getItemCount(): Int = picUris.size

    override fun onBindViewHolder(holder: PictureEditViewHolder, position: Int) {

        val currentItem = picUris[position]
        holder.bindView(currentItem)

    }

    fun populateData(uris: List<String>) {
        // TODO LUCAS Pas besoin de wrapper dans une ArrayList,
        //  expose toujours une liste immutable pour ne pas avoir de problème avec le ListAdapter
        picUris = ArrayList(uris)
        notifyDataSetChanged()
    }

    inner class PictureEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.iv_picture_edit_item)
        private val deleteButton = itemView.findViewById<ImageButton>(R.id.ib_delete_picture_edit_item)

        init {
            deleteButton.setOnClickListener {
                listener.onDeleteButtonClick(picUris[adapterPosition], adapterPosition)
            }
        }

        fun bindView(uri: String) {
            Glide.with(imageView.context).load(uri).into(imageView)
        }

    }

    interface DeleteButtonClickListener {
        fun onDeleteButtonClick(pictureUri: String, position: Int)
    }
}