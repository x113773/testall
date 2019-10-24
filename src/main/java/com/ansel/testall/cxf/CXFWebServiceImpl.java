package com.ansel.testall.cxf;

import javax.jws.WebService;

import org.springframework.stereotype.Service;
 
@Service
@WebService(serviceName = "WebServiceDemoService", // 与接口中指定的name一致
        targetNamespace = "http://cxf.testall.ansel.com/"// 与接口中的命名空间一致,一般是接口的包名倒
)
public class CXFWebServiceImpl implements CXFWebService {
 
    @Override
    public String doSomething(String requestInfo) {
    	System.out.println(requestInfo);
        return requestInfo;
    }
 
 
}
 
