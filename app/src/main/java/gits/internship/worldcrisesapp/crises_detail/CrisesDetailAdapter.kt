package gits.internship.worldcrisesapp.crises_detail

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.data.Resource
import gits.internship.worldcrisesapp.databinding.CrisesDetailRowBinding

class CrisesDetailAdapter(private var lists : List<Resource>,private var viewModel: CrisesDetailViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            CrisesDetailRowHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent!!.context),
                    R.layout.crises_detail_row,
                    parent,
                    false) as CrisesDetailRowBinding)

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val data = lists[position]
        val actionListener = object : CrisesDetailItemListener{
            override fun onItemClickListener() {
                viewModel.openLinkResource.value = data.rdf_resource
            }
        }

        (holder as CrisesDetailRowHolder).bindRows(data,actionListener)
    }

    fun replaceData(data: List<Resource>) {
        this.lists = data
        notifyDataSetChanged()
    }

    class CrisesDetailRowHolder(binding: CrisesDetailRowBinding) : RecyclerView.ViewHolder(binding.root){
        val crisesDetailRowBinding = binding

        fun bindRows(resource: Resource, crisesDetailItemListener: CrisesDetailItemListener){
            crisesDetailRowBinding.data = resource
            crisesDetailRowBinding.action = crisesDetailItemListener
            crisesDetailRowBinding.executePendingBindings()
        }
    }
}