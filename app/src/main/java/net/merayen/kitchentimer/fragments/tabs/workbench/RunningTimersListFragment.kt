package net.merayen.kitchentimer.fragments.tabs.workbench

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.merayen.kitchentimer.R

import net.merayen.kitchentimer.viewmodels.RunningTimerViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RunningTimersListFragment.OnListFragmentInteractionListener] interface.
 */
class RunningTimersListFragment : Fragment() {
    private var columnCount = 1

    private val viewModel by viewModels<RunningTimerViewModel>()

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_running_timers_list_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    else -> GridLayoutManager(context, columnCount)
                }

                val adapter = MyRunningTimersListRecyclerViewAdapter(listener)
                this.adapter = adapter

                viewModel.getWithTasks().observe(this@RunningTimersListFragment.viewLifecycleOwner) {
                    adapter.setData(it)
                }

                fun update() {
                    adapter.updateTimes()
                    postDelayed({ update() }, 500)
                }

                update()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(runningTaskId: Int)
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RunningTimersListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
