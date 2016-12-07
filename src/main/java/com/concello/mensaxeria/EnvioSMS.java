package com.concello.mensaxeria;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import org.zkoss.util.logging.Log;
//import utilities.AxisSSLSocketFactory;
//limite 500 mensajes por peticion
//no se puede enviar otra peticion hasta finalizar la primera
//son peticiones sincronas
//limite mensaje de texto 459 caracteres     

public class EnvioSMS {

    private static final Log log = Log.lookup(EnvioSMS.class);
    private static final String USER = "85B13A60-RAFAEL.OT"; //Nombre de usuario
    private static final String PASS = "cd5f8e9cc0c"; //Contraseña
    public static String endpoint = "https://www.mensajerianegocios.movistar.es/SrvConexion";

    public EnvioSMS() {
        log.info("Enviando SMS");
        //            Vector params = new Vector();
//            params.addElement(login);
//            params.addElement(password);
//            Vector SMS = new Vector();
//            Vector primer_SMS = new Vector();
//            primer_SMS.addElement("665979119");
//            primer_SMS.addElement("Texto primer mensaje");
//            primer_SMS.addElement("miRemite");
//            SMS.addElement(primer_SMS);
        log.info("Cargando certificado");
        //AxisProperties.setProperty("axis.socketSecureFactory", "utilities.FluxitSSLSocketFactory", true);
        System.setProperty("javax.net.ssl.trustStore", "resources/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","");
        log.info("Creando llamada");
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setUsername(USER);
            call.setPassword(PASS);
            call.setOperationName("MensajeriaNegocios.enviarSMS");
            String ret = (String) call.invoke(new Object[]{"some kind of message"});
            log.info(ret);
        } catch (ServiceException ex) {
            Logger.getLogger(EnvioSMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(EnvioSMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(EnvioSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//
//Crear segundo SMS
//        Vector segundo_SMS = new Vector();
//        segundo_SMS.addElement("609000003");
//        segundo_SMS.addElement("Texto segundo mensaje");
//        segundo_SMS.addElement("217812");
//        SMS.addElement(segundo_SMS);
//        params.addElement(SMS);
//        result = client.execute("MensajeriaNegocios.enviarSMS", params);
}
