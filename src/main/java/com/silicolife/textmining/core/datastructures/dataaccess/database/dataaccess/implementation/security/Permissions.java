package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;

/**
 * Identify the permissions of the anote2daemon
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Component
public class Permissions {

	private static final List<String> fullGrant = new ArrayList<String>(Arrays.asList(PermissionsUtilsEnum.owner.getName(), 
			PermissionsUtilsEnum.read.getName(), PermissionsUtilsEnum.read_write.getName()));
	private static final List<String> writeGrant = new ArrayList<String>(Arrays.asList(PermissionsUtilsEnum.owner.getName(), PermissionsUtilsEnum.read_write.getName()));
	private static final List<String> ownerGrant = new ArrayList<String>(Arrays.asList(PermissionsUtilsEnum.owner.getName()));

	public static List<String> getFullgrant() {
		return fullGrant;
	}

	public static List<String> getWritegrant() {
		return writeGrant;
	}

	public static List<String> getOwnergrant() {
		return ownerGrant;
	}
}
