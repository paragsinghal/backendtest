package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request;

import java.util.List;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.utils.CollectionUtils;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.request.AbstractRequest;

public class UpdatePermitRequest extends AbstractRequest{
	
	private String permitId;
	private PermitStatus status;
	
	public UpdatePermitRequest() {
		super();
	}

	public UpdatePermitRequest(String callingUserId) {
		super(callingUserId);
	}

	public UpdatePermitRequest(String permitId, PermitStatus status) {
		super();
		this.permitId = permitId;
		this.status = status;
	}

	public String getPermitId() {
		return permitId;
	}

	public void setPermitId(String permitId) {
		this.permitId = permitId;
	}

	public PermitStatus getStatus() {
		return status;
	}

	public void setStatus(PermitStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdatePermitRequest [permitId=" + permitId + ", status="
				+ status + "]";
	}
	
	public void verify() throws IllegalArgumentException {
		super.verify();
		List<String> verificationErrors = collectVerificationErrors();
		if (CollectionUtils.isNotEmpty(verificationErrors)) {
			throw new IllegalArgumentException(verificationErrors.toString());
		}
	}

	protected List<String> collectVerificationErrors() {

		List<String> verificationErrors = super.collectVerificationErrors();
		
		if(permitId==null || permitId.isEmpty()){
			verificationErrors.add("Permit id invalid");
		}
		if(status==null){
			verificationErrors.add("status invalid");
		}
		
		return verificationErrors;
	}
	
}
