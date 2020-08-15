package com.serhat.repoapp.ui.repodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.serhat.repoapp.R
import com.serhat.repoapp.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var repoData = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).repoData}

        initViews(repoData)
    }

    private fun initViews(repoData: User?) {
        txtUserName.text = repoData?.owner!!.login
        Picasso.get().load(repoData.owner.avatar_url).placeholder(R.drawable.place_holder).into(ivUserImage)
        //TODO format texts
        tvStarCount.text = "Star Count: " + repoData.stargazers_count
        tvOpenIssueCount.text = "Open Issues Count: " + repoData.open_issues_count
    }
}