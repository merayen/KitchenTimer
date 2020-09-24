package net.merayen.kitchentimer.fragments.tabs.quicktimers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.QuickTimersTabViewModel

class QuickTimersTab : Fragment() {
    private val viewModel by viewModels<QuickTimersTabViewModel>()

    class QuickTimersRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.quick_timers_list_item, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mView.findViewById<TextView>(R.id.name).text = "Test"
            println("Position: $position")
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.quicktimers_tab_fragment, container, false)

        val quickTimersList = view.findViewById<RecyclerView>(R.id.quick_timers_list)
        with(quickTimersList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = QuickTimersRecyclerViewAdapter()
        }

        return view
    }
}