package app.ip_address.domain.usecase

import app.ip_address.domain.model.IpInfo
import app.ip_address.domain.repo.IpRepository
import javax.inject.Inject

class GetIpInfoUseCase @Inject constructor(
    private val repository: IpRepository
) {
    suspend operator fun invoke(): IpInfo = repository.getIpInfo()
}