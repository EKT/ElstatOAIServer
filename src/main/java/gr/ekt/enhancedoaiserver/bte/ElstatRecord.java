/**
 * Copyright (c) 2007-2014, National Documentation Centre (EKT, www.ekt.gr)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *     Neither the name of the National Documentation Centre nor the
 *     names of its contributors may be used to endorse or promote
 *     products derived from this software without specific prior written
 *     permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
