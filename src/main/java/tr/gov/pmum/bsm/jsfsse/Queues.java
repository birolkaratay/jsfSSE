package tr.gov.pmum.bsm.jsfsse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Singleton;

/**
 * To store user messages lists in Maps
 * @author birol karatay
 */
@Singleton
public class Queues implements Serializable {
    
    /**
     * User chat messages store
     */
    private static ConcurrentMap<Integer, Message> chatMessageQueue = new ConcurrentHashMap<Integer, Message>();
    
    /**
     * Default Constructor
     */
    public Queues() {
        Queues.chatMessageQueue = new ConcurrentHashMap<Integer, Message>();
    }

    /**
     * 
     * @param message will add to the chat Queue
     * @return  if everything is good return true;
     */
    @Lock(LockType.WRITE)
    public static boolean addMessage(Message message){
        chatMessageQueue.put(message.getId(), message);
        System.out.println("++++++++++++++++++++ Size of ChatMessageQueue : "+ message.getId().toString() + ":" + chatMessageQueue.size());
        return true;
    }
    
    /**
     * To remove a message from Queue
     * @param message will be removed from the ChatMessageQueue
     */
    @Lock(LockType.WRITE)
    public static void removeMessage(Message message){
        chatMessageQueue.remove(message.getId(), message);
        System.out.println("++++++++++++++++++++ Size of ChatMessageQueue : "+ message.getId().toString() + ":" + chatMessageQueue.size());
    }
    
    /**
     * 
     * @param messageId : If the chatQueue have new events that bigger this id 
     * @return all messages that bigger than parameter id. 
     */
    @Lock(LockType.READ)
    public static List<Message> readLastEvents(Integer messageId){
        List<Message> newMessageList = new ArrayList<Message>();
        if(messageId.compareTo(Constant.getCurrentValue()) > 0 ){ 
            return newMessageList;
        }
        Integer counter = new Integer(messageId.intValue() + 1);
        while(null != chatMessageQueue.get(counter)){
            newMessageList.add(chatMessageQueue.get(counter));
            counter++;
        }
        return newMessageList;
    }
     
}
