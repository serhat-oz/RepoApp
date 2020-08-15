package com.serhat.repoapp.ui.listofrepos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.serhat.repoapp.BR
import com.serhat.repoapp.databinding.ReposListRowItemBinding
import com.serhat.repoapp.model.User

class ListOfRepoAdapter(private val repoListViewModel: ListOfRepoViewModel) : RecyclerView.Adapter<RepoListViewHolder>() {
    private var repoList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ReposListRowItemBinding.inflate(inflater, parent, false)
        return RepoListViewHolder(dataBinding, repoListViewModel)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun updateRepoList(repoList: List<User>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }

}
class RepoListViewHolder constructor(private val dataBinding: ViewDataBinding, private val listOfRepoViewModel: ListOfRepoViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {


    fun bind(itemData: User) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        itemView.setOnClickListener {
            //TODO repo datail fragment will open here
        }
    }
}