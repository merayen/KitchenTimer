package net.merayen.kitchentimer.fragments.tabs.kitchenlayout

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.databinding.KitchenLayoutTabFragmentBinding
import net.merayen.kitchentimer.viewmodels.KitchenLayoutTabViewModel

class KitchenLayoutTab : Fragment() {
	private val viewModel by viewModels<KitchenLayoutTabViewModel>()

	private lateinit var binding: KitchenLayoutTabFragmentBinding

	private var selected: Int = -1

	private val WIDTH = 16
	private val HEIGHT = 16

	private val COLORS = listOf(Color.LTGRAY, Color.GRAY, Color.DKGRAY)

	private val items = (0 until WIDTH * HEIGHT).map { 0 }.toIntArray()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = KitchenLayoutTabFragmentBinding.inflate(inflater, container, false)
		for (y in 0 until HEIGHT) {
			val row = TableRow(context)
			row.layoutParams = TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT,
				1f
			)
			for (x in 0 until WIDTH) {
				val button = Button(context)
				button.layoutParams = TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT,
					TableRow.LayoutParams.MATCH_PARENT
					//10,10
				)
				//text.text = "?"
				button.setBackgroundColor(Color.WHITE)
				button.setOnClickListener {
					selectBlock(x, y)
				}
				row.addView(button)
			}
			binding.grid.addView(row)
		}
		val view = binding.root

		// Color buttons
		for ((i, c) in COLORS.withIndex()) {
			val button = Button(context)
			button.setBackgroundColor(c)
			button.setOnClickListener {
				if (selected > -1) {
					items[selected] = i
					binding.colorbuttons.visibility = View.INVISIBLE
					update()
				}
			}
			binding.colorbuttons.addView(button)
		}

		update()

		return view
	}

	private fun update() {
		for ((i,block) in iterateBlocks().withIndex()) {
			block.setBackgroundColor(COLORS[items[i]])
			block.text = ""
		}
	}

	private fun selectBlock(selectedX: Int, selectedY: Int) {
		binding.colorbuttons.visibility = View.VISIBLE
		for ((i, block) in iterateBlocks().withIndex())
			block.text = if (i % WIDTH == selectedX && i / WIDTH == selectedY) "O" else ""
		selected = selectedX + selectedY * WIDTH
	}

	private fun iterateBlocks(): List<Button> {
		val result = ArrayList<Button>()
		for ((y, row) in binding.grid.iterator().withIndex())
			for ((x, column) in (row as TableRow).iterator().withIndex())
				result.add(column as Button)
		return result
	}
}