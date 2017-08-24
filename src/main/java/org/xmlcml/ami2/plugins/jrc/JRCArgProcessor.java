package org.xmlcml.ami2.plugins.jrc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.ami2.plugins.AMIArgProcessor;
import org.xmlcml.cproject.args.ArgIterator;
import org.xmlcml.cproject.args.ArgumentOption;
import org.xmlcml.cproject.files.ContentProcessor;
import org.xmlcml.cproject.files.ResultElement;
import org.xmlcml.cproject.files.ResultsElement;

/** 
 * Processes commandline arguments.
 * 
 * @author pm286
 */
public class JRCArgProcessor extends AMIArgProcessor {
	
	public static final Logger LOG = Logger.getLogger(JRCArgProcessor.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}
	
	protected List<String> words;
	protected Set<String> materials;

	HashSet<String> nms = new HashSet<String>() {{
		add("NM-100");
		add("NM-101");
		add("NM-102");
		add("NM-103");
		add("NM-104");
		add("NM-105");
		add("NM-110");
		add("NM-111");
		add("NM-112");
		add("NM-113");
		add("NM-200");
		add("NM-201");
		add("NM-202");
		add("NM-203");
		add("NM-204");
		add("NM-211");
		add("NM-212");
		add("NM-213");
		add("NM-220");
		add("NM-300");
		add("NM-300K");
		add("NM-400");
		add("NM-401");
		add("NM-402");
		add("NM-403");
	}};
	
	public JRCArgProcessor() {
		super();
	}

	public JRCArgProcessor(String[] args) {
		this();
		parseArgs(args);
	}

	public JRCArgProcessor(String argString) {
		this(argString.split(WHITESPACE));
	}

	// =============== METHODS ==============

	public void parseSimple(ArgumentOption option, ArgIterator argIterator) {
		// no special argument
	}
	
	public void countWords(ArgumentOption option) {
		words = currentCTree.extractWordsFromScholarlyHtml();
		materials = new HashSet<String>();
		for (String word : words) {
			if (word.startsWith("NM-")) {
				word = normalizeWord(word);
				if (nms.contains(word.toUpperCase())) {
					materials.add(word);
				} else {
					System.out.println("word: " + word);
				}
			} else if (word.startsWith("NM") && word.length()>2 && Character.isDigit(word.charAt(2))) { // NMxxx
				word = normalizeWord(word);
				String refword = "NM-" + word.substring(2);
				if (nms.contains(refword.toUpperCase())) {
					materials.add(word);
				} else {
					System.out.println("word: " + word);
				}
			}
		}
	}

	private String normalizeWord(String word) {
		String original = word;
		word = word.trim();
		String result = word;
		boolean changeMade = true;
		while (changeMade) {
			changeMade = false;
			if (result.endsWith(".") || 
			    result.endsWith(",") || 
			    result.endsWith(":") || 
			    result.endsWith(";") || 
			    result.endsWith("]") || 
	            result.endsWith(")")) {
				result = result.substring(0, result.length()-1);
				changeMade = true;
			}
		}
		// if (!original.equals(result)) System.out.println("'" + original + "' -> '" + result + "'");
		return result;
	}

	public void outputWordCounts(ArgumentOption option) {
		ContentProcessor contentProc = getOrCreateContentProcessor();

		ResultsElement resultsElement = new ResultsElement();
		for (String material : materials) {
			ResultElement resultElement = new ResultElement();
			resultElement.setValue("material", material);
			resultsElement.appendChild(resultElement);
		}
		resultsElement.setTitle("jrc");
		contentProc.addResultsElement(resultsElement);

		contentProc.createResultsDirectoriesAndOutputResultsElement(option.getName());
	}
	
	// =============================

	@Override
	/** parse args and resolve their dependencies.
	 * 
	 * (don't run any argument actions)
	 * 
	 */
	public void parseArgs(String[] args) {
		super.parseArgs(args);
	}

	
}
