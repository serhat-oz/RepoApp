package com.serhat.repoapp.ui.listofrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhat.repoapp.R
import com.serhat.repoapp.databinding.FragmentListOfRepoBinding
import com.serhat.repoapp.db.Favorite
import com.serhat.repoapp.model.User
import kotlinx.android.synthetic.main.fragment_list_of_repo.*
import kotlinx.android.synthetic.main.fragment_list_of_repo.view.*

class ListOfRepoFragment: Fragment() {

    private lateinit var viewDataBinding: FragmentListOfRepoBinding
    private lateinit var repoAdapter: ListOfRepoAdapter
    private lateinit var userRepos:List<User>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentListOfRepoBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ListOfRepoFragment).get(ListOfRepoViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRepos = emptyList()
        initToolbar()
        createAdapter()
        initObservers()
    }

    private fun initToolbar() {
        val toolbar = viewDataBinding.tbMain
        toolbar.tb_title_main.text = getString(R.string.app_name)
    }

    private fun initObservers() {
        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        viewDataBinding.viewmodel?.liveRepoList?.observe(viewLifecycleOwner, Observer {
            userRepos = it

            viewDataBinding.viewmodel!!.getAllFavorites().observe(this, Observer<List<Favorite>> {
                generateRepoListWithFavorites(it)
            })
        })


    }

    private fun generateRepoListWithFavorites(favoriteList: List<Favorite>?) {
        var position = 0
        userRepos.forEach {
            position++
            var favorite = Favorite(it.node_id)
            userRepos[position -1].isAddedToFavorite = favoriteList?.contains(favorite)!!
        }
        repoAdapter.updateRepoList(userRepos)
    }

    private fun createAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            repoAdapter = ListOfRepoAdapter(viewModel)
            val layoutManager = LinearLayoutManager(activity)
            rvListOfRepo.layoutManager = layoutManager
            rvListOfRepo.adapter = repoAdapter
        }
    }
}