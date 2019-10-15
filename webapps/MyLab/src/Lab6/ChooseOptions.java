package Lab6;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Adapter.*;
import model.*;

public class ChooseOptions extends HttpServlet {
  
  public Automobile BuildModel(String filename){
    int lineCount = 0;
    boolean eof = false;
    Automobile auto = new Automobile();

    try{
    BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath(filename)));
    while(!eof) {
         lineCount++;
         if(br.readLine() == null) {
              eof = true;
              }
         }
         eof = false;
    br.close();
    br = new BufferedReader(new FileReader(getServletContext().getRealPath(filename)));
    auto = new BuildAuto().ConstructAutoWeb(br, lineCount, filename);
    }catch(IOException e){
        System.err.println(e);
        }
    return auto;
  }
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String modelName = request.getParameter("Param");
    Automobile auto = BuildModel(modelName+".txt");
    double baseprice = auto.getBaseprice();
    
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n");
    out.println("<style>");
    out.println("table, th, td{text-align: center;border: 1px solid black;border-collapse:collapse;}");
    out.println("</style>");
    out.println("<table>");
    out.println("<form method=POST action=\"/MyLab/UserDesign\">");
    out.println("<TR><TD><strong>Make/Model</strong></TD>");
    out.println("<TD>"+auto.getName()+"</TD></TR>");
    out.println("<TR><TD><strong>BasePrice</strong></TD>");
    out.println("<TD>"+baseprice+"</TD></TR>");
    out.println("<input type=\"hidden\" id=ModelID name=Model/Make value=\""+auto.getName()+"\">");
    out.println("<input type=\"hidden\" name=\"baseprice\""+ "value=$"+baseprice+">");
    for(OptionSet opset:auto.getOpset()){
      if(!opset.getName().isEmpty()){
      out.println("<TR><strong><TD>" + opset.getName() + "</strong></TD>");
         out.println("<TD><select name=\"" + opset.getName() + "\">");
        for(Option opt:opset.getOpt())
            out.println("<option name=\"" + opt.getName() + "\">" + opt.toString() +  "</option>");
      out.println("</select>");
      out.println("</TD></TR>");
      }
    }
    out.println("<TR><TD colspan=\"2\"><input type=\"submit\"></TD></TR>");
    out.println("<img src="+modelName+".jpg style=\"width:150px;height:150px;\" align=\"left\">");
    out.println("</form></table>");
    out.println("</BODY></HTML>");
    }
    
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                throws ServletException, IOException {
        doGet(request, response);
    }
}
