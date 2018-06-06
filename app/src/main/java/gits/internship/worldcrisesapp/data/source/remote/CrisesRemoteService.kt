package gits.internship.worldcrisesapp.data.source.remote

import com.google.gson.GsonBuilder
import gits.internship.worldcrisesapp.utils.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface CrisesRemoteService {

    companion object Factory{
        fun create(): CrisesRemoteService{
            val retrofit= Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build()
            return retrofit.create(CrisesRemoteService::class.java)
        }
    }
}