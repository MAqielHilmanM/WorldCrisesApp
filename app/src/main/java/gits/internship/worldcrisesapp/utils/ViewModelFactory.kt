package gits.internship.worldcrisesapp.utils

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import gits.internship.worldcrisesapp.crises_detail.CrisesDetailViewModel
import gits.internship.worldcrisesapp.crises_list.CrisesListViewModel
import gits.internship.worldcrisesapp.data.source.CrisesRepository

class ViewModelFactory private constructor(
        private val application: Application,
        private val crisesRepository: CrisesRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass){
        when{
            isAssignableFrom(CrisesDetailViewModel::class.java) ->
                    CrisesDetailViewModel(application,crisesRepository)
            isAssignableFrom(CrisesListViewModel::class.java) ->
                CrisesListViewModel(application,crisesRepository)
            else ->
                    throw IllegalArgumentException("Unknow View Model class: {${modelClass.name}}")
        }
    } as T

    companion object {
        @Suppress("StaticFieldLeak")
        @Volatile private var INSTANCE : ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java){
                    INSTANCE ?: ViewModelFactory(
                            application,
                            Injection.provideCrisesRepository(application.applicationContext
                    )).also{ INSTANCE = it }
                }

        @VisibleForTesting fun destroyInstance(){ INSTANCE = null}
    }
}