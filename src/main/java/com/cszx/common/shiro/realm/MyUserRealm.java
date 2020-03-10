package com.cszx.common.shiro.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cszx.pm.model.user.User;
import com.cszx.pm.service.UserService;

public class MyUserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginId = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// Set<String> roleSet = userService.findRolesByLoginId(loginId);
		authorizationInfo.setRoles(userService.findRolesByLoginId(loginId));
		// authorizationInfo.setStringPermissions(userService.findPermissionsByLoginId(loginId));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginId = (String) token.getPrincipal();
		User user = userService.getUserByLoginId(loginId);
		List<String> roleCodes = userService.getRolesByLoginId(loginId);
		String roleCode = "";
		for (String s : roleCodes) {
			roleCode = roleCode + s;
		}
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginId(),
				user.getLoginPassword(), ByteSource.Util.bytes("cxzhao"), getName());
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("user", user);
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("roleCode", roleCode);

		return authenticationInfo;
	}

	@Override
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		// principals.getPrimaryPrincipal();
		super.clearCachedAuthenticationInfo(principals);
	}

}
