package com.oreilly.ws;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebserviceConfigurer extends WsConfigurerAdapter {
	
	@Bean
	public XsdSchema bidSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Bids.xsd"));
	}

	@Bean(name="bids")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("BidsPort");
		definition.setLocationUri("/ws");
		definition.setSchema(schema);
		return definition;
	}
	
	
	//It processes the request coming to the server
	// and routs them to the appropriate endpoints
	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext context){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformSchemaLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}
}
