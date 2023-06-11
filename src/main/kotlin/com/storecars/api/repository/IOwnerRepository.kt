package com.storecars.api.repository

import com.storecars.api.entity.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IOwnerRepository : JpaRepository<Owner, Long> {

    @Query("""Select o from Owner o where (null = :name or o.name ILIKE :name)""")
    fun findAllAndFilterByName(name: String?): List<Owner>
}

