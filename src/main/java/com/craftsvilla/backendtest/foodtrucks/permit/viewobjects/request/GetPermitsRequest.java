package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request;

import java.util.ArrayList;
import java.util.List;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.utils.CollectionUtils;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.request.AbstractRequest;

public class GetPermitsRequest extends AbstractRequest {

	private String applicantName;
	private Long afterDate;
	private Long beforeDate;
	private String streetName;
	private PermitStatus status;
	private Integer start;
	private Integer limit;

	public GetPermitsRequest() {
		super();
	}

	public GetPermitsRequest(String callingUserId, String applicantName,
			Long afterDate, Long beforeDate, String streetName, PermitStatus status, Integer start,
			Integer limit) {
		super(callingUserId);
		this.applicantName = applicantName;
		this.afterDate = afterDate;
		this.beforeDate = beforeDate;
		this.streetName = streetName;
		this.status = status;
		this.start = start;
		this.limit = limit;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(Long afterDate) {
		this.afterDate = afterDate;
	}

	public Long getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(Long beforeDate) {
		this.beforeDate = beforeDate;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public PermitStatus getStatus() {
		return status;
	}

	public void setStatus(PermitStatus status) {
		this.status = status;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "GetPermitsRequest [applicantName=" + applicantName
				+ ", afterDate=" + afterDate + ", beforeDate=" + beforeDate
				+ ", streetName=" + streetName + ", status=" + status
				+ ", start=" + start + ", limit=" + limit + "]";
	}

	public void verify() throws IllegalArgumentException {
		super.verify();
		List<String> verificationErrors = collectVerificationErrors();
		if (CollectionUtils.isNotEmpty(verificationErrors)) {
			throw new IllegalArgumentException(verificationErrors.toString());
		}
	}

	protected List<String> collectVerificationErrors() {
		super.collectVerificationErrors();
		List<String> verificationErrors = new ArrayList<String>();

		if (afterDate != null && afterDate < 0) {
			verificationErrors.add("AFTER DATE Invalid");
		}

		if (beforeDate != null && beforeDate < 0) {
			verificationErrors.add("BEFORE DATE Invalid");
		}

		if (applicantName != null && applicantName.isEmpty()) {
			verificationErrors.add("Applicant Name Empty");
		}

		if (streetName != null && streetName.isEmpty()) {
			verificationErrors.add("Street Name Empty");
		}

		if (start != null && start <= 0) {
			verificationErrors.add("start not correct");
		}

		if (limit != null && limit <= 0) {
			verificationErrors.add("limit not correct");
		}

		return verificationErrors;
	}

}
