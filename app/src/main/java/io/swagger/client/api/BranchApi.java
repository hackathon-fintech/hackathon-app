package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.Pair;

import io.swagger.client.model.*;

import java.util.*;

import io.swagger.client.model.UserAccount;
import io.swagger.client.model.DepositSlip;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class BranchApi {
  String basePath = "http://hackathonbi.cloudapp.net:8080/banco-inteligente/rest";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  
  /**
   * usuarios en una sucursal con una accion especifica
   * branchName=MONEDA, action=TELLER,INFORMATION,EXECUTIVE
   * @param token 
   * @param branchName 
   * @param action 
   * @return List<UserAccount>
   */
  public List<UserAccount>  getJsonAll (String token, String branchName, String action) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/branch/user".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "branchName", branchName));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "action", action));
    

    
    headerParams.put("token", ApiInvoker.parameterToString(token));
    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<UserAccount>) ApiInvoker.deserialize(response, "array", UserAccount.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * trae depositos de un cliente
   * 
   * @param token 
   * @param rut 
   * @return List<DepositSlip>
   */
  public List<DepositSlip>  getJson (String token, String rut) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/branch/user/deposit".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    
    headerParams.put("token", ApiInvoker.parameterToString(token));
    
    headerParams.put("rut", ApiInvoker.parameterToString(rut));
    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<DepositSlip>) ApiInvoker.deserialize(response, "array", DepositSlip.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * cliente o ejecutivo graba o modifica un deposito
   * status=NEW,IGNORE,DONE type=CHECK,CASH
   * @param token 
   * @param rut 
   * @param body 
   * @return DepositSlip
   */
  public DepositSlip  putJson (String token, String rut, DepositSlip body) throws ApiException {
    Object postBody = body;
    

    // create path and map variables
    String path = "/branch/user/deposit".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    
    headerParams.put("token", ApiInvoker.parameterToString(token));
    
    headerParams.put("rut", ApiInvoker.parameterToString(rut));
    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (DepositSlip) ApiInvoker.deserialize(response, "", DepositSlip.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
