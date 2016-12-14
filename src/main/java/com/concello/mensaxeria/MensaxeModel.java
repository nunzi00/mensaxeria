package com.concello.mensaxeria;
//model view

import java.util.List;
import org.zkoss.util.logging.Log;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

public final class MensaxeModel extends SelectorComposer<Component> {

    private List<Mensaxe> mensajes;
    private Mensaxe mensaje=new Mensaxe();
    MensaxeService Mens;
    private int size = 0;
    private static final Log log = Log.lookup(MensaxeService.class);

    public MensaxeModel() throws Exception {

        this.Mens = new MensaxeService();
        setMensajes(this.Mens.getMensajes());
//        size = Mens.getSize();
//        
    }
    @Command
    public final void submit() {
        log.info("submit en MensaxeModel");
        Mens.doAddMensaxeHTML(this.mensaje);
    }

    /**
     * @return the mensajes
     */
    public List<Mensaxe> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public final void setMensajes(List<Mensaxe> mensajes) {
        this.mensajes = mensajes;
    }

    /**
     * @return the Mens
     */
    public MensaxeService getMens() {
        return Mens;
    }

    /**
     * @param Mens the Mens to set
     */
    public void setMens(MensaxeService Mens) {
        this.Mens = Mens;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the mensaje
     */
    public Mensaxe getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(Mensaxe mensaje) {
        this.mensaje = mensaje;
    }
}
