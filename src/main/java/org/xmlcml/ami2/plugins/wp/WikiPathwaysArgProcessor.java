package org.xmlcml.ami2.plugins.wp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
public class WikiPathwaysArgProcessor extends AMIArgProcessor {
	
	public static final Logger LOG = Logger.getLogger(WikiPathwaysArgProcessor.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}
	
	protected List<String> words;
	protected Map<String,String> matches; // match, exact

	HashSet<String> pathways = new HashSet<String>() {{
        add("WP2940");
        add("WP2911");
        add("WP2884");
        add("WP2865");
        add("WP2775");
        add("WP2712");
        add("WP2363");
        add("WP2361");
		add("WP2312");
        add("WP2279");
        add("WP2249");
        add("WP2199");
        add("WP2036");
		add("WP2032");
        add("WP2006");
        add("WP2005");
        add("WP2004");
        add("WP2002");
        add("WP2001");
        add("WP1982");
        add("WP1805");
        add("WP1772");
        add("WP1763");
        add("WP1545");
        add("WP1531");
		add("WP1471");
        add("WP1309");
        add("WP1308");
        add("WP1306");
        add("WP1299");
        add("WP1290");
        add("WP1288");
        add("WP1286");
        add("WP1283");
        add("WP1281");
        add("WP1274");
        add("WP1270");
        add("WP1262");
        add("WP1259");
        add("WP1255");
        add("WP1251");
        add("WP1247");
        add("WP1234");
        add("WP683");
        add("WP673");
		add("WP623");
        add("WP622");
        add("WP615");
		add("WP619");
        add("WP590");
        add("WP574");
        add("WP534");
        add("WP530");
        add("WP529");
        add("WP523");
        add("WP517");
		add("WP515");
        add("WP509");
        add("WP508");
        add("WP505");
		add("WP501");
        add("WP495");
        add("WP473");
        add("WP460");
        add("WP458");
        add("WP457");
        add("WP455");
        add("WP449");
        add("WP442");
        add("WP447");
        add("WP441");
        add("WP439");
        add("WP437");
        add("WP435");
        add("WP431");
        add("WP429");
        add("WP426");
		add("WP414");
        add("WP413");
        add("WP412");
		add("WP411");
        add("WP408");
		add("WP382");
        add("WP376");
        add("WP375");
        add("WP373");
        add("WP366");
        add("WP362");
        add("WP358");
        add("WP357");
        add("WP356");
        add("WP352");
        add("WP351");
        add("WP348");
		add("WP336");
        add("WP334");
        add("WP326");
        add("WP319");
        add("WP306");
        add("WP303");
        add("WP302");
		add("WP295");
        add("WP294");
        add("WP285");
        add("WP284");
        add("WP271");
        add("WP270");
        add("WP263");
		add("WP254");
		add("WP252");
        add("WP246");
        add("WP244");
		add("WP236");
		add("WP205");
        add("WP200");
        add("WP199");
		add("WP183");
		add("WP179");
        add("WP161");
		add("WP157");
        add("WP155");
        add("WP147");
        add("WP143");
        add("WP140");
		add("WP138");
        add("WP135");
		add("WP134");
        add("WP131");
        add("WP117");
		add("WP111");
		add("WP107");
		add("WP103");
        add("WP89");
        add("WP79");
        add("WP78");
        add("WP73");
        add("WP68");
        add("WP66");
        add("WP65");
        add("WP59");
        add("WP58");
		add("WP55");
		add("WP53");
        add("WP44");
        add("WP40");
        add("WP33");
        add("WP30");
        add("WP24");
        add("WP18");
        add("WP15");
        add("WP5");
        add("WP1");
	}};
	
	public WikiPathwaysArgProcessor() {
		super();
	}

	public WikiPathwaysArgProcessor(String[] args) {
		this();
		parseArgs(args);
	}

	public WikiPathwaysArgProcessor(String argString) {
		this(argString.split(WHITESPACE));
	}

	// =============== METHODS ==============

	public void parseSimple(ArgumentOption option, ArgIterator argIterator) {
		// no special argument
	}
	
	public void countWords(ArgumentOption option) {
		words = currentCTree.extractWordsFromScholarlyHtml();
		matches = new HashMap<String,String>();
		for (String word : words) {
			if (word.contains("WP") && word.length()>2) {
  			    String normalized = normalizeWord(word).toUpperCase();
  			    if (pathways.contains(normalized)) {
  			    	matches.put(normalized, word);
  			    } else if (normalized.length()>2) {
  			    	System.out.println("\t\tadd(\"" + normalized + "\");");
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
			    result.endsWith("’") ||
			    result.endsWith("\"") ||
			    result.endsWith("]") ||
	            result.endsWith(")")) {
				result = result.substring(0, result.length()-1);
				changeMade = true;
			}
		}
		changeMade = true;
		while (changeMade) {
			changeMade = false;
			if (result.startsWith("(") ||
		        result.startsWith("[") ||
			    result.startsWith("\"") ||
		        result.startsWith("‘")) {
				result = result.substring(1);
				changeMade = true;
			}
		}
		if (result.contains("_R") || result.contains("_r")) {
			String[] parts = result.toUpperCase().split("_R");
			try {
				Integer.parseInt(parts[1]); // is the second part an integer?
				// yes, it is. It's likely a revision, and will assume that anyway
				result = parts[0];
			} catch (Exception e) {}; // ok, not an integer
		} else if (result.contains("_")) {
			String[] parts = result.split("_");
			try {
				Integer.parseInt(parts[1]); // is the second part an integer?
				// yes, it is. It's likely a revision, and will assume that anyway
				result = parts[0];
			} catch (Exception e) {}; // ok, not an integer
		} else if (result.contains("-")) {
			String[] parts = result.split("-");
			try {
				Integer.parseInt(parts[1]); // is the second part an integer?
				// yes, it is. It's likely a revision, and will assume that anyway
				result = parts[0];
			} catch (Exception e) {}; // ok, not an integer
		}
		if (result.contains(":WP")) {
			String[] parts = result.split(":WP");
			try {
				Integer.parseInt(parts[1]); // is the second part an integer?
				// yes, it is. It's likely a revision, and will assume that anyway
				result = "WP" + parts[1];
			} catch (Exception e) {}; // ok, not an integer
		}
		if (result.toUpperCase().contains("INSTANCE/WP")) {
			String[] parts = result.toUpperCase().split("INSTANCE/WP");
			try {
				Integer.parseInt(parts[1]); // is the second part an integer?
				// yes, it is. It's likely a revision, and will assume that anyway
				result = "WP" + parts[1];
			} catch (Exception e) {}; // ok, not an integer
		}
		if (!original.equals(result)) System.out.println("'" + original + "' -> '" + result + "'");
		return result;
	}

	public void outputWordCounts(ArgumentOption option) {
		ContentProcessor contentProc = getOrCreateContentProcessor();

		ResultsElement resultsElement = new ResultsElement();
		for (String match : matches.keySet()) {
			ResultElement resultElement = new ResultElement();
			resultElement.setValue("match", match);
			resultElement.setValue("exact", matches.get(match));
			resultsElement.appendChild(resultElement);
		}
		resultsElement.setTitle("wp");
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
