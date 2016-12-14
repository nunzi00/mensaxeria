/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import org.zkoss.util.logging.Log;

/**
 *
 * @author lnunzi
 */
public class PrioridadValidator extends AbstractValidator {

    private static final Log log = Log.lookup(PrioridadValidator.class);

    @Override
    public void validate(ValidationContext ctx) {
        log.info(ctx.getProperty().getValue());
        String prioridad=(String)ctx.getProperty().getValue();
        if (!"Urgente".equals(prioridad) && 
        !"Baja".equals(prioridad) && 
        !"Normal".equals(prioridad)){
            this.addInvalidMessage(ctx, "prioridad", "Debe seleccionar una prioridad de envio");
        }
    }
}
