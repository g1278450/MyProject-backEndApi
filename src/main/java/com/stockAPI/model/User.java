package com.stockAPI.model;

public class User {

	/** 帳戶id-系統給 */
	private Integer id;

	/** 帳號 */
	private String account;

	/** 名字 */
	private String name;

	/** 密碼 */
	private String password;

	/** 權限 */
	private String authority;

	// 需加入無參數的建構子，不然BeanRowMapper無法初始化
	public User() {

	}

	public User(String account, String name, String password, String authority) {
		this.account = account;
		this.name = name;
		this.password = password;
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
