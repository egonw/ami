package org.xmlcml.ami2.plugins.phylotree.nexml;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.graphics.svg.SVGElement;
import org.xmlcml.graphics.svg.SVGG;
import org.xmlcml.xml.XMLUtil;

public class NexmlTrees extends NexmlElement {

	private final static Logger LOG = Logger.getLogger(NexmlTrees.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}

	public final static String TAG = "trees";
	private List<NexmlTree> treeList;

	/** constructor.
	 * 
	 */
	public NexmlTrees() {
		super(TAG);
	}

	public void setOtus(String otus) {
		this.addAttribute(new Attribute("otus", otus));
	}

	public List<NexmlTree> getOrCreateTreeList() {
		if (treeList == null) {
			List<Element> elementList = XMLUtil.getQueryElements(this, "./*[local-name()='tree']");
			treeList = new ArrayList<NexmlTree>();
			for (Element element : elementList) {
				treeList.add((NexmlTree) element);
			}
		}
		return treeList;
	}

	public int size() {
		getOrCreateTreeList();
		return treeList.size();
	}

	public NexmlTree get(int i) {
		getOrCreateTreeList();
		return treeList.get(i);
	}

	public SVGElement createSVG() {
		getOrCreateTreeList();
		SVGG g = new SVGG();
		for (NexmlTree tree : treeList) {
			g.appendChild(tree.createSVG());	
		}
		return g;
	}
	
}
