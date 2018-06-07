package gits.internship.worldcrisesapp.data.source.remote

import gits.internship.worldcrisesapp.data.source.CrisesDataSource
import gits.internship.worldcrisesapp.utils.Constant
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object CrisesRemoteDataSource : CrisesDataSource{

    private val service = CrisesRemoteService.create()

    override fun getCrisesDetail(callback: CrisesDataSource.GetCrisesDetailCallback, id: String) {
        service.getCrisesDetail(id,Constant.BASE_AUTHTOKEN)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result ->
                    kotlin.run {
                            callback.onCrisesLoaded(result)
                    }
                },{
                    error -> callback.onError(error.message)
                })
    }

    override fun getCrises(callback: CrisesDataSource.GetCrisesCallback) {
        service.getCrisesList(auth_token = Constant.BASE_AUTHTOKEN)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    kotlin.run {
                        if (result.size != 0)
                            callback.onCrisesLoaded(result.toList())
                        else
                            callback.onNotAvailable()
                    }
                }, {error ->
                    callback.onError(error.message)
                })
    }

}