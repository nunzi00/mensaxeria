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
public class ContenidoHTMLValidator extends AbstractValidator {

    private static final Log log = Log.lookup(ContenidoHTMLValidator.class);

    @Override
    public void validate(ValidationContext ctx) {
        log.info(ctx.getProperty().getValue());
        String contenido=(String)ctx.getProperty().getValue();
        if (contenido.length() > 30 && contenido != null   ){
            log.error("Valido");
        } else {
            this.addInvalidMessage(ctx, "contenido", "El mensaje es demasiado corto");
            log.info("Contenido no valido");
        }
    }
}
