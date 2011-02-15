package com.infoservice.domain;

public class SecurityGroup implements java.io.Serializable {

	private static final long serialVersionUID = -2584010544615674429L;

	private Long id;
	private String code;
	private String name;
	private String describe;
	private String menuGroupText;
	private Byte isLocked;

	public Byte getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Byte isLocked) {
		this.isLocked = isLocked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMenuGroupText() {
		return menuGroupText;
	}

	public void setMenuGroupText(String menuGroupText) {
		this.menuGroupText = menuGroupText;
	}

}
