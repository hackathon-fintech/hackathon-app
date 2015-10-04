package io.swagger.client.model;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class UserAccount  {
  
  @SerializedName("rut")
  private String rut = null;
  @SerializedName("firstName")
  private String firstName = null;
  @SerializedName("specialAssistance")
  private Boolean specialAssistance = null;
  @SerializedName("premium")
  private Boolean premium = null;
  @SerializedName("branchCode")
  private String branchCode = null;
  @SerializedName("branchStatus")
  private String branchStatus = null;
  @SerializedName("action")
  private String action = null;
  @SerializedName("photoURL")
  private String photoURL = null;
  @SerializedName("account")
  private String account = null;
  @SerializedName("token")
  private String token = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getRut() {
    return rut;
  }
  public void setRut(String rut) {
    this.rut = rut;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getSpecialAssistance() {
    return specialAssistance;
  }
  public void setSpecialAssistance(Boolean specialAssistance) {
    this.specialAssistance = specialAssistance;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getPremium() {
    return premium;
  }
  public void setPremium(Boolean premium) {
    this.premium = premium;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getBranchCode() {
    return branchCode;
  }
  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getBranchStatus() {
    return branchStatus;
  }
  public void setBranchStatus(String branchStatus) {
    this.branchStatus = branchStatus;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getPhotoURL() {
    return photoURL;
  }
  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getAccount() {
    return account;
  }
  public void setAccount(String account) {
    this.account = account;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAccount {\n");
    
    sb.append("  rut: ").append(rut).append("\n");
    sb.append("  firstName: ").append(firstName).append("\n");
    sb.append("  specialAssistance: ").append(specialAssistance).append("\n");
    sb.append("  premium: ").append(premium).append("\n");
    sb.append("  branchCode: ").append(branchCode).append("\n");
    sb.append("  branchStatus: ").append(branchStatus).append("\n");
    sb.append("  action: ").append(action).append("\n");
    sb.append("  photoURL: ").append(photoURL).append("\n");
    sb.append("  account: ").append(account).append("\n");
    sb.append("  token: ").append(token).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
