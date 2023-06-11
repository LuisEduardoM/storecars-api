package com.storecars.api.service.dto

data class OwnerUpdateDto(
    val id: Long,
    val name: String?,
    val email: String?,
    val phone: String?
)