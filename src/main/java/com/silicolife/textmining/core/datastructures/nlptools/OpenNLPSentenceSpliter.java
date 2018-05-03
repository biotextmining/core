package com.silicolife.textmining.core.datastructures.nlptools;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.documents.structure.SentenceImpl;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

/**
 * Class that represents a OpenNLP Tagging 
 * 
 * @author Hugo Costa
 *
 */
public class OpenNLPSentenceSpliter {

	private final static String sentenceModelFile = "nlpmodels/en-sent.bin";

	private SentenceModel sentenceModel;

	private static OpenNLPSentenceSpliter _instance;

	private OpenNLPSentenceSpliter(){

	}

	/**
	 * Gives access to the OpenNLP instance
	 * @return 
	 */
	public static synchronized OpenNLPSentenceSpliter getInstance() {
		if (_instance == null) {
			OpenNLPSentenceSpliter.createInstance();
		}
		return _instance;
	}
	
	

	public SentenceModel getSentenceModel() throws IOException {
		if(sentenceModel == null)
			sentenceModel = initSentenceModel();
		return sentenceModel;
	}

	/**
	 * Creates the singleton instance.
	 */
	private static void createInstance(){

		if (_instance == null) {
			_instance = new OpenNLPSentenceSpliter();
		}
	}

	private SentenceModel initSentenceModel() throws IOException{
		InputStream modelIn = OpenNLPSentenceSpliter.class.getClassLoader().getResourceAsStream(sentenceModelFile);
		return new SentenceModel(modelIn);
	}


	/**
	 * Sentence Splitter. Return a List of {@link ISentence}
	 * 
	 * @param text
	 * @return
	 * @throws IOException 
	 */
	public List<ISentence> getSentencesText(String text) throws IOException
	{
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(getSentenceModel());
		Span sentences[] = sentenceDetector.sentPosDetect(text.trim());
		List<ISentence> sents = new ArrayList<ISentence>();
		for(Span sent:sentences)
		{
			
			SentenceImpl sen = new SentenceImpl(sent.getStart(), sent.getEnd(), text.substring((int) sent.getStart(), (int) sent.getEnd()));
			sents.add(sen);
		}	
		return sents;
	}

}
