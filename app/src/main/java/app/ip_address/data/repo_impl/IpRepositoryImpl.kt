package app.ip_address.data.repo_impl

import app.ip_address.data.remote.IpApi
import app.ip_address.domain.model.IpInfo
import app.ip_address.domain.repo.IpRepository
import javax.inject.Inject

class IpRepositoryImpl @Inject constructor(
    private val api: IpApi
) : IpRepository {
    override suspend fun getIpInfo(): IpInfo {
        return  api.getIpInfo().toDomain()
    }
}