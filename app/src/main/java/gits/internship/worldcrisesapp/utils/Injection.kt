package gits.internship.worldcrisesapp.utils

import android.content.Context
import android.preference.PreferenceManager
import gits.internship.worldcrisesapp.data.source.CrisesRepository
import gits.internship.worldcrisesapp.data.source.local.CrisesLocalDataSource
import gits.internship.worldcrisesapp.data.source.remote.CrisesRemoteDataSource

object Injection {
    fun provideCrisesRepository(context: Context) = CrisesRepository.getInstance(
            CrisesRemoteDataSource,
            CrisesLocalDataSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!
            )}