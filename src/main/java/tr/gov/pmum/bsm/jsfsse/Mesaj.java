/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.pmum.bsm.jsfsse;

import java.io.Serializable;

/**
 *
 * @author birol
 */
public class Mesaj implements Serializable {
    
    private Integer id;
    private String kullanici;
    private String icerik;

    public Mesaj(Integer id, String kullanici, String icerik) {
        this.id = id;
        this.kullanici = kullanici;
        this.icerik = icerik;
    }

    public Mesaj() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
    
}
