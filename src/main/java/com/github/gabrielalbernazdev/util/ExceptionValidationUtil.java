package com.github.gabrielalbernazdev.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.validation.ConstraintViolationException;

public class ExceptionValidationUtil {
    /**
     * Iterates through the cause chain to find a ConstraintViolationException.
     *
     * @param t The throwable to inspect.
     * @return The first ConstraintViolationException found, or null if none.
     */
    public static ConstraintViolationException findConstraintViolation(Throwable t) {
        Throwable cause = t;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException) {
                return (ConstraintViolationException) cause;
            }
            cause = cause.getCause();
        }
        return null;
    }

    /**
     * Adds FacesMessages for each constraint violation in the given exception.
     *
     * @param cve The ConstraintViolationException containing violations.
     * @param ctx The FacesContext to which messages will be added.
     */
    public static void handleViolations(ConstraintViolationException cve, FacesContext ctx) {
        cve.getConstraintViolations().forEach(cv -> {
            String field = cv.getPropertyPath().toString();
            String msg   = cv.getMessage();
            ctx.addMessage(null,
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    String.format("Invalid Field [%s]: %s", field, msg),
                    null
                )
            );
        });
    }

    /**
     * Convenience method to find and handle constraint violations within any throwable.
     *
     * @param t   The throwable to inspect for validation errors.
     * @param ctx The FacesContext to which messages will be added.
     * @return true if a ConstraintViolationException was found and handled; false otherwise.
     */
    public static boolean handleConstraintViolations(Throwable t, FacesContext ctx) {
        ConstraintViolationException cve = findConstraintViolation(t);
        if (cve != null) {
            handleViolations(cve, ctx);
            return true;
        }
        return false;
    }
}
