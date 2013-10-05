/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.pmum.bsm.jsfsse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.ejb.Lock;
import javax.inject.Singleton;

/**
 *
 * @author birol
 */
@Singleton
public class MesajListener implements Serializable {
    /**
     *
     */
    private static ConcurrentMap<Integer, Mesaj> mesajListesi = new ConcurrentHashMap<Integer, Mesaj>();
    
    private static Integer mesajIdCounter = new Integer(0);

    public MesajListener() {
        this.mesajListesi = new ConcurrentHashMap<Integer, Mesaj>();
        this.mesajIdCounter = new Integer(0);
    }

    public static Integer checkMesajIdCounter(){
        return mesajIdCounter;
    }
    
    public static Integer getMesajIdCounter() {
        mesajIdCounter++;        
        return mesajIdCounter;
    }

    private static void setMesajIdCounter(Integer mesajIdCounter) {
        MesajListener.mesajIdCounter = mesajIdCounter;
    }

    
    public static boolean mesajEkle(Mesaj mesaj){
        mesajListesi.put(mesaj.getId(), mesaj);
        System.out.println("++++++++++++++++++++ Mesaj Dizisinin Boyutu : "+ mesaj.getId().toString() + ":" + mesajListesi.size());
        return true;
    }
    
    public static void mesajSil(Mesaj mesaj){
        mesajListesi.remove(mesaj.getId(), mesaj);
        System.out.println("++++++++++++++++++++ Mesaj Dizisinin Boyutu : "+ mesaj.getId().toString() + ":" + mesajListesi.size());
    }
    
    public static List<Mesaj> mesajOku(Integer id){
        List<Mesaj> liste = new ArrayList<Mesaj>();
        if(id.compareTo(mesajIdCounter) > 0 ){ return liste;}
        Integer counter = new Integer(id.intValue() + 1);
        while(null != mesajListesi.get(counter)){
            liste.add(mesajListesi.get(counter));
            counter++;
        }
        return liste;
    }
     
}
