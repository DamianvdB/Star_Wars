package com.dvdb.starwars.presentation.film

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dvdb.starwars.R
import com.dvdb.starwars.presentation.film.detail.FilmDetailFragment
import com.dvdb.starwars.presentation.film.list.FilmListFragment
import com.dvdb.starwars.presentation.film.list.FilmListSharedViewModel
import com.dvdb.starwars.presentation.util.NavigationManager
import kotlinx.android.synthetic.main.activity_film.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

private val FRAGMENT_LIST_TAG = FilmListFragment::class.java.simpleName
private val FRAGMENT_DETAIL_TAG = FilmDetailFragment::class.java.simpleName

class FilmActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val navigationManagerFactory: NavigationManager.Factory by instance()
    private val navigationManager: NavigationManager by lazy { navigationManagerFactory.create(supportFragmentManager) }
    private val sharedViewModel: FilmListSharedViewModel by lazy { ViewModelProviders.of(this).get(FilmListSharedViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        initActionBar(savedInstanceState)
        addFilmListFragment()
        observeViewModel()
    }

    private fun initActionBar(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        setTitle(R.string.films)
        if (savedInstanceState != null && navigationManager.isFragmentWithTagPresent(FRAGMENT_DETAIL_TAG)) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun addFilmListFragment() {
        if (navigationManager.isFragmentsEmpty()) {
            navigationManager.addFragment(frame_layout.id, navigationManager.getFilmListFragment(), FRAGMENT_LIST_TAG)
        }
    }

    private fun observeViewModel() {
        sharedViewModel.data.observe(this, Observer {
            it?.let { filmTitle ->
                if (!navigationManager.isFragmentWithTagPresent(FRAGMENT_DETAIL_TAG)) {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    navigationManager.replaceFragment(frame_layout.id, navigationManager.getFilmDetailFragment(filmTitle), FRAGMENT_DETAIL_TAG)
                    sharedViewModel.data.value = null
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (!navigationManager.isFragmentWithTagPresent(FRAGMENT_LIST_TAG)) {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    navigationManager.replaceFragment(frame_layout.id, navigationManager.getFilmListFragment(), FRAGMENT_LIST_TAG)
                } else {
                    super.onBackPressed()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
