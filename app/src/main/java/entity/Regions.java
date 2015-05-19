package entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Regions implements Serializable{
	@SerializedName("name")
	private String name ;
	@SerializedName("code")
	private String code ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
