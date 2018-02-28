package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.pdf.codec.Base64;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CredentialsAccessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.UserExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroups;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettings;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettingsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.GeneratePassword;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.AuthPropertiesWrapper;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

/**
 * 
 * 
 * 
 * @author Utilizador
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;
	private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

	@Autowired
	public UserServiceImpl(UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
		passwordEncoder.setIterations(1000000);
	}

	@Override
	public Boolean checkLogin(String username, String password) {

		AuthUsers user = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", username);
		if (user == null) {
			return false;
		}
		Long userId = user.getAuId();
		String strUserId = String.valueOf(userId);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(strUserId, null);
		if (!passwordEncoder.isPasswordValid(user.getAuPassword(), password, salt)) {
			return false;
		}
		return true;
	}

	@Override
	public IUser login(String username, String password) throws CredentialsAccessException {

		AuthUsers user = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", username);
		if (user == null) {
			throw new CredentialsAccessException(ExceptionsCodes.codeWrongCredentials, ExceptionsCodes.msgWrongCredentials);
		}
		Long userId = user.getAuId();
		String strUserId = String.valueOf(userId);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(strUserId, null);
		if (!passwordEncoder.isPasswordValid(user.getAuPassword(), password, salt)) {
			throw new CredentialsAccessException(ExceptionsCodes.codeWrongCredentials, ExceptionsCodes.msgWrongCredentials);
		}
		return user;
	}

	@Override
	public IUser getById(Long id) {
		IUser user = usersManagerDao.getAuthUsersDao().findById(id);
		return user;
	}
	
	@Override
	public IUser getLoggedUser() {
		IUser user = this.userLogged.getCurrentUserLogged();
		return user;
	}

	@Override
	public List<AuthUsers> getAllUsers() {
		List<AuthUsers> authUsers = usersManagerDao.getAuthUsersDao().findAll();
		return authUsers;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createUser(IUser user) {

		usersManagerDao.getAuthUsersDao().save(user);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "create", "auth_users", null, "create a new User");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Boolean createUserFromWeb(IUser userIn) {
		
		Long id = GenerateRandomId.generateID();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(String.valueOf(id), null);
		String pass = GeneratePassword.generate(String.valueOf(userIn.getAuPassword()), salt);
		
		AuthUsers user = new AuthUsers();
		user.setAuId(id);
		user.setAuFullname(userIn.getAuFullname());
		user.setAuUsername(userIn.getAuUsername());
		user.setAuPassword(pass);
		user.setAuAddress(userIn.getAuAddress());
		user.setAuZipCode(userIn.getAuZipCode());
		user.setAuLocation(userIn.getAuLocation());
			user.setAuPhone(userIn.getAuPhone());
		user.setAuEmail(userIn.getAuEmail());
		user.setAuthGroups((AuthGroups) userIn.getAuthGroups());
	
		usersManagerDao.getAuthUsersDao().save(user);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "create", "auth_users", null, "create a new User");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}
	


	@Transactional(readOnly = false)
	@Override
	public Boolean updateUser(IUser user) {
		usersManagerDao.getAuthUsersDao().update(user);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "update", "auth_users", null, "update an user");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Boolean updateCurrentUserFromWeb(IUser user, String oldPassword) {
		
		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUsers userFromBd = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", userLog.getAuUsername());
		
		
		Long userId = userLog.getAuId();
		String strUserId = String.valueOf(userId);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(strUserId, null);
		if (!passwordEncoder.isPasswordValid(userFromBd.getAuPassword(), oldPassword, salt)) {
			return false;
		}
		else {
			//String pass = GeneratePassword.generate(oldPassword, salt);
			//userCopy.setAuPassword(pass);	
		if(user.getAuAddress()!=null) userFromBd.setAuAddress(user.getAuAddress());
		if(user.getAuEmail()!=null) userFromBd.setAuEmail(user.getAuEmail()); 
		if(user.getAuFullname()!=null) userFromBd.setAuFullname(user.getAuFullname()); 
		if(user.getAuLocation()!=null) userFromBd.setAuLocation(user.getAuLocation()); 
		if(user.getAuPhone()!=null) userFromBd.setAuPhone(user.getAuPhone()); 
		if(user.getAuUsername()!=null) userFromBd.setAuUsername(user.getAuUsername());
		if(user.getAuZipCode()!=null) userFromBd.setAuZipCode(user.getAuZipCode());
		
		if(user.getAuPassword()!=null) {
		Long id = userLog.getAuId();
		encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		salt = encoder.encodePassword(String.valueOf(id), null);
		String pass = GeneratePassword.generate(String.valueOf(user.getAuPassword()), salt);
		userFromBd.setAuPassword(pass);
		}
		
		usersManagerDao.getAuthUsersDao().update(userFromBd);
		
		AuthUserLogs log = new AuthUserLogs(userFromBd, new Date(), "update", "auth_users", null, "update an user");
		usersManagerDao.getAuthUserLogsDao().save(log);
		

		return true;
		}
	}
	
	@Transactional(readOnly = false)
	@Override
	public byte[] postAvatar(String image) {
		
		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUsers userFromBd = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", userLog.getAuUsername());
		System.out.println(image);
		String[] bytesString = image.split(",");
		byte[] bytes = new byte[bytesString.length];
		for(int i = 0 ; i < bytes.length ; ++i) {
		    bytes[i] = Byte.parseByte(bytesString[i]);
		}
		System.out.println(bytes);
		userFromBd.setAuAvatar(bytes);
		
		usersManagerDao.getAuthUsersDao().update(userFromBd);
		
		AuthUserLogs log = new AuthUserLogs(userFromBd, new Date(), "update", "auth_users", null, "update an user");
		usersManagerDao.getAuthUserLogsDao().save(log);
		

		return bytes;
		
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeUser(Long id) throws UserExceptions {

		AuthUsers user = usersManagerDao.getAuthUsersDao().findById(id);
		if (user == null)
			throw new UserExceptions(ExceptionsCodes.codeNoUser, ExceptionsCodes.msgNoUser);

		usersManagerDao.getAuthUsersDao().update(user);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "delete", "auth_users", null, "delete an user");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public List<IGroup> getAllGroups() {

		List<AuthGroups> authGroups = usersManagerDao.getAuthGroup().findAll();
		List<IGroup> groups = new ArrayList<IGroup>();
		for (AuthGroups authGroup : authGroups)
			groups.add(authGroup);

		return groups;
	}

	@Override
	public Boolean hasPermissionRole(List<String> roleCodes) {
		Long userId = userLogged.getCurrentUserLogged().getAuId();
		AuthUsers user = usersManagerDao.getAuthUsersDao().findById(userId);
		Set<AuthGroupHasRoles> userRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles userRole : userRoles) {
			String code = userRole.getAuthRoles().getArRoleCode();
			if (roleCodes.contains(code))
				return true;

		}

		return false;
	}

	@Override
	public IUser getByEmail(String email) {
		AuthUsers user = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auEmail", email);
		// if (user != null)
		// user.setAuPassword(null);
		return user;
	}

	@Override
	public IUser getByUsername(String username) {
		AuthUsers user = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", username);
		// if (user != null)
		// user.setAuPassword(null);
		return user;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean saveProperties(Properties properties) {
		AuthUsers user = userLogged.getCurrentUserLogged();
		if(user!=null)
		{
			List<AuthUserSettings> userSettings = AuthPropertiesWrapper.convert(user,properties);
			for(AuthUserSettings userSetting:userSettings)
			{
				usersManagerDao.getAuthSettings().saveOrUpdate(userSetting);
			}
		}
		else
		{
			
		}	
		
		return true;
	}

	@Override
	public Properties loadProperties(Set<String> propertiesIdentifiers) {
		 AuthUsers user = userLogged.getCurrentUserLogged();
		if(user!=null)
		{
			Properties properties = new Properties();
			for(String propKey:propertiesIdentifiers)
			{
				AuthUserSettingsId id = new AuthUserSettingsId(user.getAuId(), propKey);
				AuthUserSettings authSettings = usersManagerDao.getAuthSettings().findById(id);
				if(authSettings!=null)
					properties.put(propKey, authSettings.getAusPropValue());
			}
			return properties;
		}
		else
		{
			return new Properties();
		}
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}
}