package com.serhat.repoapp.ui.listofrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.serhat.repoapp.databinding.FragmentListOfRepoBinding
import kotlinx.android.synthetic.main.fragment_list_of_repo.*

class ListOfRepoFragment: Fragment() {

    private lateinit var viewDataBinding: FragmentListOfRepoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentListOfRepoBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ListOfRepoFragment).get(ListOfRepoViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        

    }
}