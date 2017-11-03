package com.silicolife.textmining.core.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroups;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.GeneratePassword;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public class CreateUserTest {

	@Test
	public void test() throws InvalidDatabaseAccess, ANoteException, IOException {
		DatabaseConnectionInit.init(DataBaseTypeEnum.MYSQL,"localhost","3306","databasename","databasenameuser","databasenamepwd");

		List<IGroup> groups = InitConfiguration.getDataAccess().getAllGroups();
		IGroup group = groups.get(0);

		Long userID = GenerateRandomId.generateID();
		String password = "test";
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(String.valueOf(userID), null);
		String pass = GeneratePassword.generate(String.valueOf(password), salt);
		String preferLanguage = "en";
		byte[] auAvatar = extractBytes("src/test/resources/images/anoteuser.png");

		IUser user = new AuthUsers(userID, (AuthGroups) group, "test3", pass, "test3", "test3@silicolife.com",preferLanguage,auAvatar);
		InitConfiguration.getDataAccess().createUser(user);
	}

	public byte[] extractBytes (String ImageName) throws IOException {
		// open image
		File imgPath = new File(ImageName);
		FileInputStream fin = new FileInputStream(imgPath);
		byte [] b1=new byte[(int)imgPath.length()];
		fin.read(b1);
        fin.close();
        return b1;
	}

}
