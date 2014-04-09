package gr.ekt.enhancedoaiserver.bte;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import ORG.oclc.oai.server.crosswalk.Crosswalk;
import ORG.oclc.oai.server.verb.CannotDisseminateFormatException;

public class ElstatRawCrosswalk extends Crosswalk {

    public ElstatRawCrosswalk(String schema)
    {
        super(schema);
    }

    public boolean isAvailableFor(Object nativeItem)
    {
        // We have RAW for everything
        return true;
    }

    /* (non-Javadoc)
     * @see ORG.oclc.oai.server.crosswalk.Crosswalk#createMetadata(java.lang.Object)
     */
    @Override
    public String createMetadata(Object arg0)
            throws CannotDisseminateFormatException {
        // TODO Auto-generated method stub

        ElstatRecord record = (ElstatRecord)arg0;

        Element rawElement = DocumentFactory.getInstance().createElement(new QName("mods"));
        rawElement.addNamespace("", "http://www.loc.gov/mods/v3");
        rawElement.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rawElement.addAttribute("xsi:schemaLocation", "http://www.loc.gov/mods/v3 http://www.loc.gov/standards/mods/v3/mods-3-4.xsd");
        for (Object elementObj : record.getXmlRecord().elements()){
            rawElement.add(((Element) elementObj).createCopy());
        }
        //System.out.println(rawElement.asXML());

        return rawElement.asXML();
    }


}
