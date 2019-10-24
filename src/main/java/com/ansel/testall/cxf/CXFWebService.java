package com.ansel.testall.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

 
@WebService
public interface CXFWebService {
 
    @WebMethod
    String doSomething(@WebParam(name = "requestInfo")String RequestInfo);
    
}

