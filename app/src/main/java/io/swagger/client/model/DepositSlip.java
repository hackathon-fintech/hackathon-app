package io.swagger.client.model;

import java.util.*;
import io.swagger.client.model.DepositSlipDetail;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class DepositSlip  {
  
  @SerializedName("userRut")
  private String userRut = null;
  @SerializedName("depositId")
  private Long depositId = null;
  @SerializedName("fromName")
  private String fromName = null;
  @SerializedName("fromPhone")
  private String fromPhone = null;
  @SerializedName("toName")
  private String toName = null;
  @SerializedName("toAccount")
  private String toAccount = null;
  @SerializedName("status")
  private String status = null;
  @SerializedName("detail")
  private List<DepositSlipDetail> detail = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getUserRut() {
    return userRut;
  }
  public void setUserRut(String userRut) {
    this.userRut = userRut;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Long getDepositId() {
    return depositId;
  }
  public void setDepositId(Long depositId) {
    this.depositId = depositId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getFromName() {
    return fromName;
  }
  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getFromPhone() {
    return fromPhone;
  }
  public void setFromPhone(String fromPhone) {
    this.fromPhone = fromPhone;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getToName() {
    return toName;
  }
  public void setToName(String toName) {
    this.toName = toName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getToAccount() {
    return toAccount;
  }
  public void setToAccount(String toAccount) {
    this.toAccount = toAccount;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<DepositSlipDetail> getDetail() {
    return detail;
  }
  public void setDetail(List<DepositSlipDetail> detail) {
    this.detail = detail;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepositSlip {\n");
    
    sb.append("  userRut: ").append(userRut).append("\n");
    sb.append("  depositId: ").append(depositId).append("\n");
    sb.append("  fromName: ").append(fromName).append("\n");
    sb.append("  fromPhone: ").append(fromPhone).append("\n");
    sb.append("  toName: ").append(toName).append("\n");
    sb.append("  toAccount: ").append(toAccount).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("  detail: ").append(detail).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
