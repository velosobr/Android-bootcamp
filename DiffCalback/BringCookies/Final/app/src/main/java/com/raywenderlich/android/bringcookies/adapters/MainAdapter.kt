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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.bringcookies.R
import com.raywenderlich.android.bringcookies.databinding.ItemGroceryBinding
import com.raywenderlich.android.bringcookies.model.Item
import java.util.Collections.emptyList


private const val ARG_DONE = "arg.done"

class MainAdapter(val action: (items: MutableList<Item>, changed: Item, checked: Boolean) -> Unit) :
    ListAdapter<Item, MainAdapter.ItemViewHolder>(DiffCallback()) {

  private lateinit var binding: ItemGroceryBinding
  var tracker: SelectionTracker<Long>? = null

  override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemViewHolder {
    binding = ItemGroceryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
    return ItemViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, pos: Int) {
    onBindViewHolder(holder, pos, emptyList())
  }

  override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int, payload: List<Any>) {
    val item = getItem(pos)

    if (payload.isEmpty() || payload[0] !is Bundle) {
      viewHolder.bind(item)
    } else {
      val bundle = payload[0] as Bundle
      viewHolder.update(bundle)
    }
  }

  override fun getItemCount(): Int {
    return currentList.size
  }

  private class DiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        oldItem == newItem

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
      if (oldItem.id == newItem.id) {
        return if (oldItem.done == newItem.done) {
          super.getChangePayload(oldItem, newItem)
        } else {
          val diff = Bundle()
          diff.putBoolean(ARG_DONE, newItem.done)
          diff
        }
      }

      return super.getChangePayload(oldItem, newItem)
    }
  }

  inner class ItemViewHolder(private val itemBinding: ItemGroceryBinding) :
      RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Item) {
      itemBinding.cbItem.text = item.value
      itemBinding.cbItem.setOnCheckedChangeListener(null)
      itemBinding.cbItem.isChecked = item.done
      itemBinding.cbItem.setOnCheckedChangeListener { _, isChecked ->
        if (item.id == 0) {
          itemBinding.cbItem.isChecked = false
          action(currentList.toMutableList(), item, false)

        } else {
          action(currentList.toMutableList(), item, isChecked)
        }
      }

      setItemTextStyle(item.done)

      tracker?.let {

        if (it.isSelected(item.timeStamp)) {
          itemBinding.llContainer.setBackgroundColor(
              ContextCompat.getColor(itemBinding.llContainer.context, R.color.colorPrimary60))
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

    fun update(bundle: Bundle) {
      if (bundle.containsKey(ARG_DONE)) {
        val checked = bundle.getBoolean(ARG_DONE)
        itemBinding.cbItem.isChecked = checked
        setItemTextStyle(checked)
      }
    }

    fun getItem(): ItemDetailsLookup.ItemDetails<Long> =

        object : ItemDetailsLookup.ItemDetails<Long>() {

          override fun getPosition(): Int = adapterPosition

          override fun getSelectionKey(): Long = getItem(adapterPosition).timeStamp
        }
  }
}