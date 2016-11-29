package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;
@Table(name="user")
public class User extends AbstractModel {
	@Column(datatype = "setString", name = "email")
	private String email;
	@Column(datatype = "setString", name = "password")
	private String password;
	@Column(datatype = "setLong", name = "role_id")
	private Long roleId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
