package com.example.demo.shared.exception;

import org.postgresql.util.PSQLException;

public class PostgresErrorHandler {
    
    public static RuntimeException mapToHttpException(
            Exception e
    ) {
        return mapToHttpException(e, new PostgresErrorMessage());
    }
    
    public static RuntimeException mapToHttpException(
            Exception e,
            PostgresErrorMessage psErrorMessage
    ) {
        PSQLException psqlEx = PostgresErrorHandler.findPSQLException(e);
        String sqlState = psqlEx.getSQLState();
        if (sqlState == null) {
            return new InternalServerException();
        }
        
        return switch (sqlState) {
            case "23505" -> // unique_violation
                    new ConflictException(psErrorMessage.getUniqueConstraintViolationMessage());
            case "23503" -> // foreign_key_violation
                    new ResourceNotFoundException("Any of the given foreign Key doesn't exist");
            default -> new InternalServerException();
        };
    }
    
    private static PSQLException findPSQLException(Throwable e) {
        while (e != null) {
            if (e instanceof PSQLException psqlException) {
                return psqlException;
            }
            e = e.getCause();
        }
        return null;
    }
}
