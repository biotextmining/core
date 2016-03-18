package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettings;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettingsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

public class AuthPropertiesWrapper {

	public static List<AuthUserSettings> convert(AuthUsers authUser,Properties properties) {
		List<AuthUserSettings> authProperties = new ArrayList<AuthUserSettings>();
		for(String propKey:properties.stringPropertyNames())
		{
			AuthUserSettingsId id = new AuthUserSettingsId(authUser.getAuId(), propKey);
			String ausPropValue = properties.getProperty(propKey);
			AuthUserSettings e = new AuthUserSettings(id, authUser, ausPropValue );
			authProperties.add(e);
		}
		return authProperties;
	}

}
