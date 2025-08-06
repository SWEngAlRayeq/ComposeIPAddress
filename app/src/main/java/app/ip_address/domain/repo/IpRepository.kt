package app.ip_address.domain.repo

import app.ip_address.domain.model.IpInfo

interface IpRepository {
    suspend fun getIpInfo(): IpInfo
}