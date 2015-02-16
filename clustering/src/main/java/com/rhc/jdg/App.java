package com.rhc.jdg;

import java.util.Map;
import java.util.Scanner;

import org.infinispan.client.hotrod.RemoteCache;

import com.rhc.jdg.provider.JavaCacheProvider;
import com.rhc.jdg.provider.RemoteCacheProvider;
import com.rhc.jdg.provider.XmlCacheProvider;
import com.rhc.jdg.util.MapUtil;

public class App {
//	private static JavaCacheProvider javaCacheProvider = new JavaCacheProvider();
//	private static XmlCacheProvider xmlCacheProvider = new XmlCacheProvider();
	private static RemoteCacheProvider remoteCacheProvider = new RemoteCacheProvider();
	
	public static void main(String[] args) {
//		Map<String, String> javaCache = javaCacheProvider.getCache();
//		Map<String, String> xmlCache = xmlCacheProvider.getCache();
		RemoteCache<String, String> remoteCache = remoteCacheProvider.getCache();
		
		boolean running = true;
		Scanner input = new Scanner(System.in);
		
		while (running) {
			System.out.print("Add [a], Remove [r], or List [l]:");
			String choice = input.next();
			
			if (choice.equalsIgnoreCase("a")) {
				System.out.print("Key:");
				String key = input.next();
				System.out.print("Value:");
				String value = input.next();
				remoteCache.put(key, value);
			} else if (choice.equalsIgnoreCase("r")) {
				System.out.print("Key:");
				String key = input.next();
				remoteCache.remove(key);
			} else if (choice.equalsIgnoreCase("l")) {
				System.out.println(MapUtil.contents(remoteCache));
			} else {
				System.out.println("Unknown input!");
			}
		}
		
		input.close();
	}
}