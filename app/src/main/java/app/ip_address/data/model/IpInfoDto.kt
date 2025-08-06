package app.ip_address.data.model

import app.ip_address.domain.model.IpInfo

data class IpInfoDto(
    val ip: String,
    val city: String,
    val region: String,
    val country_name: String,
    val postal: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String
) {
    fun toDomain() =
        IpInfo(ip, city, region, country_name, postal, latitude, longitude, timezone)
}