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
 * View layer for FacesContext
 * @author birol karatay
 */
@SessionScoped
@Named
public class MessageView implements Serializable {

    private Message userMessage = new Message();

    public Message getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(Message userMessage) {
        this.userMessage = userMessage;
    }

    public void sendMessage() {
        Queues.addMessage(new Message(Constant.getNextValue(), userMessage.getUserName(), userMessage.getMessageText()));
        userMessage.setMessageText("");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information", "Your Message has been sent!"));
    }
}
