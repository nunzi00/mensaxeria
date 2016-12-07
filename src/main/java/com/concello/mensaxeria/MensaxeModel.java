package com.concello.mensaxeria;

import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Footer;

public final class MensaxeModel extends SelectorComposer<Component> {

    private List<Mensaxe> mensajes;
    MensaxeService Mens;
    private int size = 0;
    
      

    public MensaxeModel() throws Exception {
        this.Mens = new MensaxeService();
        setMensajes(this.Mens.getMensajes());
//        size = Mens.getSize();
//        
    }

    /**
     * @return the mensajes
     */
    @Subscribe("myqueue")
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
}
   
