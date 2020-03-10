package com.cszx.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	private CacheManager cacheManager;

	public CacheUtil() {
		cacheManager = CacheManager.create(this.getClass().getResource("/ehcache.xml"));
	}

	public void setCache(String key, Object value, String cacheName) {
		Element element = new Element(key, value);
		Cache cache = cacheManager.getCache(cacheName);
		cache.put(element);
	}

	public Object getCache(String key, String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if (element == null)
			return null;
		Object data = element.getObjectValue();// 获取到的缓存数据
		return data;
	}

	public void remove(String key, String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
	}
}
