/**
 *
 */
package gr.ekt.enhancedoaiserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import ORG.oclc.oai.server.verb.NoSetHierarchyException;
import ORG.oclc.oai.server.verb.OAIInternalServerError;
import gr.ekt.oaicatbte.BTECatalog;

/**
 * @author kstamatis
 *
 */
public class ElstatInMemoryCatalog extends BTECatalog {

    Map<String, String> sets = new HashMap<String, String>();


    public ElstatInMemoryCatalog(Properties properties)
    {
        // Don't need to do anything
    }


    @Override
    public void close() {
        super.close();
    }


    /* (non-Javadoc)
     * @see gr.ekt.oaicatbte.BTECatalog#listAllSets()
     */
    @Override
    public Map<String, String> listAllSets() throws NoSetHierarchyException,
            OAIInternalServerError {
        return sets;
    }

    public void setSets(Map<String, String> sets) {
        this.sets = sets;
    }
}
