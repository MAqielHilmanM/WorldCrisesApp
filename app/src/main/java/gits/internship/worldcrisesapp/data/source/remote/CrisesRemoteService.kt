package gits.internship.worldcrisesapp.data.source.remote

import com.google.gson.GsonBuilder
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.utils.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CrisesRemoteService {
    @GET("crises/{id}")
    fun getCrisesDetail(
            @Path("id") id:String,
            @Query("auth_token") auth_token:String
    ) : Observable<Crises>

    @GET("crises")
    fun getCrisesList(
            @Query("auth_token") auth_token:String,
            @Query("type") type : String? = null,
            @Query("level") level : String?= null,
            @Query("event_id") event_id : String?= null,
            @Query("callback") callback : String?= null,
            @Query("lat") lat : String?= null,
            @Query("lon") lon : String?= null,
            @Query("radius") radius : String?= null
    ) : Observable<MutableList<Crises>>
    
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