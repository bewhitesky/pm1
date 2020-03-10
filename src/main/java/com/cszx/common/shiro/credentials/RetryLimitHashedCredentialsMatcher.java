package com.cszx.common.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

//密码尝试5次
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
	private Cache<String,AtomicInteger> passwordRetryCache;
	
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
		passwordRetryCache=cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String loginId=(String)token.getPrincipal();
		AtomicInteger retryCount=passwordRetryCache.get(loginId);
		if(retryCount==null){
			retryCount=new AtomicInteger(0);
			passwordRetryCache.put(loginId, retryCount);
		}
		if(retryCount.incrementAndGet()>10){
			throw new ExcessiveAttemptsException();
		}
		boolean matches= super.doCredentialsMatch(token, info);
		if(matches){
			passwordRetryCache.remove(loginId);
		}
		return matches;
	}
	
	
}
