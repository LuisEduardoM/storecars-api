package com.storecars.api.service

import com.storecars.api.entity.Owner
import com.storecars.api.exception.BusinessException
import com.storecars.api.repository.IOwnerRepository
import com.storecars.api.service.dto.OwnerCreateDto
import com.storecars.api.service.dto.OwnerDetailsDto
import com.storecars.api.service.dto.OwnerUpdateDto
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class OwnerService(private val ownerRepository: IOwnerRepository) : IOwnerService {

    override fun findById(id: Long): OwnerDetailsDto = getOwnerByIdOrFail(id).toDto()

    override fun findAll(name: String?): List<OwnerDetailsDto> =
        ownerRepository.findAllAndFilterByName(name).map { it.toDto() }

    override fun save(dto: OwnerCreateDto): Long =
        ownerRepository.save(Owner.fromDtoToSave(dto)).id

    override fun update(dto: OwnerUpdateDto): Long {
        val owner = getOwnerByIdOrFail(dto.id)
        val ownerToUpdate = owner.copy(
            name = dto.name ?: owner.name,
            email = dto.email ?: owner.email,
            phone = dto.phone ?: owner.phone
        )
        return ownerRepository.save(ownerToUpdate).id
    }

    override fun delete(id: Long) {
        getOwnerByIdOrFail(id).also { ownerRepository.delete(it) }
    }

    private fun getOwnerByIdOrFail(id: Long) =
        ownerRepository.findById(id).getOrNull() ?: throw BusinessException("Owner with $id not found")
}