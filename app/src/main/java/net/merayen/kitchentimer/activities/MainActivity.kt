package net.merayen.kitchentimer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isEmpty
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_activity.*
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.fragments.*
import net.merayen.kitchentimer.fragments.dummy.DummyContent
import java.lang.RuntimeException

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity(), RunningTimersListFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        supportActionBar?.hide()

        // TODO move out of this onCreate
        val tabs = findViewById<TabLayout>(R.id.tabs)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null)
                    selectTab(tab.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null)
                    selectTab(tab.position)
            }
        })

        selectTab(0)
    }

    private fun selectTab(position: Int) {
        supportFragmentManager.commit {
            val tabContent = findViewById<FrameLayout>(R.id.tabContent)

            val newTab = when (position) {
                0 -> WorkbenchTab::class.java
                1 -> ItemSetupTab::class.java
                2 -> TaskSetupTab::class.java
                3 -> StorageTab::class.java
                else -> throw RuntimeException()
            }

            if (!tabContent.isEmpty()) {
                tabContent.removeAllViews()
                replace(R.id.tabContent, newTab, intent.extras)
            } else {
                add(R.id.tabContent, newTab, intent.extras)
            }
        }
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        println("YOU TAPPED $item") // TODO should be captured by a sub fragment showing the running timers?
    }
}
