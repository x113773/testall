package com.ansel.testall.axis;

import org.apache.axis.transport.http.AxisServlet;
import org.springframework.stereotype.Component;

 
@Component
@javax.servlet.annotation.WebServlet(
        urlPatterns =  "/services/*",
        loadOnStartup = 1,
        name = "AxisServlet"
)
public class WebServlet extends AxisServlet {


}
