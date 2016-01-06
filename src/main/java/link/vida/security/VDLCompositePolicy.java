package link.vida.security;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.List;

public class VDLCompositePolicy extends Policy {
	
	private List<Policy> policies;
	
	public VDLCompositePolicy(List<Policy> policies) {
		this.policies = policies;
	}

	@Override
	public PermissionCollection getPermissions(CodeSource codesource) {
		Permissions perms = new Permissions();
		for (Policy policy : policies) {
			PermissionCollection permsColl = policy.getPermissions(codesource);
			for (Enumeration<Permission> en = permsColl.elements(); en.hasMoreElements();) {
				perms.add(en.nextElement());
			}
		}
		return perms;
	}

	@Override
	public PermissionCollection getPermissions(ProtectionDomain domain) {
		Permissions perms = new Permissions();
		for (Policy policy : policies) {
			PermissionCollection permsColl = policy.getPermissions(domain);
			for (Enumeration<Permission> en = permsColl.elements(); en.hasMoreElements();) {
				perms.add(en.nextElement());
			}
		}
		return perms;
	}

	@Override
	public boolean implies(ProtectionDomain domain, Permission permission) {
		for (Policy policy : policies) {
			if (policy.implies(domain, permission)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void refresh() {
		for (Policy policy : policies) {
			policy.refresh();
		}
	}
	
}
