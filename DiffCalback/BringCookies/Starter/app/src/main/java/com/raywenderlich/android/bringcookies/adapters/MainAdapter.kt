/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.bringcookies.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.bringcookies.R
import com.raywenderlich.android.bringcookies.databinding.ItemGroceryBinding
import com.raywenderlich.android.bringcookies.model.Cell

private class DiffCallback : DiffUtil.ItemCallback<Cell>() {
    override fun areItemsTheSame(oldItem: Cell, newItem: Cell) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cell, newItem: Cell) = oldItem == newItem
}

class MainAdapter(val action: (cells: MutableList<Cell>, changed: Cell, checked: Boolean) -> Unit) :
    ListAdapter<Cell, MainAdapter.ItemViewHolder>(AsyncDifferConfig.Builder(DiffCallback()).build()) {

    private lateinit var binding: ItemGroceryBinding

    var tracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemViewHolder {
        binding =
            ItemGroceryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int) {
        val item = currentList[pos]
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ItemViewHolder(private val itemBinding: ItemGroceryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cell: Cell) {
            itemBinding.cbItem.text = cell.value
            itemBinding.cbItem.setOnCheckedChangeListener(null)
            itemBinding.cbItem.isChecked = cell.done
            itemBinding.cbItem.setOnCheckedChangeListener { _, isChecked ->
                if (cell.id == 0) {
                    itemBinding.cbItem.isChecked = false
                    action(currentList.toMutableList(), cell, false)
                } else {
                    action(currentList.toMutableList(), cell, isChecked)
                }
            }

            setItemTextStyle(cell.done)

            tracker?.let {

                if (it.isSelected(cell.timeStamp)) {
                    itemBinding.llContainer.setBackgroundColor(
                        ContextCompat.getColor(
                            itemBinding.llContainer.context,
                            R.color.colorPrimary60
                        )
                    )
                } else {
                    itemBinding.llContainer.background = null
                }
            }
        }

        private fun setItemTextStyle(checked: Boolean) {
            val resources = itemBinding.cbItem.context.resources
            if (checked) {
                val color = ResourcesCompat.getColor(resources, R.color.colorDone, null)
                itemBinding.cbItem.setTextColor(color)
                itemBinding.cbItem.paintFlags =
                    itemBinding.cbItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                val color = ResourcesCompat.getColor(resources, R.color.colorActive, null)
                itemBinding.cbItem.setTextColor(color)
                itemBinding.cbItem.paintFlags =
                    itemBinding.cbItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        fun getItem(): ItemDetailsLookup.ItemDetails<Long> =

            object : ItemDetailsLookup.ItemDetails<Long>() {

                override fun getPosition(): Int = adapterPosition

                override fun getSelectionKey(): Long = getItem(adapterPosition).timeStamp
            }
    }
}
