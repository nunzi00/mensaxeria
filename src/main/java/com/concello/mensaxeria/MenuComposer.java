/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concello.mensaxeria;

import java.awt.Checkbox;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

/**
 *
 * @author lnunzi
 */
public class MenuComposer extends SelectorComposer<Component> {

//    private static final Logger logger = Logger.getLogger(MenuComposer.class.getName());
//    private Level basico = Level.INFO;
    @Wire
    private Button submitButton;

    @Wire
    private Checkbox acceptTermBox;

    @Listen("onClick = #novoMensaxe")
    public void novoMensaxe() {
        try {
            //logger.log(basico,"Novo mensaxe");
            new MensaxeService().novo();
        } catch (Exception ex) {
            Logger.getLogger(MenuComposer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Listen("onClick = #historicoMensaxe")
    public void historico() {
        try {
            new MensaxeService().historico();
        } catch (Exception ex) {
            Logger.getLogger(MenuComposer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Listen("onClick = #colaMensaxe")
    public void cola() {

    }

    @Listen("onClick = #novoUsuario")
    public void novoUsuario() {

    }

    @Listen("onClick = #buscarUsuario")
    public void buscarUsuario() {

    }

    @Listen("onClick = #modificarUsuario")
    public void modificarUsuario() {
    }

    @Listen("onClick = #eliminarUsuario")
    public void eliminarUsuario() {
    }

    @Listen("onClick = #permisosUsuario")
    public void permisosUsuario() {
    }

    @Listen("onClick = #novoGrupo")
    public void novoGrupo() {
    }

    @Listen("onClick = #consultarGrupo")
    public void consultarGrupo() {
    }

    @Listen("onClick = #modificarGrupo")
    public void modificarGrupo() {
    }

    @Listen("onClick = #eliminarGrupo")
    public void eliminarGrupo() {
    }

    @Listen("onClick = #novoContacto")
    public void novoContacto() {
    }

    @Listen("onClick = #buscarContacto")
    public void buscarContacto() {
    }

    @Listen("onClick = #editarContacto")
    public void editarContacto() {
    }

    @Listen("onClick = #eliminarContacto")
    public void eliminarContacto() {
    }

}
