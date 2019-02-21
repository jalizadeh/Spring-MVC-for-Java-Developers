package com.oreilly.ws;

import java.math.BigDecimal;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.oreilly.ws.generated.Bid;
import com.oreilly.ws.generated.BidRequest;
import com.oreilly.ws.generated.BidResponse;

@Endpoint
public class BidEndpoint {

	@ResponsePayload
	@PayloadRoot(namespace="http://www.oreilly.org/Bids",localPart="BidRequest")
	public BidResponse getBid(@RequestPayload BidRequest request) {
		System.out.println(request.getProjectName());
		
		BidResponse response = new BidResponse();
		Bid bid = new Bid();
		bid.setQuote(new BigDecimal("1000"));
		try {
			bid.setValidToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		response.setBid(bid);
		return response;
	}
}
