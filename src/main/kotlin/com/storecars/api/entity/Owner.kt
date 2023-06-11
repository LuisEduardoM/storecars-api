package com.storecars.api.entity

import com.storecars.api.service.dto.OwnerCreateDto
import com.storecars.api.service.dto.OwnerDetailsDto
import jakarta.persistence.*

@Entity
@Table(name = "woner")
data class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,
    val name: String,
    val email: String,
    val phone: String
) {

    fun toDto() = OwnerDetailsDto(
        id = this.id,
        name = this.name,
        email = this.email,
        phone = this.phone
    )

    companion object {
        fun fromDtoToSave(dto: OwnerCreateDto) = Owner(
            name = dto.name,
            email = dto.email,
            phone = dto.phone
        )
    }
}