package com.storecars.api.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    private val CONSTANT_VALIDATION_NOT_BLANK = "NotBlank"
    private val CONSTANT_VALIDATION_NOT_NULL = "NotNull"
    private val CONSTANT_VALIDATION_LENGTH = "Length"
    private val CONSTANT_VALIDATION_PATTERN = "Pattern"
    private val CONSTANT_VALIDATION_MIN = "Min"

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors: List<ErrorCode> = generatedErrorList(ex.bindingResult)
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException, request: WebRequest): ResponseEntity<Any?>? {
        val msgUser: String = ex.message
        val msgDeveloper: String = ex.message
        val errors = listOf(ErrorCode(msgUser, msgDeveloper))
        return handleExceptionInternal(ex, errors, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    private fun generatedErrorList(bindingResult: BindingResult) =
        bindingResult.fieldErrors.map { fieldError ->
            val userMessage = handleMessagesError(fieldError)
            val msgDeveloper = fieldError.toString()
            ErrorCode(userMessage, msgDeveloper)
        }

    private fun handleMessagesError(fieldError: FieldError): String {
        if (fieldError.code == CONSTANT_VALIDATION_NOT_BLANK) {
            return fieldError.defaultMessage + " is mandatory."
        }
        if (fieldError.code == CONSTANT_VALIDATION_NOT_NULL) {
            return fieldError.defaultMessage + " is mandatory."
        }
        if (fieldError.code == CONSTANT_VALIDATION_LENGTH) {
            return fieldError.defaultMessage + String.format(
                " must have between %s and %s characters.",
                fieldError.arguments!![2], fieldError.arguments!![1]
            )
        }
        if (fieldError.code == CONSTANT_VALIDATION_PATTERN) {
            return fieldError.defaultMessage + " invalid format."
        }
        return if (fieldError.code == CONSTANT_VALIDATION_MIN) {
            fieldError.defaultMessage + String.format(
                " must be greater than or equal to %s", fieldError.arguments!![1]
            )
        } else fieldError.toString()
    }
}