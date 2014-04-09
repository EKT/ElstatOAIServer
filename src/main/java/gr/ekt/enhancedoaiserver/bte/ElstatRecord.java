package gr.ekt.enhancedoaiserver.bte;

import java.util.ArrayList;
import java.util.List;

import gr.ekt.bte.core.StringValue;
import gr.ekt.bte.core.Value;
import gr.ekt.bte.record.MapRecord;
import org.dom4j.Element;

/**
 * Created by kutsurak on 31/01/2014.
 */
public class ElstatRecord extends MapRecord {
    private Element xmlRecord;

    ElstatRecord(Element dcRecord) {
        this.xmlRecord = dcRecord;
    }

    public Element getXmlRecord() {
        return xmlRecord;
    }

    public void setXmlRecord(Element dcRecord) {
        this.xmlRecord = dcRecord;
    }

    @Override
    public List<Value> getValues(String field) {

        List<Value> ret = super.getValues(field);
        if (ret != null){
            return ret;
        }

        List els = xmlRecord.elements(field);
        if (els!=null && els.size()>0){
            ret = new ArrayList<Value>();
            for (Object el : els){
                Element element = (Element)el;
                ret.add(new StringValue(element.getText()));
            }

            return ret;
        }
        return null;
    }


}
