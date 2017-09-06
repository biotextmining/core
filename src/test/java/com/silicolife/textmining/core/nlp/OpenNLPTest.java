package com.silicolife.textmining.core.nlp;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.nlptools.OpenNLPSentenceSpliter;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;

public class OpenNLPTest {

	@Test
	public void test() throws IOException{
		String text4 = "All of these enzymes utilize pyridoxal-5'-phosphate as a co-factor and function together with inner-membrane substrate-product antiporters that remove decarboxylation products to the external medium in exchange for fresh substrate.";
		
		List<ISentence> sentences = OpenNLPSentenceSpliter.getInstance().getSentencesText(text4);
		for(ISentence sentence : sentences)
			System.out.println(sentence);
	}	
}

