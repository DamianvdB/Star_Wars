package com.dvdb.starwars.presentation.film.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dvdb.starwars.R
import com.dvdb.starwars.common.model.FilmDetail
import kotlinx.android.synthetic.main.fragment_film_detail.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.factory

const val FILM_TITLE_BUNDLE_KEY = "film_title"
private val TAG = FilmDetailFragment::class.java.simpleName

class FilmDetailFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ((String) -> FilmDetailViewModelFactory) by factory()
    private val viewModel: FilmDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory(arguments!!.getString(FILM_TITLE_BUNDLE_KEY)!!))
            .get(FilmDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewModel.updateFilmDetail()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, Observer<FilmDetailViewModelState> {
            when (it) {
                is FilmDetailViewModelState.Loading -> {
                    layout_detail_refresh.isRefreshing = it.isLoading
                }
                is FilmDetailViewModelState.Success -> {
                    text_detail_title.text = it.filmDetail.title
                    text_detail_characters.text = it.filmDetail.characters.joinToString(separator = ", ")
                    text_detail_opening_crawl.text = it.filmDetail.openingCrawl
                    text_detail_release_date.text = it.filmDetail.releaseDate
                    updateStarRating(it.filmDetail)
                }
                is FilmDetailViewModelState.Error -> {
                    Log.e(TAG, "Could not load film detail", it.throwable)
                }
            }
        })
    }

    private fun updateStarRating(filmDetail: FilmDetail) {
        val imageViews = listOf<ImageView>(
            image_detail_rating_1, image_detail_rating_2, image_detail_rating_3, image_detail_rating_4, image_detail_rating_5
        )
        var i = viewModel.filmTotalRating
        while (i > filmDetail.ratingOutOf5) {
            imageViews[i - 1].setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_star_border_black_24dp))
            i--
        }
    }

    private fun initView() {
        text_detail_title.text = viewModel.filmTitle
        setRefreshListener()
    }

    private fun setRefreshListener() {
        layout_detail_refresh.setOnRefreshListener { viewModel.updateFilmDetail() }
    }
}
