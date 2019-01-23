package com.dotcom.aurora.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="AU_ROLE")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private long roleId;
	private String roleName;
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
