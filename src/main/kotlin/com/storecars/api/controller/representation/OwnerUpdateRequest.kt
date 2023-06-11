package com.storecars.api.controller.representation

import com.storecars.api.service.dto.OwnerUpdateDto
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class OwnerUpdateRequest(
    @field:Length(min = 3, max = 200)
    val name: String?,
    @field:Email
    val email: String?,
    val phone: String?
) {
    fun toDto(id: Long) = OwnerUpdateDto(
        id = id,
        name = this.name,
        email = this.name,
        phone = this.phone
    )
}