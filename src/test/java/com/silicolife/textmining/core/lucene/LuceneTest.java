package com.silicolife.textmining.core.lucene;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.init.DatabaseConnectionInit;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;



public class LuceneTest {

	@Test
	public void rebuildIndex() throws InvalidDatabaseAccess, ANoteException {
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
		InitConfiguration.getDataAccess().rebuildLuceneIndex();
	}
	
//	@Test
	public void testIndex() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getAllResourceElementsByExactTermUsingLucene("Bacterium coli commune");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
//	@Test
	public void testIndex2() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getAllResourceElementsByExactSynonymUsingLucene("e. coli");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
//	@Test
	public void testIndex3() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(5727281589065523363L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getAllResourceElementsByExactTermFromResourceUsingLucene("Bacterium coli commune", resource);
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		for(IResourceElement elem : elemts.getResourceElements()){
			System.out.println(elem.getId());
		}
		
	}
	
//	@Test
	public void testIndex4() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
//		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(3685732426240829176L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getAllResourceElementsByPartialTermUsingLucene("Bacteriu");
		long endtime = GregorianCalendar.getInstance().getTimeInMillis();
		System.out.println(endtime-startime);
		System.out.println(elemts.size());
		Set<String> notStart = new HashSet<>();
		for(IResourceElement elem : elemts.getResourceElements()){
			if(elem.getTerm().startsWith("Bacteriu")){
				
			}else if(elem.getTerm().startsWith("bacteriu")){
				
			}else{
				notStart.add(elem.getTerm());
			}
			
		}
		
		System.out.println(notStart);
		
	}
	
	
//	@Test
	public void testIndex5() throws InvalidDatabaseAccess, ANoteException{
		DatabaseConnectionInit.init("localhost","3306","textminingcarbontest","root","admin");
//		IResource<IResourceElement> resource = InitConfiguration.getDataAccess().getResourceByID(3685732426240829176L);
		long startime = GregorianCalendar.getInstance().getTimeInMillis();
		IResourceElementSet<IResourceElement> elemts = InitConfiguration.getDataAccess().getAllResourceElementsByPartialSynonymUsingLucene("lens neutral proteinase");
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

}
