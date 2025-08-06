package app.ip_address.domain.model

data class IpInfo(
    val ip: String,
    val city: String,
    val region: String,
    val country: String,
    val postal: String?,
    val latitude: Double,
    val longitude: Double,
    val timezone: String
)