package com.serhat.repoapp.ui.repodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.serhat.repoapp.BR
import com.serhat.repoapp.R
import com.serhat.repoapp.databinding.FragmentRepoDetailBinding
import com.serhat.repoapp.db.Favorite
import com.serhat.repoapp.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list_of_repo.view.tb_title_main
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import kotlinx.android.synthetic.main.fragment_repo_detail.view.*

class RepoDetailFragment: Fragment() {

    private lateinit var viewDataBinding: FragmentRepoDetailBinding
    var isAddedToFavorite:Boolean = false
    lateinit var nodeId:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRepoDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@RepoDetailFragment).get(RepoDetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var repoData = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).repoData}
        nodeId = repoData!!.node_id
        viewDataBinding.setVariable(BR.itemData, repoData)

        initObservers(repoData)
    }

    private fun initObservers(repoData: User?) {
        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        /*viewDataBinding.viewmodel!!.getAllFavorites().observe(this, Observer<List<Favorite>>{
            it
            Toast.makeText(context, "", Toast.LENGTH_LONG).show()
        })*/

        viewDataBinding.viewmodel!!.getFavoriteByNodeId(nodeId).observe(this, Observer<Favorite>{
            //if null this repo not added to favorites
            isAddedToFavorite = it != null
            viewDataBinding.viewmodel?.dataLoading?.value = false
            initViews(repoData)
        })
    }

    private fun initViews(repoData: User?) {

        Picasso.get().load(repoData!!.owner.avatar_url).placeholder(R.drawable.place_holder)
            .into(ivUserImage)
        tvStarCount.text = repoData.stargazers_count.toString()
        tvOpenIssueCount.text = repoData.open_issues_count.toString()
        tvLanguage.text = repoData.language


        val toolbar = viewDataBinding.tbDetail
        toolbar.tb_title_main.text = repoData.name

        toolbar.ivToolbarBackButton.setOnClickListener {
            activity?.onBackPressed()
        }

        if (isAddedToFavorite){
            toolbar.ivToolbarAddFavorite.setImageResource(R.drawable.ic_star_orange_24dp)
        }else{
            toolbar.ivToolbarAddFavorite.setImageResource(R.drawable.ic_star_white_24dp)
        }

        toolbar.ivToolbarAddFavorite.setOnClickListener {
            addOrRemoveFromFavorite()
        }
    }

    private fun addOrRemoveFromFavorite() {
        var favorite = Favorite(nodeId)
        if (!isAddedToFavorite) {
            viewDataBinding.viewmodel!!.insert(favorite)
        }else{
            viewDataBinding.viewmodel!!.remove(favorite)
        }
    }

}