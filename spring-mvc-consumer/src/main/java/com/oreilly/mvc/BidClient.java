package com.oreilly.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.oreilly.ws.generated.BidRequest;
import com.oreilly.ws.generated.BidResponse;

@Component
public class BidClient extends WebServiceGatewaySupport {
	
	@Autowired
	public BidClient(Jaxb2Marshaller marshaller) {
		this.setMarshaller(marshaller);
		this.setUnmarshaller(marshaller);
	}
	
	public BidResponse getBid(String projectName) {
		BidRequest request = new BidRequest();
		request.setProjectName(projectName);
		
		WebServiceTemplate template = super.getWebServiceTemplate();
		return (BidResponse) template.marshalSendAndReceive("http://localhost:8081/ws/", request);
	}
}
