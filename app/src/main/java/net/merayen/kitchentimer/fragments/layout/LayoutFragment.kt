package net.merayen.kitchentimer.fragments.layout

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
import net.merayen.kitchentimer.data.LayoutBlock
import net.merayen.kitchentimer.databinding.LayoutFragmentBinding
import net.merayen.kitchentimer.viewmodels.LayoutViewModel

class LayoutFragment : Fragment() {
	interface Handler {
		/**
		 * User has clicked on a block.
		 */
		fun onSelect()
	}

	var handler: Handler? = null

	private val viewModel by viewModels<LayoutViewModel>()

	private lateinit var binding: LayoutFragmentBinding

	var selected: LayoutBlock? = null
		private set

	//private val items = (0 until WIDTH * HEIGHT).map { 0 }.toIntArray()
	private var items: List<LayoutBlock>? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = LayoutFragmentBinding.inflate(inflater, container, false)
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
				(button.layoutParams as TableRow.LayoutParams).setMargins(1, 1, 1, 1)
				button.setBackgroundColor(Color.WHITE)
				button.setOnClickListener {
					selectBlock(x, y)
				}
				row.addView(button)
			}
			binding.grid.addView(row)
		}

		// Oberserve the data
		viewModel.layoutRepository.get().observe(viewLifecycleOwner) {
			items = it
			for ((i, button) in iterateBlocks().withIndex()) {
				button.text = it[i].text
				button.setBackgroundColor(COLORS[it[i].color])
			}
		}
		return binding.root
	}

	private fun selectBlock(selectedX: Int, selectedY: Int) {
		selected = items?.get(selectedX + selectedY * WIDTH)
		handler?.onSelect()
	}

	private fun iterateBlocks(): List<Button> {
		val result = ArrayList<Button>()
		for ((y, row) in binding.grid.iterator().withIndex())
			for ((x, column) in (row as TableRow).iterator().withIndex())
				result.add(column as Button)
		return result
	}

	private fun update() {
		for ((i, block) in iterateBlocks().withIndex()) {
			block.setBackgroundColor(COLORS[items?.get(i)?.color ?: 0])
			block.text = items?.get(i)?.text ?: ""
		}
	}
}