package org.softfriascorporations.avalonplazafec.exeptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.spec.InvalidKeySpecException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     *
     * Excepción	HTTP Status	Cuándo ocurre
     * EntityNotFoundException	404	No se encuentra un registro en la base de datos
     * DataIntegrityViolationException	409	Violación de FK, UNIQUE, NOT NULL
     * MethodArgumentNotValidException	400	Fallo de validación de @Valid en request body
     * HttpRequestMethodNotSupportedException	405	Petición con método HTTP no permitido
     * RuntimeException	404	Excepciones genéricas lanzadas con lambdas (orElseThrow)
     * NullPointerException	500	Acceso a objeto nulo
     * IllegalArgumentException	400	Argumentos inválidos
     * Exception	500	Cualquier otra excepción no prevista
     *
     *
     */

    // 1. Entidad no encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Registro no encontrado: " + e.getMessage());
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("error: " + e.getMessage());
    }

    // 2. Violación de integridad (FK, UNIQUE, NOT NULL)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Violación de integridad en la base de datos: " + e.getMessage());
    }

    // 3. Validación de Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error de validación: " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    // 4. Petición HTTP no soportada
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Método HTTP no permitido: " + e.getMessage());
    }

    // 5. RuntimeException genérica (para lambdas, orElseThrow, etc.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: " + e.getMessage());
    }

    // 6. NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointer(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: objeto nulo");
    }

    // 7. IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Argumento inválido: " + e.getMessage());
    }

    // 8. Default: cualquier otra excepción
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error inesperado: " + e.getMessage());
    }
    // 9.InvalidKeySpecException
    @ExceptionHandler(InvalidKeySpecException.class)
    public ResponseEntity<String> handleAll(InvalidKeySpecException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("credenciales invalidas: " + e.getMessage());
    }
}
