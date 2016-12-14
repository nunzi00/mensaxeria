/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria.validators;

import java.util.Calendar;
import java.util.Date;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import org.zkoss.util.logging.Log;

/**
 *
 * @author lnunzi
 */
public class Fecha_envioValidator extends AbstractValidator {

    private static final Log log = Log.lookup(Fecha_envioValidator.class);

    @Override
    public void validate(ValidationContext ctx) {
        log.info(ctx.getProperty().getValue());
        Date envio = (Date) ctx.getProperty().getValue();
        Calendar ahoratmp = Calendar.getInstance();
        ahoratmp.add(Calendar.MINUTE, -5);
        Date ahora=ahoratmp.getTime();
        if (envio.after(ahora)){
            log.error("Valida");
        } else {
            this.addInvalidMessage(ctx, "fecha_envio_date", "La fecha de envio debe ser igual o superior a la actual");
            log.info("FECHA NO VALIDA");
        }
    }
}
