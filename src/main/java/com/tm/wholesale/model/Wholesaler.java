package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tm.wholesale.validation.WholesalerChangePasswordValidatedMark;
import com.tm.wholesale.validation.WholesalerCreateValidatedMark;
import com.tm.wholesale.validation.WholesalerEditValidatedMark;
import com.tm.wholesale.validation.WholesalerLoginValidatedMark;

public class Wholesaler implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	@NotNull(groups = { WholesalerEditValidatedMark.class })
	private Integer id;
	private String company_name;
	@NotEmpty(groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	private String name;
	@NotEmpty(groups = { WholesalerLoginValidatedMark.class, WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerLoginValidatedMark.class, WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	private String login_name;
	@NotEmpty(groups = { WholesalerLoginValidatedMark.class, WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class, WholesalerChangePasswordValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerLoginValidatedMark.class, WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class, WholesalerChangePasswordValidatedMark.class  })
	private String login_password;
	private String role;
	@NotEmpty(groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	private String cellphone;
	@NotEmpty(groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	@Length(min = 0, max = 30, groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	@Email(groups = { WholesalerCreateValidatedMark.class, WholesalerEditValidatedMark.class })
	private String email;
	private String auth;
	private String memo;
	private String status;
	private Integer company_id;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * TABLE RELATED PROPERTIES
	 */

	private Map<String, Object> params = new HashMap<String, Object>();
	private String[] authArray;
	private String[] midArr;
	private String[] cidArr;
	@NotEmpty(groups = { WholesalerChangePasswordValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerChangePasswordValidatedMark.class  })
	private String old_password;
	@NotEmpty(groups = { WholesalerChangePasswordValidatedMark.class })
	@Length(min = 0, max = 20, groups = { WholesalerChangePasswordValidatedMark.class  })
	private String confirm_password;
	
	private List<MaterialWholesaler> mws = new ArrayList<MaterialWholesaler>();
	private List<ComboWholesaler> cws = new ArrayList<ComboWholesaler>();
	private Map<Integer, MaterialWholesaler> mwMaps = new HashMap<Integer, MaterialWholesaler>();
	private Map<Integer, ComboWholesaler> cwMaps = new HashMap<Integer, ComboWholesaler>();

	/*
	 * END TABLE RELATED PROPERTIES
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String[] getAuthArray() {
		return authArray;
	}

	public void setAuthArray(String[] authArray) {
		this.authArray = authArray;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String[] getMidArr() {
		return midArr;
	}

	public void setMidArr(String[] midArr) {
		this.midArr = midArr;
	}

	public String[] getCidArr() {
		return cidArr;
	}

	public void setCidArr(String[] cidArr) {
		this.cidArr = cidArr;
	}

	public List<MaterialWholesaler> getMws() {
		return mws;
	}

	public void setMws(List<MaterialWholesaler> mws) {
		this.mws = mws;
	}

	public List<ComboWholesaler> getCws() {
		return cws;
	}

	public void setCws(List<ComboWholesaler> cws) {
		this.cws = cws;
	}

	public Map<Integer, MaterialWholesaler> getMwMaps() {
		return mwMaps;
	}

	public void setMwMaps(Map<Integer, MaterialWholesaler> mwMaps) {
		this.mwMaps = mwMaps;
	}

	public Map<Integer, ComboWholesaler> getCwMaps() {
		return cwMaps;
	}

	public void setCwMaps(Map<Integer, ComboWholesaler> cwMaps) {
		this.cwMaps = cwMaps;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
	

}
