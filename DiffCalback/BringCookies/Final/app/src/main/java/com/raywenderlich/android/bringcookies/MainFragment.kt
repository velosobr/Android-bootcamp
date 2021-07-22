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

package com.raywenderlich.android.bringcookies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.raywenderlich.android.bringcookies.adapters.ItemsDetailsLookup
import com.raywenderlich.android.bringcookies.adapters.ItemsKeyProvider
import com.raywenderlich.android.bringcookies.adapters.MainAdapter
import com.raywenderlich.android.bringcookies.databinding.FragmentMainBinding
import com.raywenderlich.android.bringcookies.model.Item

class MainFragment : Fragment(), ActionMode.Callback {

  private lateinit var binding: FragmentMainBinding
  private lateinit var tracker: SelectionTracker<Long>

  private var actionMode: ActionMode? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
    binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupToolbar()
    setupUiComponents()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_main, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_shuffle) {
      val adapter = binding.rvGroceries.adapter as MainAdapter

      val items = adapter.currentList.toMutableList()
      if (items.size > 3) {
        items.subList(1, items.size).shuffle()
        adapter.submitList(items)
      } else {
        Snackbar.make(binding.clContainer, R.string.item_add_more, Snackbar.LENGTH_SHORT).show()
      }

      return true
    }

    return false
  }

  private fun setupToolbar() {
    val appCompatActivity = activity as AppCompatActivity
    appCompatActivity.setSupportActionBar(binding.toolbar)
    appCompatActivity.setTitle(R.string.app_header)

    setHasOptionsMenu(true)
  }

  private fun setupUiComponents() {
    val mainAdapter = MainAdapter { items: MutableList<Item>, changed: Item, isChecked: Boolean ->

      var element = items.first { it.timeStamp == changed.timeStamp }
      val index = items.indexOf(element)

      element = if (index == 0) {
        Snackbar.make(binding.clContainer, R.string.item_more_cookies, Snackbar.LENGTH_SHORT).show()
        element.copy(done = false)

      } else {
        element.copy(done = isChecked)
      }

      items[index] = element
      updateAndSave(items)
    }

    binding.rvGroceries.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = mainAdapter
    }

    binding.etNewItem.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
        // Do nothing
      }

      override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        // Do nothing
      }

      override fun afterTextChanged(text: Editable?) {
        binding.ivAddToCart.isEnabled = text.toString().isNotEmpty()
      }
    })

    binding.ivAddToCart.isEnabled = false
    binding.ivAddToCart.setOnClickListener {
      val list = mainAdapter.currentList.toMutableList()
      list.add(Item(
          list.size,
          binding.etNewItem.text.toString(),
          System.currentTimeMillis(),
          false
      ))

      binding.etNewItem.text?.clear()

      updateAndSave(list)
    }

    tracker = SelectionTracker.Builder(
        getString(R.string.item_selection),
        binding.rvGroceries,
        ItemsKeyProvider(mainAdapter),
        ItemsDetailsLookup(binding.rvGroceries),
        StorageStrategy.createLongStorage()
    ).withSelectionPredicate(
        SelectionPredicates.createSelectAnything()
    ).build()

    tracker.addObserver(
        object : SelectionTracker.SelectionObserver<Long>() {
          override fun onSelectionChanged() {
            super.onSelectionChanged()

            if (actionMode == null) {
              val currentActivity = activity as MainActivity
              actionMode = currentActivity.startSupportActionMode(this@MainFragment)

              binding.etNewItem.clearFocus()
              binding.etNewItem.isEnabled = false
            }

            val items = tracker.selection.size()
            if (items > 0) {
              actionMode?.title = getString(R.string.action_selected, items)
            } else {
              actionMode?.finish()
            }
          }
        })

    mainAdapter.tracker = tracker
    mainAdapter.submitList(getGroceriesList(requireContext()))
  }

  private fun updateAndSave(list: List<Item>) {
    (binding.rvGroceries.adapter as MainAdapter).submitList(list)
    saveGroceriesList(requireContext(), list)
  }

  // region ActionMode

  override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
    mode?.menuInflater?.inflate(R.menu.menu_actions, menu)
    return true
  }

  override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = true

  override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
    return when (item!!.itemId) {
      R.id.action_delete -> {
        val mainAdapter = binding.rvGroceries.adapter as MainAdapter

        var selected = mainAdapter.currentList.filter {
          tracker.selection.contains(it.timeStamp)
        }

        val groceries = mainAdapter.currentList.toMutableList()
        if (groceries[0] == selected[0]) {
          Snackbar.make(binding.clContainer, R.string.item_prohibited, Snackbar.LENGTH_SHORT).show()
          selected = selected.subList(1, selected.size)
        }

        groceries.removeAll(selected)

        updateAndSave(groceries)
        actionMode?.finish()
        true
      }
      else -> {
        false
      }
    }
  }

  override fun onDestroyActionMode(mode: ActionMode?) {
    tracker.clearSelection()
    actionMode = null

    binding.etNewItem.isEnabled = true
  }

  // endregion
}