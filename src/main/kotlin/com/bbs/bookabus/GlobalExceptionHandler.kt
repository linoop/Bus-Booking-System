package com.bbs.bookabus

import com.bbs.bookabus.dto.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<Response<Nothing>> {
        val response = Response<Nothing>(
                status = "Error",
                message = ex.message ?: "An error occurred"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    @ExceptionHandler(BusNotFoundException::class)
    fun handleBusNotFoundException(ex: BusNotFoundException): ResponseEntity<Response<Nothing>> {

        val response = Response<Nothing>(
                status = HttpStatus.NOT_FOUND.value().toString(),
                message = ex.message ?: "An error occurred"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBusNotFoundException(ex: MethodArgumentNotValidException): ResponseEntity<Response<Map<String, String>>> {
        val errors = ex.bindingResult.allErrors.associate {
            val fieldName = (it as FieldError).field
            val errorMessage = it.defaultMessage ?: "Invalid value for field '$fieldName'"
            fieldName to errorMessage
        }
        val response = Response(
                status = HttpStatus.BAD_REQUEST.value().toString(),
                message = "Invalid value for field(s)",
                data = errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }
}

class BusNotFoundException(id: Long) : RuntimeException("Can't find Bus with id $id")