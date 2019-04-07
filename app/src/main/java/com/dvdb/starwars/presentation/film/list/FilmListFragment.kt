package com.dvdb.starwars.presentation.film.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvdb.starwars.R
import kotlinx.android.synthetic.main.fragment_film_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

private val TAG = FilmListFragment::class.java.simpleName

class FilmListFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: FilmListViewModelFactory by instance()
    private val _layoutInflater: LayoutInflater by instance()
    private lateinit var viewModel: FilmListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FilmListViewModel::class.java)
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FilmListRecyclerViewAdapter(requireContext(), _layoutInflater)
        }
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this,
            Observer<FilmListViewModelState> {
                when (it) {
                    is FilmListViewModelState.Loading -> {
                    }
                    is FilmListViewModelState.Success -> {
                        Log.d(TAG, "Number of film list items loaded: ${it.listItems.count()}")
                        (recycler_view.adapter as FilmListRecyclerViewAdapter).updateItems(it.listItems)
                    }
                    is FilmListViewModelState.Error -> {
                        Log.e(TAG, "Could not load film list items", it.throwable)
                    }
                }
            })
    }
}
