package Foodify.Backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@Document(collection="RegisteredCustomer")
public class Registered_Customer{

	@Id
	private String id;
	
	@NotNull(message = "UserName is required.")
	@Size(min = 6, message = "UserName should be atleast 6 characters.")
	private String userName;
	private String password;
	private String accountState;

	private Boolean blocked;
	
	private String location;
	private String email;
	private String telephone;
	private Binary image;
	private String bImage;
//	-----------1.15.22---this is for after logged in-----------
	private Object authorities;

	public Object getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Object authorities) {
		this.authorities = authorities;
	}

	//Reset Password
	@Field(name = "resetPasswordToken")
	private String resetPasswordToken;
		
//	registered user constructor
	public Registered_Customer() {

	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}

	public String getaccountState() {
		return accountState;
	}
	public void setaccountState(String accountState) {
		this.accountState = accountState;
	}
	public String getlocation() {
		return location;
	}
	public void setlocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String gettelephone() {
		return telephone;
	}
	public void settelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	public Binary getImage() {
		return image;
	}
	public Binary setImage(Binary image) {
		return this.image = image;
	}
	
	public String getbImage() {
		return bImage;
	}
	public void setbImage(String bImage) {
		this.bImage = bImage;
	}

	//	toString method for user
	@Override
	public String toString() {
		return "Registered_Customer ["
				+ "userName=" + userName + 
				", password=" + password + 
				", accountState=" + accountState
				+ ", location=" + location +
				", email=" + email + 
				", telephone=" + telephone + 
				"]";
	}

	
};
