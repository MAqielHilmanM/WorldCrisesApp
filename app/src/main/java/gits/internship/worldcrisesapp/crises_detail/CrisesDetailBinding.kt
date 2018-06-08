package gits.internship.worldcrisesapp.crises_detail

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import gits.internship.worldcrisesapp.data.Resource

object CrisesDetailBinding {
    @BindingAdapter("app:resourceList")
    @JvmStatic
    fun setResourceList(recyclerView: RecyclerView, resource: List<Resource>?) {
        if (resource != null) {
            with(recyclerView.adapter as CrisesDetailAdapter){
                replaceData(resource)
            }
        }
    }



}