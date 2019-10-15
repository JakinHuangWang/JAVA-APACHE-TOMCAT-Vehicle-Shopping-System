package Lab6;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import model.*;


public class ListModels extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        
    response.setContentType("text/html");
    
    String [] CarOptions = {"FordWagonZTW", "LexusES", "TeslaModel3", "ToyotaCorolla"};
    
    PrintWriter writer = response.getWriter();
    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                      "Transitional//EN\">\n";
                writer.println(docType +
                                "<HTML>\n" +
                                "<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
                                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                                "<H2 FGCOLOR=\"#808080\">Select Car Models</H2>\n");
                writer.println("<table>");
                writer.println("<TR><TD><form method=POST action=\"/MyLab/ChooseOptions\"></TD></TR>");
                writer.println("<TR><TD><select name=\"Param\">");
                for(String name:CarOptions)
                    writer.println("<option name=" + name + ">" + name + "</option>");
                writer.println("</select></TD></TR>");
                writer.println("<TR><TD><input type=\"submit\"></TD></TR>");
                writer.println("</form></table>");
                writer.println("</BODY></HTML>");
  }
    
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                throws ServletException, IOException {
        doGet(request, response);
    }
}
