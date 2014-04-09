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
