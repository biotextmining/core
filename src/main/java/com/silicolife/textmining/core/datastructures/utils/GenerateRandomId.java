package com.silicolife.textmining.core.datastructures.utils;

import java.net.InetAddress;
import java.util.Random;

public class GenerateRandomId {

	public synchronized static Long generateID() {

		long time = System.currentTimeMillis();
		long nanoTime = System.nanoTime();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long addressHashCode;

		try {
			InetAddress inetAddress;
			inetAddress = InetAddress.getLocalHost();
			addressHashCode = inetAddress.getCanonicalHostName().hashCode() ^ inetAddress.getHostName().hashCode() ^ inetAddress.getHostAddress().hashCode();
		} catch (Exception err) {
			addressHashCode = GenerateRandomId.class.hashCode();
		}
		Random random1 = new Random(time);
		Random random2 = new Random(nanoTime);
		Random random3 = new Random(addressHashCode ^ freeMemory);

		return Math.abs(random1.nextLong() ^ random2.nextLong() ^ random3.nextLong());
	}
}
