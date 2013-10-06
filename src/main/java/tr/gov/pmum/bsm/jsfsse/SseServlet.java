package tr.gov.pmum.bsm.jsfsse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author birol karatay
 */
@WebServlet(name = "SseServlet", urlPatterns = {"/SseServlet"})
public class SseServlet extends HttpServlet {

    DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    
    /**
     * Do nothing
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Every 1/20 second Thread check Queues for new Events
     * If there is a new Event it will be sent to the client. 
     * 
     * A counter count sent events ids.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Besides "text/event-stream;", Chrome also needs charset, otherwise
        // does not work
        // "text/event-stream;charset=UTF-8"
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        PrintWriter out = response.getWriter();
        Integer lastSentMessageId = new Integer(Constant.getCurrentValue().intValue());
         
        while (true) {
            List<Message> lastEvents = Queues.readLastEvents(lastSentMessageId);
            for (Message message : lastEvents) {
            out.print("id: " + "sseChannel" + "\n");
            out.print("data: (" + df.format(System.currentTimeMillis()) + 
                             ") " + message.getUserName() + " Said : " + message.getMessageText() + "\n\n");
            out.flush();    
            lastSentMessageId = message.getId();
            }
            
            // out.close(); //Do not close the writer!
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
