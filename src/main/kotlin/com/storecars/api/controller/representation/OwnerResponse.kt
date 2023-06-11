package com.storecars.api.controller.representation

import com.storecars.api.service.dto.OwnerDetailsDto

data class OwnerResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String
) {
    companion object {
        fun fromDto(dto: OwnerDetailsDto) = OwnerResponse(
            id = dto.id,
            name = dto.name,
            email = dto.email,
            phone = dto.phone
        )
    }
}