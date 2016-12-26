package com.craftsvilla.backendtest.foodtrucks.viewobjects.request;

import java.util.ArrayList;
import java.util.List;

import com.craftsvilla.backendtest.foodtrucks.utils.CollectionUtils;

/**
 * @author parag
 *
 *Abstract request for each frontend request. 
 *All properties which are neccesary for any request can be put here. Like user authentication.
 */
public abstract class AbstractRequest {

	private String callingUserId;

	public AbstractRequest() {
		super();
	}

	public AbstractRequest(String callingUserId) {
		super();
		this.callingUserId = callingUserId;
	}

	public String getCallingUserId() {
		return callingUserId;
	}

	public void setCallingUserId(String callingUserId) {
		this.callingUserId = callingUserId;
	}
	
	public void verify() throws IllegalArgumentException {

		List<String> verificationErrors = collectVerificationErrors();
		if (CollectionUtils.isNotEmpty(verificationErrors)) {
			throw new IllegalArgumentException(verificationErrors.toString());
		}
	}

	protected List<String> collectVerificationErrors() {

		List<String> verificationErrors = new ArrayList<String>();
		
		if(callingUserId==null || callingUserId.isEmpty()){
			verificationErrors.add("CALLING_USER_ID");
		}
		
		return verificationErrors;
	}

	@Override
	public String toString() {
		return "AbstractRequest [callingUserId=" + callingUserId + "]";
	}
	
}
