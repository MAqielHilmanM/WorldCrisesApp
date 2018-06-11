package gits.internship.worldcrisesapp.crises_detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import android.util.Log
import android.widget.Toast
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.data.Population
import gits.internship.worldcrisesapp.data.source.CrisesDataSource
import gits.internship.worldcrisesapp.data.source.CrisesRepository
import gits.internship.worldcrisesapp.utils.SingleLiveEvent
import io.reactivex.Observable
import kotlin.math.log
class CrisesDetailViewModel(application: Application,private val crisesRepository: CrisesRepository) : AndroidViewModel(application){
        val crisesData : ObservableField<Crises> = ObservableField()
        internal val openLinkResource = SingleLiveEvent<String>()
    val id : ObservableField<String> = ObservableField()

    fun start(){
        //getData()
    }

    private fun getData() {
        Log.wtf("CrisesDetailVM","id: "+id.get())
        crisesRepository.getCrisesDetail(object : CrisesDataSource.GetCrisesDetailCallback{
            override fun onNotAvailable() {
                Toast.makeText(getApplication(),"No Data Found", Toast.LENGTH_SHORT).show()
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(),"Error at "+msg,Toast.LENGTH_SHORT).show()
            }


            override fun onCrisesLoaded(crises: Crises) {
                crisesData.set(crises)
            }
        }, id.toString())
    }

    fun getPopulation() : String {
        if (crisesData.get()?.crisis_population_hash != null)
            return crisesData.get()?.crisis_population_hash!!.value.toString()
        else{
            val separatorPopulation = crisesData.get()?.crisis_population?.split(" ")
            if (separatorPopulation != null) {
                return separatorPopulation.get(0)
            }else
                return 0.toString()
        }
    }
}

