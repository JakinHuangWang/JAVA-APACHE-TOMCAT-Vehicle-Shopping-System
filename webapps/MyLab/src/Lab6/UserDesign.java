package Lab6;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import model.*;

public class UserDesign extends HttpServlet{
     public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
            
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
          
      out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n");
      out.println("<html>");
      String modelName;
      double baseprice = 0, finalprice;
      int counter = 0;
          
      out.println("<style>");
      out.println("table, th, td{border: 1px solid blue;border-collapse:collapse;}");
      out.println("</style>");
          
      out.println("<form method=POST action=\"src/Lab6/ExportToExcel.jsp\">");
      out.println("<table>");
      Enumeration e = request.getParameterNames();
      while(e.hasMoreElements()){
            Object obj = e.nextElement();
            String fieldName = (String)obj;
            String fieldValue = request.getParameter(fieldName);
            counter++;
            if(fieldValue.contains("$"))
                baseprice += Double.parseDouble(fieldValue.split("\\$")[1].strip());
          out.println("<TR><TD>" + fieldName + "</TD><TD align=right>" + fieldValue + "</TD></TR>");
          out.println("<input type=\"hidden\" name=\""+fieldName+"\" value=\""+fieldValue+"\">");
      }
      out.println("<TR><TD>" + "Final Price</TD><TD align=right>$" + baseprice + "</TD></TR>");
      out.println("<input type=\"hidden\" name=\"Final Price\" value="+baseprice+">");
      out.println("</table>");
      out.println("<input type=\"submit\" value=\"Download Excel File\">");
      out.println("</form>");
      out.println("</html>");
      out.close();
      }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                throws ServletException, IOException {
        doGet(request, response);
    }
}