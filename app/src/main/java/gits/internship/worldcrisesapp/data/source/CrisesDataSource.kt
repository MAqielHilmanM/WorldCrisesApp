package gits.internship.worldcrisesapp.data.source

import gits.internship.worldcrisesapp.data.Crises
import javax.security.auth.callback.Callback

interface CrisesDataSource {
    fun getCrises(callback: GetCrisesCallback)

    interface GetCrisesCallback {
        fun onCrisesLoaded(crises : MutableList<Crises>?)
        fun onNotAvailable()
        fun onError(msg : String?)
    }
}