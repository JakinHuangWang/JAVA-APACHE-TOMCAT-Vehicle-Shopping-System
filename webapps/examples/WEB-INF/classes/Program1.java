/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Program1 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String[][] variables =
        { { "AUTH_TYPE", request.getAuthType() },
            { "CONTENT_LENGTH",
                String.valueOf(request.getContentLength()) },
            { "CONTENT_TYPE", request.getContentType() },
            { "DOCUMENT_ROOT",
                getServletContext().getRealPath("/") },
            { "PATH_INFO", request.getPathInfo() },
            { "PATH_TRANSLATED", request.getPathTranslated() },
            { "QUERY_STRING", request.getQueryString() },
            { "REMOTE_ADDR", request.getRemoteAddr() },
            { "REMOTE_HOST", request.getRemoteHost() },
            { "REMOTE_USER", request.getRemoteUser() },
            { "REQUEST_METHOD", request.getMethod() },
            { "SCRIPT_NAME", request.getServletPath() },
            { "SERVER_NAME", request.getServerName() },
            { "SERVER_PORT",
                String.valueOf(request.getServerPort()) },
            { "SERVER_PROTOCOL", request.getProtocol() },
            { "SERVER_SOFTWARE",
                getServletContext().getServerInfo() }
        };
        String title = "Servlet Example: Showing CGI Variables";
        out.println(
                    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                    "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                    "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                    "<TR BGCOLOR=\"#FFAD00\">\n" +
                    "<TH>CGI Variable Name<TH>Value");
        for(int i=0; i<variables.length; i++) {
            String varName = variables[i][0];
            String varValue = variables[i][1];
            if (varValue == null)
                varValue = "<I>Not specified</I>";
            out.println("<TR><TD>" + varName + "<TD>" + varValue);
        }
        out.println("</TABLE></BODY></HTML>");
    }
    
    
    /** POST and GET requests handled identically. */
    
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}
