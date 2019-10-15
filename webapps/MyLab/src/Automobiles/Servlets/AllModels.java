package Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Adapter.*;
import model.*;


public class AllModels extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            		throws ServletException, IOException {
		response.setContentType("text/html");
		
		BuildAuto ba = new BuildAuto();
		ba.BuildAuto("FordWagonZTW.txt");
		ba.BuildAuto("TeslaModel3.txt");
		ba.BuildAuto("ToyotaCorolla.txt");
		
		PrintWriter out = response.getWriter();
		String docType =
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
						"Transitional//EN\">\n";
		out.println(docType +
				"<HTML>\n" +
				"<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1>Hello</H1>\n");
		out.println("<select name=\"cars\">");
		for(String modelName:ba.getAlMap().keySet())
			out.println("<option>" + modelName + "</option>");
		out.println("</select>");
		out.println("</BODY></HTML>");
}

}
