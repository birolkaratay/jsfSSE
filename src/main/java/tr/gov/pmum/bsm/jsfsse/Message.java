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
public class Message implements Serializable {

    private Integer id;
    private String userName;
    private String messageText;

    public Message(Integer id, String userName, String messageText) {
        this.id = id;
        this.userName = userName;
        this.messageText = messageText;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    
}
