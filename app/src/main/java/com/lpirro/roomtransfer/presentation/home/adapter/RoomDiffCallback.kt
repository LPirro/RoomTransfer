package com.lpirro.roomtransfer.presentation.home.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.lpirro.roomtransfer.domain.model.Room

class RoomDiffCallback(
    private val oldList: List<Room>,
    private val newList: List<Room>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldCourse: Int, newPosition: Int): Boolean {
        return oldList[oldCourse].name == newList[newPosition].name
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
