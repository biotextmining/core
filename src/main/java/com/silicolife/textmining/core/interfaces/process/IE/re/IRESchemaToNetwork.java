package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.sql.SQLException;

import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.network.IEdge;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetwork;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfiguration;

/**
 * Interface to transform a RE Schema into a {@link INetwork}
 * 
 * @author Hugo Costa
 *
 */
public interface IRESchemaToNetwork {
	public INetwork<INode,IEdge> getNetwork(IRESchema rePorcess,IREToNetworkConfiguration configurations) throws SQLException, Exception;
	public void setcancel();
}
