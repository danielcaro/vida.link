package link.vida.security;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.Enumeration;
//
//import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalIdentifier;
//import aprendevaadin.prueba04.demo.jaas.dao.IDemoViewExecPermissionData;
//import aprendevaadin.prueba04.demo.jaas.dao.IDemoViewExecPermissionIdentifier;
//import aprendevaadin.prueba04.demo.jaas.dao.impl.JassDaoImpl;

public class VDLPolicy extends Policy {
    
    
        

	@Override
	public PermissionCollection getPermissions(CodeSource codesource) {
		final Permissions persmissions = new Permissions();
		persmissions.add(new AllPermission());
		return persmissions;
	}
	
	@Override
	public PermissionCollection getPermissions(ProtectionDomain domain) {
		
		final Permissions permissions = new Permissions();
		
		Principal[] principals = domain.getPrincipals();
		
		if (principals != null && principals.length > 0) {
			
			////////////////////////////////////////////////////////////////////////
			// Si el dominio tiene principales se obtienen los permisos de estos.
			////////////////////////////////////////////////////////////////////////
			for (Principal principal : principals) {
				
				// Se obtienen los permisos para el principal 'DemoGroupPrincipal'.
//				if (principal instanceof DemoGroupPrincipal) {
//					DemoGroupPrincipal demoGroupPrincipal = (DemoGroupPrincipal) principal;
//					IDemoGroupPrincipalIdentifier demoGroupPrincipalIdentifier = demoGroupPrincipal.getIdentifier();
//					for (IDemoViewExecPermissionIdentifier identifier : JassDaoImpl.getInstance().getDemoViewExecPermission(demoGroupPrincipalIdentifier)) {

//						IDemoViewExecPermissionData data = JassDaoImpl.getInstance().getDemoViewExecPermissionData(identifier);
//						permissions.add(new DemoViewExecPermission(data.getView()));
//					}
//				}
				
				// Se obtienen los permisos para otros principales.
				// ....
				// ....
				
			}
			
		} else if (domain.getCodeSource() != null) {
			
			////////////////////////////////////////////////////////////////////////
			// Se obtienen los permisos asociados a la procedencia o la firma del codigo.
			////////////////////////////////////////////////////////////////////////
			PermissionCollection codeSrcPerms = getPermissions(domain.getCodeSource());
			for (Enumeration<Permission> en = codeSrcPerms.elements(); en.hasMoreElements();) {
				permissions.add(en.nextElement());
			}
			
		}
		
		// Podemos retornar la lista de permisos obtenidos por cualquiera de los dos origenes.
		return permissions;
	}
	
	@Override
	public boolean implies(ProtectionDomain domain, Permission permission) {
		PermissionCollection perms = getPermissions(domain);
		return perms.implies(permission);
	}

	@Override
	public void refresh() {
	}
	
}
