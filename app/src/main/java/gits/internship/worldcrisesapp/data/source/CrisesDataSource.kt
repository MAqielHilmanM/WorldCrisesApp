package gits.internship.worldcrisesapp.data.source

import gits.internship.worldcrisesapp.data.Crises
import javax.security.auth.callback.Callback

interface CrisesDataSource {
    fun getCrises(callback: GetCrisesCallback)

    interface GetCrisesCallback {
        fun onCrisesLoaded(crises : List<Crises>)
        fun onNotAvailable()
        fun onError(msg : String?)
    }

    fun getCrisesDetail(callback: GetCrisesDetailCallback, id : String)

    interface GetCrisesDetailCallback {
        fun onCrisesLoaded(crises : Crises)
        fun onNotAvailable()
        fun onError(msg : String?)
    }
}