package com.storecars.api.controller

import com.storecars.api.controller.representation.IdResponse
import com.storecars.api.controller.representation.OwnerCreateRequest
import com.storecars.api.controller.representation.OwnerResponse
import com.storecars.api.controller.representation.OwnerUpdateRequest
import com.storecars.api.service.IOwnerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/owner")
class OwnerController(private val ownerService: IOwnerService) {

    @GetMapping
    fun findAll(name: String?) = ownerService.findAll(name).map { OwnerResponse.fromDto(it) }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = OwnerResponse.fromDto(ownerService.findById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid request: OwnerCreateRequest) =
        IdResponse(ownerService.save(request.toDto()))

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: OwnerUpdateRequest) =
        IdResponse(ownerService.update(request.toDto(id)))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = ownerService.delete(id)
}