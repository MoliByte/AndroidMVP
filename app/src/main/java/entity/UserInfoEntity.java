package entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
/**
    "id": 413,
    "avatar": "http://napi.skinrun.cn/uploads/000/000/413/source.jpg",
    "username": "18736557125",
    "email": "default",
    "member_id": 0,
    "mobile": "18736557125",
    "niakname": "淡蓝天际",
    "realname": "格格",
    "gender": 2,
    "birthday": "1989-10-16 00:00:00",
    "region": "411324",
    "regionNames": "",
    "skinType": 2,
    "hasMerchant": 0,
    "integral": 185
 */
public class UserInfoEntity implements Serializable{
	public int id ;
	
	@SerializedName("avatar")
	public String user_avatar ;
	public String username ;
	public String email ;
	public int member_id ;
	public String mobile ;
	public String niakname ;
	public String realname ;
	public int gender ;
	public String birthday ;
	public String region ;
	public String regionNames ;
	public int skinType ;
	public int hasMerchant ;
	public int integral ;
	
	public String token ;
	
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNiakname() {
		return niakname;
	}
	public void setNiakname(String niakname) {
		this.niakname = niakname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegionNames() {
		return regionNames;
	}
	public void setRegionNames(String regionNames) {
		this.regionNames = regionNames;
	}
	public int getSkinType() {
		return skinType;
	}
	public void setSkinType(int skinType) {
		this.skinType = skinType;
	}
	public int getHasMerchant() {
		return hasMerchant;
	}
	public void setHasMerchant(int hasMerchant) {
		this.hasMerchant = hasMerchant;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
	
}
