//package com.ansel.testall.https;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HttpsConfiguration {
//
//	 @Bean
//	    public EmbeddedServletContainerFactory servletContainer() {
//	        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory(){
//	            protected void postProcessContext(Context context) {
//	                SecurityConstraint securityConstraint = new SecurityConstraint();
//	                securityConstraint.setUserConstraint("CONFIDENTIAL");
//	                SecurityCollection collection = new SecurityCollection();
//	                collection.addPattern("/*");
//	                securityConstraint.addCollection(collection);
//	                context.addConstraint(securityConstraint);
//	            }
//	        };
//	        tomcat.addAdditionalTomcatConnectors(httpConnector());
//	        return tomcat;
//	    }
//
//	    @Bean
//	    public Connector httpConnector(){
//	        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	        connector.setScheme("http");
//	        connector.setPort(8080);//表示用8080端口来供http访问
//	        connector.setSecure(false);
//	        connector.setRedirectPort(8443);//自动重定向到8443端口
//	        return connector;
//	    }
//}