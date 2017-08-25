package org.xmlcml.ami2.plugins.wp;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.ami2.plugins.AMIPlugin;

/** test plugin.
 * 
 * Very simple tasks for testing and tutorials.
 * 
 * @author pm286
 *
 */
public class WikiPathwaysPlugin extends AMIPlugin {

	private static final Logger LOG = Logger.getLogger(WikiPathwaysPlugin.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}

//	private SimpleArgProcessor argProcessor;
	
	public WikiPathwaysPlugin(String[] args) {
		super();
		this.argProcessor = new WikiPathwaysArgProcessor(args);
	}
	
	public static void main(String[] args) {
		WikiPathwaysArgProcessor argProcessor = new WikiPathwaysArgProcessor(args);
		argProcessor.runAndOutput();
	}

	public WikiPathwaysPlugin(String args) {
		super();
		this.argProcessor = new WikiPathwaysArgProcessor(args);
	}

	

}
