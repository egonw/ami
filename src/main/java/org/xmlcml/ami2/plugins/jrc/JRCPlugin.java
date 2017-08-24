package org.xmlcml.ami2.plugins.jrc;

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
public class JRCPlugin extends AMIPlugin {

	private static final Logger LOG = Logger.getLogger(JRCPlugin.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}

//	private SimpleArgProcessor argProcessor;
	
	public JRCPlugin(String[] args) {
		super();
		this.argProcessor = new JRCArgProcessor(args);
	}
	
	public static void main(String[] args) {
//		System.out.println("Main started...");
		JRCArgProcessor argProcessor = new JRCArgProcessor(args);
		argProcessor.runAndOutput();
	}

	public JRCPlugin(String args) {
		super();
		this.argProcessor = new JRCArgProcessor(args);
	}

	

}
