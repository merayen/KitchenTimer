package net.merayen.kitchentimer.fragments.tabs.kitchenlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.databinding.KitchenLayoutTabFragmentBinding
import net.merayen.kitchentimer.fragments.layout.COLORS
import net.merayen.kitchentimer.fragments.layout.LayoutFragment
import net.merayen.kitchentimer.viewmodels.KitchenLayoutTabViewModel

// TODO make the grid-part a separate fragment
class KitchenLayoutTab : Fragment() {
	private val viewModel by viewModels<KitchenLayoutTabViewModel>()

	private lateinit var binding: KitchenLayoutTabFragmentBinding

	private lateinit var layoutFragment: LayoutFragment

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = KitchenLayoutTabFragmentBinding.inflate(inflater, container, false)
		val view = binding.root

		// Color buttons
		for ((i, c) in COLORS.withIndex()) {
			val button = Button(context)
			button.setBackgroundColor(c)
			button.setOnClickListener {
				val selected = layoutFragment.selected
				if (selected != null) {
					selected.color = COLORS.indexOf(c)
					AppDatabase.getInstance().queryExecutor.execute {
						viewModel.layoutBlockRepository.set(selected)
					}
					binding.colorbuttons.visibility = View.INVISIBLE
				}
			}
			binding.colorbuttons.addView(button)
		}

		parentFragmentManager.commit {
			layoutFragment = LayoutFragment()
			add(binding.layout.id, layoutFragment)

			layoutFragment.handler = object : LayoutFragment.Handler {
				override fun onSelect() {
					binding.colorbuttons.visibility = View.VISIBLE
					// TODO remove "0" from all the other ones first?
					val selected = layoutFragment.selected
					if (selected != null) {
						selected.text = "0"

						AppDatabase.getInstance().queryExecutor.execute {
							viewModel.layoutBlockRepository.set(selected)
						}
					}
				}
			}
		}

		return view
	}
}