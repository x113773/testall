package com.ansel.testall.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.AxisProperties;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CXFController {

	@RequestMapping(value = "/cxf", method = RequestMethod.GET)
	public Map<String, Object> testCXF() {
		return main3();
	}

	/**
	 * 0.axis方式
	 * 
	 * @return
	 */
	public Map<String, Object> main0() {
		Map<String, Object> result = new HashMap<String, Object>();
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			String url = "https://localhost:8443/webservice/doSomething";// 接口地址
			String xmltr = makeXmlDoSomethingServices();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(new QName("http://cxf.testall.ansel.com/", "doSomething"));// WSDL里面描述的接口名称
			call.addParameter("requestInfo", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 命名空间
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型6
			AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
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

	/**
	 * 1.代理类工厂的方式,需要拿到对方的接口地址
	 */
	public Map<String, Object> main1() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 接口地址
			String address = "https://localhost:8443/webservice/doSomething?wsdl";
			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(CXFWebService.class);
			// 创建一个代理接口实现
			CXFWebService cws = (CXFWebService) jaxWsProxyFactoryBean.create();
			/*****************************************************/
			Client proxy = ClientProxy.getClient(cws);
			HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
			TLSClientParameters tlsParams = conduit.getTlsClientParameters();
			if (tlsParams == null) {
				tlsParams = new TLSClientParameters();
			}
			tlsParams.setDisableCNCheck(true);
			// 设置keystore
			tlsParams.setKeyManagers(ClientUtils.getKeyManagers());
			// 设置信任证书
			tlsParams.setTrustManagers(ClientUtils.getTrustManagers());
			conduit.setTlsClientParameters(tlsParams);
			/*****************************************************/
			// 数据准备
			String xml = makeXmlDoSomethingServices();
			// 调用代理接口的方法调用并返回结果
			String results = cws.doSomething(xml);
			System.out.println("返回结果:" + results);
			result.put("success", true);
			result.put("message", results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 2：动态调用
	 */
	public Map<String, Object> main2() {
		Map<String, Object> result = new HashMap<String, Object>();
		// 创建动态客户端
		System.setProperty("javax.net.ssl.trustStore", "mykeys.jks"); 
		System.setProperty("javax.net.ssl.trustStorePassword","letmein");//密码
		System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol"); 
		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("https://localhost:8443/webservice/doSomething?wsdl");
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
		// PASS_WORD));

		/*****************************************************/
//		HTTPConduit conduit = (HTTPConduit) client.getConduit();
//		TLSClientParameters tlsParams = conduit.getTlsClientParameters();
//		if (tlsParams == null) {
//			tlsParams = new TLSClientParameters();
//		}
//		tlsParams.setDisableCNCheck(true);
//		// 设置keystore
//		tlsParams.setKeyManagers(ClientUtils.getKeyManagers());
//		// 设置信任证书
//		tlsParams.setTrustManagers(ClientUtils.getTrustManagers());
//		conduit.setTlsClientParameters(tlsParams);
		/*****************************************************/
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("doSomething", makeXmlDoSomethingServices());
			System.out.println("返回数据:" + objects[0]);
			result.put("success", true);
			result.put("message", objects[0]);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Object> main3() {
		Map<String, Object> result = new HashMap<String, Object>();
		// wsdl网络路径
		URL url=null;
		try {
			url = new URL("https://localhost:8443/webservice/doSomething?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 服务描述中服务端点的限定名称 两个参数分别为 命名空间 服务名
		QName qName = new QName("http://cxf.testall.ansel.com/", "doSomething");
		// 创建服务对象
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qName);
		// 获得Hiservice的实现类对象
		CXFWebService cws = service.getPort(qName, CXFWebService.class);
		// 调用WebService方法
		result.put("message", cws.doSomething(makeXmlDoSomethingServices()));
		return result;
	}

	private String makeXmlDoSomethingServices() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		sb.append("<QUERYAPPORGREQ>");
		sb.append("</QUERYAPPORGREQ>");
		return sb.toString();
	}
}
