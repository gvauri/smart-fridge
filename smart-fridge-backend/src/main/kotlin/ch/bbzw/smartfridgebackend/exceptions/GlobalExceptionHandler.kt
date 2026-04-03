package ch.bbzw.smartfridgebackend.exceptions

import java.util.Collections;
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleUserAlreadyExistsException(ex: UserAlreadyExistsException): ResponseEntity<MutableMap<String, String>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Collections.singletonMap("error", ex.message))
    }

    @ExceptionHandler
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<MutableMap<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap("error", ex.message))
    }
}