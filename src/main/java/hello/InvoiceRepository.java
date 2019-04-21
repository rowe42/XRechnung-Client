package hello;

import java.io.StringWriter;
import java.util.logging.Level;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoice;

@Component
public class InvoiceRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceRepository.class);

    public String sendInvoice(CrossIndustryInvoice invoice) {
        if (invoice == null) {
            return "empty";
        }
        
        Node edc = (Node) invoice.getExchangedDocumentContext().getAny();
        LOG.info("\nExchangedDocumentContext:\n-----\n " + toString(edc));

        Node ed = (Node) invoice.getExchangedDocument().getAny();
        LOG.info("\n\nExchangedDocument:\n-----\n " + toString(ed));

        Node sctt = (Node) invoice.getSupplyChainTradeTransaction().getAny();
        LOG.info("\n\nSupplyChainTradeTransaction:\n-----\n " + toString(sctt));
        return "accepted";
    }

    private static String toString(Node node) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(writer));
            return writer.toString();
        } catch (TransformerConfigurationException ex) {
            java.util.logging.Logger.getLogger(InvoiceRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            java.util.logging.Logger.getLogger(InvoiceRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
