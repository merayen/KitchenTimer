package net.merayen.kitchentimer.fragments

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import net.merayen.kitchentimer.R


import net.merayen.kitchentimer.fragments.RunningTimersListFragment.OnListFragmentInteractionListener
import net.merayen.kitchentimer.fragments.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_running_timers_list.view.*
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRunningTimersListRecyclerViewAdapter(
    private val mValues: List<DummyItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyRunningTimersListRecyclerViewAdapter.ViewHolder>() {
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_running_timers_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.content

        if (position > 5)
            holder.mView.alpha = 0.5f
        else
            holder.mView.setBackgroundColor(arrayOf(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN)[Random.nextInt(4)])

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
