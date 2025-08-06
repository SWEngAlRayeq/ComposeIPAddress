package app.ip_address.data.remote

import app.ip_address.data.model.IpInfoDto
import retrofit2.http.GET

interface IpApi {

    @GET("json/")
    suspend fun getIpInfo(): IpInfoDto

}