package com.ansel.testall.axis;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.AxisProperties;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AxisController {

	@RequestMapping(value = "/axis", method = RequestMethod.GET)
	public Map<String, Object> testAop2() {
		return ResponseFrom4A("1");
	}

	public Map<String, Object> ResponseFrom4A(String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		Service service = new Service();
		String name = "";
		String password = "";
		try {
			Call call = (Call) service.createCall();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
			String timeStamp = sdf.format(new Date());// 当前系统时间
			String url = "https://localhost:8443/services/AxisWebService";// 接口地址
			String xmltr = makeXmlUpdateSomethingServices(type, timeStamp, name, password);
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName("updateSomething");// WSDL里面描述的接口名称
			call.addParameter("requestInfo", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 命名空间
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型6
			  AxisProperties.setProperty("axis.socketSecureFactory",  "org.apache.axis.components.net.SunFakeTrustSocketFactory");
			String callBack = (String) call.invoke(new Object[] { xmltr });
			result.put("success", true);
			result.put("message", callBack);
			return result;

		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "出现异常！");
			e.printStackTrace();
		}
		return result;
	}

	private String makeXmlUpdateSomethingServices(String type, String timeStamp, String name, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		sb.append("<QUERYAPPORGREQ>");
		sb.append("</QUERYAPPORGREQ>");
		return sb.toString();
	}
}
