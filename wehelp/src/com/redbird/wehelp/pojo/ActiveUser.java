package com.redbird.wehelp.pojo;

import java.util.Date;
import java.util.List;

/**
 * 在线用户
 * @author c
 *
 */
public class ActiveUser extends BasePojo {
	
	private static final long serialVersionUID = -6937820421163866201L;
	// 用户名
	private String userName;
	// 昵称
	private String nickName;
	// 性别 男人 1 女人 2 其他 0
	private int sex;
	// 年龄
	private int age;
	// 电话
	private String phone;
	// 邮箱
	private String email;
	// 地址
	private String address;
	// 注册日期
	private Date registerDate;
	// 用户所拥有的角色
	private List<Permission> permissions;
	
	@Override
	public String toString() {
		return userName + " " + nickName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
