/**
 * 
 */
package gr.ekt.enhancedoaiserver.bte;

import gr.ekt.bte.core.AbstractModifier;
import gr.ekt.bte.core.MutableRecord;
import gr.ekt.bte.core.Record;
import gr.ekt.bte.core.StringValue;

/**
 * @author kstamatis
 *
 */
public class ElstatDatestampModifier extends AbstractModifier  {

	private String datestamp;

	/**
	 * 
	 */
	public ElstatDatestampModifier() {
		super("ElstatDatestampModifier");
	}

	@Override
	public Record modify(MutableRecord rec) {
		// TODO Auto-generated method stub
		
		rec.addValue("datestamp", new StringValue(datestamp));
		
		return rec;
	}

	public void setDatestamp(String datestamp) {
		this.datestamp = datestamp;
	}
}
