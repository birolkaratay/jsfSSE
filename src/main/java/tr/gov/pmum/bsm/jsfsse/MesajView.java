/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.pmum.bsm.jsfsse;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author birol
 */
@SessionScoped
@Named
public class MesajView implements Serializable {

    private Mesaj kullaniciMesaji = new Mesaj();

    public Mesaj getKullaniciMesaji() {
        return kullaniciMesaji;
    }

    public void setKullaniciMesaji(Mesaj kullaniciMesaji) {
        this.kullaniciMesaji = kullaniciMesaji;
    }

    public void kullaniciMesajiYaz() {
        MesajListener.mesajEkle(new Mesaj(MesajListener.getMesajIdCounter(), kullaniciMesaji.getKullanici(), kullaniciMesaji.getIcerik()));
        kullaniciMesaji.setIcerik("");
        //System.out.println(kullaniciMesaji.getKullanici() + " : " + kullaniciMesaji.getIcerik());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bilgilendirme", "Mesajın Gönderildi."));
        //return true;MesajListener.mesajEkle(kullaniciMesaji);        
    }
}
