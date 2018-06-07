package gits.internship.worldcrisesapp.crises_list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.widget.Toast
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.data.source.CrisesDataSource
import gits.internship.worldcrisesapp.data.source.CrisesRepository
import gits.internship.worldcrisesapp.utils.SingleLiveEvent

class CrisesListViewModel(application: Application,private val crisesRepository: CrisesRepository) : AndroidViewModel(application) {
    val crisesLists: ObservableList<Crises> = ObservableArrayList()
    internal val openDetailCrises = SingleLiveEvent<Crises>()

    fun start(){
        getData()
    }

    private fun getData() {
        crisesRepository.getCrises(object : CrisesDataSource.GetCrisesCallback{
            override fun onCrisesLoaded(crises: List<Crises>) {
                with(crisesLists){
                    clear()
                    addAll(crises)
                }
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(),"No Data Found", Toast.LENGTH_SHORT).show()
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(),"Error at "+msg,Toast.LENGTH_SHORT).show()
            }

        })
    }
}