package com.storecars.api.controller.representation

import com.storecars.api.service.dto.OwnerCreateDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class OwnerCreateRequest(
    @field:NotBlank
    @field:Length(min = 3, max = 200)
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val phone: String
) {
    fun toDto() = OwnerCreateDto(
        name = this.name,
        email = this.name,
        phone = this.phone
    )
}