<%@ page contentType="text/html"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Comparing Apples and Oranges</TITLE>
</HEAD>
  <BODY>
      <CENTER>
          <H2>Your Automobile Selection</H2>
          <TABLE BORDER=1>
    <%
    Enumeration e = request.getParameterNames();
    if (e != null){
      response.setContentType("application/vnd.ms-excel");
      response.setHeader("Content-Disposition", "inline; filename=CarConfiguration.xls");
    }
      while(e.hasMoreElements()){
            Object obj = e.nextElement();
            String fieldName = (String)obj;
            String fieldValue = request.getParameter(fieldName);
    %>
    <TR>
      <TH><%=fieldName%></TH>
      <TD style="text-align:left"><%=fieldValue%></TD>
    </TR>  
    <%}%>
        </TABLE>
      </CENTER>
    </BODY>
</HTML>
