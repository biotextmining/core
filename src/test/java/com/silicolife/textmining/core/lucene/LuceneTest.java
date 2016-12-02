package com.silicolife.textmining.core.lucene;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDemonConnectionException;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementsFilterImpl;
import com.silicolife.textmining.core.init.DaemonConnectionInit;
import com.silicolife.textmining.core.init.DatabaseConnectionInit;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;



public class LuceneTest {

//	@Test
	public void rebuildIndex() throws InvalidDatabaseAccess, ANoteException {
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
		InitConfiguration.getDataAccess().rebuildLuceneIndex();
	}
	
//	@Test
	public void rebuildIndexDaemon() throws InvalidDemonConnectionException, ANoteException {
		DaemonConnectionInit.init("http","8080","localhost","anote2daemon");
		InitConfiguration.getDataAccess().rebuildLuceneIndex();
	}
	
//	@Test
	public void testIndex() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsByExactTerm("fructosyllysine(1+)");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
	@Test
	public void testIndex2() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","sisbitmpipeline","root","admin");
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsByExactSynonym("leucyl aminopeptidase");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
//	@Test
	public void testIndex3() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(5727281589065523363L);
		IResourceElementsFilter filter = new ResourceElementsFilterImpl();
		filter.addResource(resource);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsFilteredByExactTerm(filter, "Bacterium coli commune");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
//	@Test
	public void testIndex4() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
//		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(3685732426240829176L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsByPartialTerm("acid");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		System.out.println(elemts.size());
		Set<String> notStart = new HashSet<>();
		for(IResourceElement elem : elemts.getResourceElements()){
//			if(elem.getTerm().startsWith("Bacteriu")){
//				
//			}else if(elem.getTerm().startsWith("bacteriu")){
//				
//			}else{
//				notStart.add(elem.getTerm());
//			}
			System.out.println(elem.getId());

		}
		
		System.out.println(notStart);
		
	}
	
	
//	@Test
	public void testIndex5() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
//		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(3685732426240829176L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsByPartialSynonym("acid");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		System.out.println(elemts.size());
//		Set<String> notStart = new HashSet<>();
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
//			if(elem.getTerm().startsWith("Bacteriu")){
//				
//			}else if(elem.getTerm().startsWith("bacteriu")){
//				
//			}else{
//				notStart.add(elem.getTerm());
//			}
//			
		}
//		
//		System.out.println(notStart);
		
		
	}
	
//	@Test
	public void testIndex6() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","maria","root","admin");
//		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(3685732426240829176L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getResourceElementsByPartialTermPaginated("acid",0,100);
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		System.out.println(elemts.size());
		Set<String> notStart = new HashSet<>();
		for(IResourceElement elem : elemts.getResourceElements()){
//			if(elem.getTerm().startsWith("Bacteriu")){
//				
//			}else if(elem.getTerm().startsWith("bacteriu")){
//				
//			}else{
//				notStart.add(elem.getTerm());
//			}
			System.out.println(elem.getId());

		}
		
		System.out.println(notStart);
		
	}

}
