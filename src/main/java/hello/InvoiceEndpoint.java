package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.SendInvoiceRequest;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.SendInvoiceResponse;


@Endpoint
public class InvoiceEndpoint {
	private static final String NAMESPACE_URI = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100";

	private InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceEndpoint(InvoiceRepository countryRepository) {
		this.invoiceRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendInvoiceRequest")
	@ResponsePayload
	public SendInvoiceResponse sendInvoice(@RequestPayload SendInvoiceRequest request) {
		SendInvoiceResponse response = new SendInvoiceResponse();
		response.setResult(invoiceRepository.sendInvoice(request.getCrossIndustryInvoice()));

		return response;
	}
}
