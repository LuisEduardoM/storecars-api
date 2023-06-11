package com.storecars.api.service

import com.storecars.api.service.dto.OwnerCreateDto
import com.storecars.api.service.dto.OwnerDetailsDto
import com.storecars.api.service.dto.OwnerUpdateDto

interface IOwnerService {

    fun findById(id: Long) : OwnerDetailsDto

    fun findAll(name: String?): List<OwnerDetailsDto>

    fun save(dto: OwnerCreateDto): Long

    fun update(dto: OwnerUpdateDto): Long

    fun delete(id: Long)
}