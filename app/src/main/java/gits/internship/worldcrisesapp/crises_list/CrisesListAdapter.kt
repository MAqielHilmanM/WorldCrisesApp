package gits.internship.worldcrisesapp.crises_list

import android.database.Observable
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.data.Resource
import gits.internship.worldcrisesapp.databinding.CrisesListRowBinding

class CrisesListAdapter(private var crises : List<Crises>, private var crisesListViewModel: CrisesListViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            CrisesListRowHolder(DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.crises_list_row,parent,false) as CrisesListRowBinding)

    override fun getItemCount(): Int =
            crises.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val data = crises[position]
        val actionListener = object : CrisesListItemListener{
            override fun OnItemClickListener(crises: Crises) {
                crisesListViewModel.openDetailCrises.value = crises
            }
        }

        (holder as CrisesListRowHolder).bindRows(data,actionListener)
    }

    fun replaceData(crises: List<Crises>) {
        this.crises = crises
        notifyDataSetChanged()
    }

    class CrisesListRowHolder(binding: CrisesListRowBinding) : RecyclerView.ViewHolder(binding.root){
        val crisesRowBinding = binding

        fun bindRows(crises: Crises, userActionListener : CrisesListItemListener){
            crisesRowBinding.data = crises
            crisesRowBinding.action = userActionListener
            crisesRowBinding.executePendingBindings()
        }
    }
}