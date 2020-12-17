package net.merayen.kitchentimer.fragments.tabs.workbench

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.fragments.tabs.workbench.RunningTimersListFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_running_timers_list.view.*
import net.merayen.kitchentimer.livedata.RunningTimerWithTask

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRunningTimersListRecyclerViewAdapter(
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyRunningTimersListRecyclerViewAdapter.ViewHolder>() {
    private val mOnClickListener: View.OnClickListener
    private var items: List<RunningTimerWithTask> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as RunningTimerWithTask
            mListener?.onListFragmentInteraction(item.runningTimer.runningTimerId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_running_timers_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val remaining = item.runningTimer.remaining
        holder.mContentView.text = item.task?.name ?: ""
        holder.mTimeView.text = "${remaining / 3600}h ${remaining / 60 % 60}m ${remaining % 60}s"

        if (position > 5)
            holder.mView.alpha = 0.5f
        else
            holder.mView.setBackgroundColor(
                if (item.runningTimer.remaining > 0) Color.YELLOW else Color.RED
            )

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content
        val mTimeView: TextView = mView.time

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    fun setData(items: List<RunningTimerWithTask>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateTimes() {
        notifyDataSetChanged()
    }
}
