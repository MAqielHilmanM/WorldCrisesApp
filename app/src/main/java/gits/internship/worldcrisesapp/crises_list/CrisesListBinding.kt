package gits.internship.worldcrisesapp.crises_list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import gits.internship.worldcrisesapp.data.Crises

object CrisesListBinding {
    @BindingAdapter("app:crisesList")
    @JvmStatic
    fun setCrisesList(recyclerView: RecyclerView, crises: List<Crises>) {
        with(recyclerView.adapter as CrisesListAdapter){
            replaceData(crises)
        }
    }
}