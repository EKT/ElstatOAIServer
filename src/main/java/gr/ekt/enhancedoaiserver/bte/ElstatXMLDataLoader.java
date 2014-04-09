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
/**
 *
 */
package gr.ekt.enhancedoaiserver.bte;

import gr.ekt.bte.core.DataLoadingSpec;
import gr.ekt.bte.core.RecordSet;
import gr.ekt.bte.core.StringValue;
import gr.ekt.bte.dataloader.FileDataLoader;
import gr.ekt.bte.exceptions.EmptySourceException;
import gr.ekt.bte.exceptions.MalformedSourceException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author kstamatis
 *
 */
public class ElstatXMLDataLoader extends FileDataLoader {

    Document document = null;

    /**
     *
     */
    public ElstatXMLDataLoader(String filename) throws EmptySourceException, IOException {
        ClassPathResource cpr = new ClassPathResource(filename);
        File fl = cpr.getFile();
        super.setFilename(fl.getCanonicalPath());

        SAXReader reader = new SAXReader();
        try {
            document = reader.read(fl);
        } catch (DocumentException e) {
            throw new EmptySourceException("Could not read: " + filename);
        }
    }

    /* (non-Javadoc)
     * @see gr.ekt.bte.core.DataLoader#getRecords()
     */
    public RecordSet getRecords() throws MalformedSourceException {

        throw new RuntimeException();
    }

    /* (non-Javadoc)
     * @see gr.ekt.bte.core.DataLoader#getRecords(gr.ekt.bte.core.DataLoadingSpec)
     */
    public RecordSet getRecords(DataLoadingSpec spec)
            throws MalformedSourceException {

        Element root = document.getRootElement();
        int lastRecord = 0;
        if (spec.getOffset() + spec.getNumberOfRecords() > root.elements().size()) {
            lastRecord = root.elements().size();
        }
        else {
            lastRecord = spec.getOffset() + spec.getNumberOfRecords();
        }

        List<Element> recordList = null;

        if (spec.getIdentifier() == null) {
            recordList = root.elements().subList(spec.getOffset(), lastRecord);
        }
        else {
            //Setup xpath query
            Map<String, String> namespaceURIs = new HashMap<String, String>();
            namespaceURIs.put("mods", "http://www.loc.gov/mods/v3");
            //namespaceURIs.put("dc", "http://purl.org/dc/elements/1.1/");
            String id = spec.getIdentifier();
            if (id.indexOf(":") != -1) {
                id = id.substring(id.indexOf(":") + 1);
            }

            XPath xPath = DocumentHelper.createXPath("//mods:identifier[text()='" + id + "']");
            xPath.setNamespaceURIs(namespaceURIs);
            Node nd = xPath.selectSingleNode(document);
            recordList = new ArrayList<Element>();
            if (nd != null) {
                recordList.add(nd.getParent());
            }
        }

        RecordSet rs = new RecordSet();

        for (Element recordElement : recordList){
            ElstatRecord record = new ElstatRecord(recordElement);

            record.addValue("isDeleted", new StringValue("false"));
            //record.addValue("datestamp", new StringValue(new Date));
            record.addValue("setSpecs", new StringValue("Book"));

            rs.addRecord(record);
        }

        return rs;
    }

}
