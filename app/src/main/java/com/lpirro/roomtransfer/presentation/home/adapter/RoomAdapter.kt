package com.lpirro.roomtransfer.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lpirro.roomtransfer.R
import com.lpirro.roomtransfer.databinding.ItemCardRoomBinding
import com.lpirro.roomtransfer.domain.model.Room

class RoomAdapter(
    var onRoomBookedClickListener: () -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    private val rooms = arrayListOf<Room>()

    fun setData(newRooms: List<Room>) {
        val diffCallback = RoomDiffCallback(rooms, newRooms)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        rooms.clear()
        rooms.addAll(newRooms)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RoomViewHolder(val binding: ItemCardRoomBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemCardRoomBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val context = holder.itemView.context
        val room = rooms[position]
        holder.binding.roomName.text = room.name
        holder.binding.bookRoomButton.setOnClickListener { onRoomBookedClickListener.invoke() }

        when (room.spots) {
            0 -> {
                holder.binding.roomSpots.text =
                    context.resources.getString(R.string.no_spots_available)
                holder.binding.bookRoomButton.isEnabled = false
                holder.binding.bookRoomButton.icon =
                    ContextCompat.getDrawable(context, R.drawable.ic_meeting_room_unavailable)
            }
            1 -> {
                holder.binding.roomSpots.text =
                    context.resources.getString(R.string.one_spot_available)
                holder.binding.bookRoomButton.isEnabled = true
            }
            else -> {
                holder.binding.roomSpots.text =
                    context.resources.getString(R.string.n_spots_available, room.spots)
                holder.binding.bookRoomButton.isEnabled = true
                holder.binding.bookRoomButton.icon =
                    ContextCompat.getDrawable(context, R.drawable.ic_meeting_room)
            }
        }

        val roundCornerSize =
            holder.itemView.context.resources.getDimensionPixelSize(R.dimen.room_image_corners)

        Glide.with(holder.itemView.context)
            .load(room.thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.meeting_room_placeholder)
            .placeholder(R.drawable.meeting_room_placeholder)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(roundCornerSize)))
            .centerCrop()
            .into(holder.binding.roomImage)
    }

    override fun getItemCount() = rooms.size
}
