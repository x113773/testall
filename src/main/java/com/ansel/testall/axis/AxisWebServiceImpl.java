package com.ansel.testall.axis;

import org.springframework.stereotype.Service;
 
@Service
public class AxisWebServiceImpl implements AxisWebService {
 
    @Override
    public String updateSomething(String requestInfo) {
    	System.out.println(requestInfo);
        return requestInfo;
    }
 
 
}
 
