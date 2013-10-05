package tr.gov.pmum.bsm.jsfsse;

import java.io.Serializable;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 * Using to Store static message counters etc. 
 * @author birol karatay
 */
@Singleton
@Lock
public class Constant implements Serializable{
    
    private static Integer messageCounter = new Integer(0);
    
    public static Integer getCurrentValue(){
        return messageCounter;
    }
    
    /**
     * 
     * @return new Counter number
     */
    @Lock(LockType.READ)
    public static Integer getNextValue(){
        messageCounter++;
        return messageCounter;
    }
}
