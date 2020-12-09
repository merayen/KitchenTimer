package net.merayen.kitchentimer.fragments.tabs.quicktimers

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.QuickTimersTabViewModel
import kotlin.math.max

class QuickTimersTab : Fragment() {
    private val viewModel by viewModels<QuickTimersTabViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.quicktimers_tab_fragment, container, false)

        val addHour = view.findViewById<Button>(R.id.add_hour)
        addHour.setOnClickListener {
            offsetSeconds(3600)
        }

        val addMinute = view.findViewById<Button>(R.id.add_minute)
        addMinute.setOnClickListener {
            offsetSeconds(60)
        }

        val addMinutes = view.findViewById<Button>(R.id.add_minutes)
        addMinutes.setOnClickListener {
            offsetSeconds(60 * 5)
        }

        val subHour = view.findViewById<Button>(R.id.sub_hour)
        subHour.setOnClickListener {
            offsetSeconds(-3600)
        }

        val subMinute = view.findViewById<Button>(R.id.sub_minute)
        subMinute.setOnClickListener {
            offsetSeconds(-60)
        }

        val subMinutes = view.findViewById<Button>(R.id.sub_minutes)
        subMinutes.setOnClickListener {
            offsetSeconds(-60 * 5)
        }

        val start = view.findViewById<Button>(R.id.start)
        start.setOnClickListener {
            viewModel.createQuickTimer()
        }

        offsetSeconds(0, view)

        createPresets(view)

        return view
    }

    private fun offsetSeconds(offset: Int, view: View? = this.view) {
        view!!

        viewModel.seconds = max(60, viewModel.seconds + offset)

        val seconds = viewModel.seconds
        view.findViewById<TextView>(R.id.hours)?.text = "${seconds / 3600}h"
        view.findViewById<TextView>(R.id.minutes)?.text = "${seconds / 60 % 60}m"
    }

    private fun createPresets(view: View) {
        val presets = view.findViewById<LinearLayout>(R.id.presets)
        listOf(
            60 * 10,
            60 * 15,
            60 * 20,
            60 * 25,
            60 * 30,
            60 * 40,
            60 * 50,
            60 * 30 * 3,
            60 * 30 * 5,
            60 * 30 * 7,
            60 * 30 * 9,
            60 * 30 * 11,
            60 * 30 * 13,
            60 * 30 * 15,
            60 * 30 * 17,
            60 * 30 * 19,
            60 * 30 * 21,
        ).map { seconds ->
            val something = TextView(context)
            something.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            something.height = 50 // TODO ugh, how to make this auto?
            //something.setAutoSizeTextTypeWithDefaults(AUTO_SIZE_TEXT_TYPE_UNIFORM)
            something.setAutoSizeTextTypeUniformWithConfiguration(10, 100, 3, TypedValue.COMPLEX_UNIT_PX)
            something.text = formatDuration(seconds)
            something.setOnClickListener {
                viewModel.seconds = 0
                offsetSeconds(seconds)
            }
            presets.addView(something)
        }
    }

    private fun formatDuration(seconds: Int) = "${seconds / 3600}h ${seconds / 60 % 60}m"
}
