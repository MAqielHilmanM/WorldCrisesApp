package gits.internship.worldcrisesapp.data.source.remote

import gits.internship.worldcrisesapp.data.source.CrisesDataSource

object CrisesRemoteDataSource : CrisesDataSource{
    private val service = CrisesRemoteService.create()

    override fun getCrises(callback: CrisesDataSource.GetCrisesCallback) {
    }

}