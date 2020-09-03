package net.merayen.kitchentimer.fragments

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.merayen.kitchentimer.R


import net.merayen.kitchentimer.fragments.RunningTimersListFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_running_timers_list.view.*
import net.merayen.kitchentimer.livedata.RunningTaskData
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRunningTimersListRecyclerViewAdapter(
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyRunningTimersListRecyclerViewAdapter.ViewHolder>() {
    private val mOnClickListener: View.OnClickListener
    private var items: List<RunningTaskData> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as RunningTaskData
            mListener?.onListFragmentInteraction(item.runningTaskId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_running_timers_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.mContentView.text = item.taskName

        if (position > 5)
            holder.mView.alpha = 0.5f
        else
            holder.mView.setBackgroundColor(arrayOf(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN)[Random.nextInt(4)])

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    fun setItems(items: List<RunningTaskData>) {
        this.items = items
        notifyDataSetChanged()
    }
}
