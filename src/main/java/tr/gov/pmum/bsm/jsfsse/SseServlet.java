/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.pmum.bsm.jsfsse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author birol
 */
@WebServlet(name = "SseServlet", urlPatterns = {"/SseServlet"})
public class SseServlet extends HttpServlet {

    
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Besides "text/event-stream;", Chrome also needs charset, otherwise
        // does not work
        // "text/event-stream;charset=UTF-8"
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        PrintWriter out = response.getWriter();
        Integer sonGonderilenMesajId = new Integer(MesajListener.checkMesajIdCounter().intValue());
         
        while (true) {
            List<Mesaj> gonderilecekler = MesajListener.mesajOku(sonGonderilenMesajId);
            for (Mesaj mesaj : gonderilecekler) {
            out.print("id: " + "kanal" + "\n");
            out.print("data: " +new Date(System.currentTimeMillis()).toString() + 
                             " :" + mesaj.getKullanici() + " Dedi: " + mesaj.getIcerik() + "\n\n");
            out.flush();    
            sonGonderilenMesajId = mesaj.getId();
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
