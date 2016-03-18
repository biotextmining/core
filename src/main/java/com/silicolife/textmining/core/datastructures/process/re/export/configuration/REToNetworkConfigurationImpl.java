package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.ICardinality;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationAdvanceOptions;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationAutoOpen;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationEntityOptions;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationRelationOptions;

public class REToNetworkConfigurationImpl implements IREToNetworkConfiguration{

	private IREToNetworkConfigurationEntityOptions entityOptions;
	private IREToNetworkConfigurationRelationOptions relationOptions;
	private IREToNetworkConfigurationAdvanceOptions advanceOptions;
	private IREToNetworkConfigurationAutoOpen autoopenOptions;

	
	
	public REToNetworkConfigurationImpl(
			IREToNetworkConfigurationEntityOptions entityOptions,
			IREToNetworkConfigurationRelationOptions relationOptions,
			IREToNetworkConfigurationAdvanceOptions advanceOptions,
			IREToNetworkConfigurationAutoOpen autoopenOptions) {
		super();
		this.entityOptions = entityOptions;
		this.relationOptions = relationOptions;
		this.advanceOptions = advanceOptions;
		this.autoopenOptions = autoopenOptions;
	}

	public IREToNetworkConfigurationEntityOptions getEntityConfigutrationOptions() {
		return entityOptions;
	}

	public IREToNetworkConfigurationRelationOptions getRelationConfigurationOptions() {
		return relationOptions;
	}

	public IREToNetworkConfigurationAdvanceOptions getAdvanceConfigurations() {
		return advanceOptions;
	}
	
	

	public static Set<DirectionallyEnum> getAllDefaultDirectionally() {
		Set<DirectionallyEnum> result = new HashSet<DirectionallyEnum>();
		result.add(DirectionallyEnum.LeftToRight);
		result.add(DirectionallyEnum.RightToLeft);
		result.add(DirectionallyEnum.Both);
		result.add(DirectionallyEnum.Unknown);
		return result;
	}

	public static Set<PolarityEnum> getAllDefaultPolarities() {
		Set<PolarityEnum> result = new HashSet<PolarityEnum>();
		result.add(PolarityEnum.Positive);
		result.add(PolarityEnum.Negative);
		result.add(PolarityEnum.Conditional);
		result.add(PolarityEnum.Unknown);
		return result;
	}

	public static TreeSet<ICardinality> getAllDefaultCardinalities() {
		TreeSet<ICardinality> cardinalities = new TreeSet<ICardinality>();
		cardinalities.add(new CardinalityImpl(1, 1));
		cardinalities.add(new CardinalityImpl(1, 2));
		cardinalities.add(new CardinalityImpl(2, 1));
		cardinalities.add(new CardinalityImpl(2, 2));
		return cardinalities;
	}

	public static Set<String> getAllLemma() throws ANoteException {
//		Set<String> lemmas = new HashSet<String>();
//		PreparedStatement getLemmasPS;
//			getLemmasPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesREToNetwork.getAllLemmas);
//			ResultSet rs = getLemmasPS.executeQuery();
//			while(rs.next())
//			{
//				lemmas.add(rs.getString(1));
//			}
//			rs.close();
//			getLemmasPS.close();
		return InitConfiguration.getDataAccess().getIEStatiticsAllLemmas();
	}

	public static SortedSet<IRelationsType> getAllDefaultRelationsType() throws ANoteException {
//		SortedSet<IRelationsType> relationTypes = new TreeSet<IRelationsType>();
//		Set<Integer> getLeftClassIDs = new HashSet<Integer>();
//		Set<Integer> getRightClassIDs = new HashSet<Integer>();
//			PreparedStatement getSideClassID = Configuration.getDatabase().getConnection().prepareStatement(QueriesREToNetwork.getAllClassesSide);
//			getSideClassID.setString(1, "left");		
//			ResultSet rs = getSideClassID.executeQuery();
//			while(rs.next())
//			{
//				getLeftClassIDs.add(rs.getInt(1));
//			}
//			getSideClassID.setString(1, "right");
//			rs = getSideClassID.executeQuery();
//			while(rs.next())
//			{
//				getRightClassIDs.add(rs.getInt(1));
//			}
//			rs.close();
//			getSideClassID.close();
//		for(Integer leftClassID:getLeftClassIDs)
//		{
//			for(Integer rightClassID:getRightClassIDs)
//			{
//				relationTypes.add(new RelationType(leftClassID, rightClassID));
//			}
//		}
		return InitConfiguration.getDataAccess().getIEStatiticsAllDefaultRelationsType();
	}

	@Override
	public IREToNetworkConfigurationAutoOpen getREToNetworkConfigurationAutoOpen() {
		return autoopenOptions;
	}

}
