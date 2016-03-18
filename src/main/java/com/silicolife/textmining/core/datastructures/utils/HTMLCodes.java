package com.silicolife.textmining.core.datastructures.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLCodes {
	
	private Map<String,String> htmltoTextCodes;
	private Pattern tags = Pattern.compile("(&.*?;)");

	
	public HTMLCodes()
	{
		this.htmltoTextCodes = new HashMap<String, String>();
		initHash();
	}
	
	public String cleanString(String str)
	{
		return getTags(str);
	}
	
	private String getTags(String line) {
		Matcher m = tags.matcher(line);
		String tag;
		while(m.find())
		{
			tag = m.group(1);
			if(htmltoTextCodes.containsKey(tag))
			{
				line = line.replaceAll(tag,htmltoTextCodes.get(tag));
			}
		}
		return line;
	}

	private void initHash() {
		this.htmltoTextCodes.put("&#8704;", "∀");
		this.htmltoTextCodes.put("&forall;", "∀");
		this.htmltoTextCodes.put("&#8706;", "∂");
		this.htmltoTextCodes.put("&part;", "∂");
		this.htmltoTextCodes.put("&#8707;", "∃");
		this.htmltoTextCodes.put("&exist;", "∃");
		this.htmltoTextCodes.put("&#8711;", "∇");
		this.htmltoTextCodes.put("&nabla;", "∇");
		this.htmltoTextCodes.put("&#8712;", "∈");
		this.htmltoTextCodes.put("&isin;", "∈");
		this.htmltoTextCodes.put("&#8715;", "∋");
		this.htmltoTextCodes.put("&ni;", "∋");
		this.htmltoTextCodes.put("&#8719;", "∏");
		this.htmltoTextCodes.put("&prod;", "∏");
		this.htmltoTextCodes.put("&#8721;", "∑");
		this.htmltoTextCodes.put("&sum;", "∑");
		this.htmltoTextCodes.put("&#8733;", "∝");
		this.htmltoTextCodes.put("&prop;", "∝");
		this.htmltoTextCodes.put("&#8869;", "⊥");
		this.htmltoTextCodes.put("&perp;", "⊥");
		this.htmltoTextCodes.put("&#913;", "Α");
		this.htmltoTextCodes.put("&Alpha;", "Α");
		this.htmltoTextCodes.put("&#914;", "Β");
		this.htmltoTextCodes.put("&Beta;", "Β");
		this.htmltoTextCodes.put("&#915;", "Γ");
		this.htmltoTextCodes.put("&Gamma;", "Γ");
		this.htmltoTextCodes.put("&#916;", "Δ");
		this.htmltoTextCodes.put("&Delta;", "Δ");	
		this.htmltoTextCodes.put("&#917;", "Ε");
		this.htmltoTextCodes.put("&Epsilon;", "Ε");
		this.htmltoTextCodes.put("&#918;", "Ζ");
		this.htmltoTextCodes.put("&Zeta;", "Ζ");
		this.htmltoTextCodes.put("&#919;", "Η");
		this.htmltoTextCodes.put("&Eta;", "Η");
		this.htmltoTextCodes.put("&#920;", "Θ");
		this.htmltoTextCodes.put("&Theta;", "Θ");
		this.htmltoTextCodes.put("&#921;", "Ι");
		this.htmltoTextCodes.put("&Iota;", "Ι");
		this.htmltoTextCodes.put("&#922;", "Κ");
		this.htmltoTextCodes.put("&Kappa;", "Κ");
		this.htmltoTextCodes.put("&#923;", "Λ");
		this.htmltoTextCodes.put("&Lambda;", "Λ");
		this.htmltoTextCodes.put("&#924;", "Μ");
		this.htmltoTextCodes.put("&Mu;", "Μ");
		this.htmltoTextCodes.put("&#925;", "Ν");
		this.htmltoTextCodes.put("&Nu;", "Ν");
		this.htmltoTextCodes.put("&#926;", "Ξ");
		this.htmltoTextCodes.put("&Xi;", "Ξ");
		this.htmltoTextCodes.put("&#927;", "Ο");
		this.htmltoTextCodes.put("&Omicron;", "Ο");
		this.htmltoTextCodes.put("&#928;", "Π");
		this.htmltoTextCodes.put("&Pi;", "Π");
		this.htmltoTextCodes.put("&#929;", "Ρ");
		this.htmltoTextCodes.put("&Rho;", "Ρ");
		this.htmltoTextCodes.put("&#931;", "Σ");
		this.htmltoTextCodes.put("&Sigma;", "Σ");
		this.htmltoTextCodes.put("&#932;", "Τ");
		this.htmltoTextCodes.put("&Tau;", "Τ");
		this.htmltoTextCodes.put("&#933;", "Υ");
		this.htmltoTextCodes.put("&Upsilon;", "Υ");
		this.htmltoTextCodes.put("&#934;", "Φ");
		this.htmltoTextCodes.put("&Phi;", "Φ");
		this.htmltoTextCodes.put("&#935;", "Χ");
		this.htmltoTextCodes.put("&Chi;", "Χ");
		this.htmltoTextCodes.put("&#936;", "Ψ");
		this.htmltoTextCodes.put("&Psi;", "Ψ");
		this.htmltoTextCodes.put("&#937;", "Ω");
		this.htmltoTextCodes.put("&Omega;", "Ω");
		this.htmltoTextCodes.put("&#945;", "α");
		this.htmltoTextCodes.put("&alpha;", "α");
		this.htmltoTextCodes.put("&#946;", "β");
		this.htmltoTextCodes.put("&beta;", "β");
		this.htmltoTextCodes.put("&#947;", "γ");
		this.htmltoTextCodes.put("&gamma;", "γ");
		this.htmltoTextCodes.put("&#948;", "δ");
		this.htmltoTextCodes.put("&delta;", "δ");
		this.htmltoTextCodes.put("&#949;", "ε");
		this.htmltoTextCodes.put("&epsilon;", "ε");
		this.htmltoTextCodes.put("&#950;", "ζ");
		this.htmltoTextCodes.put("&zeta;", "ζ");
		this.htmltoTextCodes.put("&#951;", "η");
		this.htmltoTextCodes.put("&eta;", "η");
		this.htmltoTextCodes.put("&#952;", "θ");
		this.htmltoTextCodes.put("&theta;", "θ");
		this.htmltoTextCodes.put("&#953;", "ι");
		this.htmltoTextCodes.put("&iota;", "ι");
		this.htmltoTextCodes.put("&#954;", "κ");
		this.htmltoTextCodes.put("&kappa;", "κ");
		this.htmltoTextCodes.put("&#955;", "λ");
		this.htmltoTextCodes.put("&lambda;", "λ");
		this.htmltoTextCodes.put("&#956;", "μ");
		this.htmltoTextCodes.put("&mu;", "μ");
		this.htmltoTextCodes.put("&#957;", "ν");
		this.htmltoTextCodes.put("&nu;", "ν");
		this.htmltoTextCodes.put("&#958;", "ξ");
		this.htmltoTextCodes.put("&xi;", "ξ");
		this.htmltoTextCodes.put("&#959;", "ο");
		this.htmltoTextCodes.put("&omicron;", "ο");
		this.htmltoTextCodes.put("&#960;", "π");
		this.htmltoTextCodes.put("&pi;", "π");
		this.htmltoTextCodes.put("&#961;", "ρ");
		this.htmltoTextCodes.put("&rho;", "ρ");
		this.htmltoTextCodes.put("&#962;", "ς");
		this.htmltoTextCodes.put("&sigmaf;", "ς");
		this.htmltoTextCodes.put("&#963;", "σ");
		this.htmltoTextCodes.put("&sigma;", "σ");
		this.htmltoTextCodes.put("&#964;", "τ");
		this.htmltoTextCodes.put("&tau;", "τ");
		this.htmltoTextCodes.put("&#965;", "υ");
		this.htmltoTextCodes.put("&upsilon;", "υ");
		this.htmltoTextCodes.put("&#966;", "φ");
		this.htmltoTextCodes.put("&phi;", "φ");
		this.htmltoTextCodes.put("&#967;", "χ");
		this.htmltoTextCodes.put("&chi;", "χ");
		this.htmltoTextCodes.put("&#968;", "ψ");
		this.htmltoTextCodes.put("&psi;", "ψ");
		this.htmltoTextCodes.put("&#969;", "ω");
		this.htmltoTextCodes.put("&omega;", "ω");				
		this.htmltoTextCodes.put("&#977;", "ϑ");
		this.htmltoTextCodes.put("&thetasym;", "ϑ");
		this.htmltoTextCodes.put("&#978;", "v");
		this.htmltoTextCodes.put("&upsih;", "ϒ");
		this.htmltoTextCodes.put("&#982;", "ϖ");
		this.htmltoTextCodes.put("&piv;", "ϖ");
		this.htmltoTextCodes.put("&#8211;", "–");
		this.htmltoTextCodes.put("&ndash;", "–");
		this.htmltoTextCodes.put("&#8212;", "—");
		this.htmltoTextCodes.put("&mdash;", "—");
		this.htmltoTextCodes.put("&#8242;", "′");
		this.htmltoTextCodes.put("&prime;", "′");
		this.htmltoTextCodes.put("&#8243;", "″");
		this.htmltoTextCodes.put("&Prime;", "″");
		this.htmltoTextCodes.put("&#8249;", "‹");
		this.htmltoTextCodes.put("&lsaquo;", "‹");
		this.htmltoTextCodes.put("&#8250;", "›");
		this.htmltoTextCodes.put("&rsaquo;", "›");
		this.htmltoTextCodes.put("&#8592;", "←");
		this.htmltoTextCodes.put("&larr;", "←");
		this.htmltoTextCodes.put("&#8593;", "↑");
		this.htmltoTextCodes.put("&uarr;", "↑");
		this.htmltoTextCodes.put("&#8594;", "→");
		this.htmltoTextCodes.put("&rarr;", "→");
		this.htmltoTextCodes.put("&#8595;", "↓");
		this.htmltoTextCodes.put("&darr;", "↓");
		this.htmltoTextCodes.put("&#8596;", "↔");
		this.htmltoTextCodes.put("&harr;", "↔");
		this.htmltoTextCodes.put("&#8217;", "’");
	}
	
	public static String textToHTML(String originalText)
	{
		if(originalText!=null)
		{
			originalText = originalText.replaceAll("[<]", "«");
			originalText = originalText.replaceAll("[>]", "»");
			return originalText;
		}
		return null;
	}

}
