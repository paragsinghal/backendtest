package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request;

import java.util.List;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.utils.CollectionUtils;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.request.AbstractRequest;

/**
 * @author parag
 *
 *Request pojo for get permits request.
 */
public class GetPermitsRequest extends AbstractRequest {

	private String id;
	private String applicantName;
	private Long afterExpirationDate;
	private Long beforeExpirationDate;
	private Long afterCreationDate;
	private Long beforeCreationDate;
	private String streetName;
	private PermitStatus status;
	private Integer start;
	private Integer limit;

	public GetPermitsRequest() {
		super();
	}

	public GetPermitsRequest( String id, String callingUserId,
			String applicantName, Long afterExpirationDate,
			Long beforeExpirationDate, Long afterCreationDate,
			Long beforeCreationDate, String streetName, PermitStatus status,
			Integer start, Integer limit) {
		super(callingUserId);
		this.id = id;
		this.applicantName = applicantName;
		this.afterExpirationDate = afterExpirationDate;
		this.beforeExpirationDate = beforeExpirationDate;
		this.afterCreationDate = afterCreationDate;
		this.beforeCreationDate = beforeCreationDate;
		this.streetName = streetName;
		this.status = status;
		this.start = start;
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getAfterExpirationDate() {
		return afterExpirationDate;
	}

	public void setAfterExpirationDate(Long afterExpirationDate) {
		this.afterExpirationDate = afterExpirationDate;
	}

	public Long getBeforeExpirationDate() {
		return beforeExpirationDate;
	}

	public void setBeforeExpirationDate(Long beforeExpirationDate) {
		this.beforeExpirationDate = beforeExpirationDate;
	}

	public Long getAfterCreationDate() {
		return afterCreationDate;
	}

	public void setAfterCreationDate(Long afterCreationDate) {
		this.afterCreationDate = afterCreationDate;
	}

	public Long getBeforeCreationDate() {
		return beforeCreationDate;
	}

	public void setBeforeCreationDate(Long beforeCreationDate) {
		this.beforeCreationDate = beforeCreationDate;
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
		return "GetPermitsRequest [id=" + id + ", applicantName="
				+ applicantName + ", afterExpirationDate="
				+ afterExpirationDate + ", beforeExpirationDate="
				+ beforeExpirationDate + ", afterCreationDate="
				+ afterCreationDate + ", beforeCreationDate="
				+ beforeCreationDate + ", streetName=" + streetName
				+ ", status=" + status + ", start=" + start + ", limit="
				+ limit + "]";
	}

	public void verify() throws IllegalArgumentException {
		super.verify();
		List<String> verificationErrors = collectVerificationErrors();
		if (CollectionUtils.isNotEmpty(verificationErrors)) {
			throw new IllegalArgumentException(verificationErrors.toString());
		}
	}

	protected List<String> collectVerificationErrors() {
		List<String> verificationErrors = 	super.collectVerificationErrors();

		if (afterExpirationDate != null && afterExpirationDate < 0) {
			verificationErrors.add("AFTER Expiration DATE Invalid");
		}

		if (beforeExpirationDate != null && beforeExpirationDate < 0) {
			verificationErrors.add("BEFORE Expiration DATE Invalid");
		}
		
		if (afterExpirationDate != null && beforeExpirationDate != null && afterExpirationDate > beforeExpirationDate) {
			verificationErrors.add("AFTER Expiration DATE cannot be greater than before expiration date");
		}
		
		if (afterCreationDate != null && afterCreationDate < 0) {
			verificationErrors.add("AFTER Creation DATE Invalid");
		}

		if (beforeCreationDate != null && beforeCreationDate < 0) {
			verificationErrors.add("BEFORE creation DATE Invalid");
		}
		
		if (afterCreationDate != null && beforeCreationDate != null && afterCreationDate > beforeCreationDate) {
			verificationErrors.add("AFTER creation DATE cannot be greater than before creation date");
		}

		if (applicantName != null && applicantName.isEmpty()) {
			verificationErrors.add("Applicant Name Empty");
		}

		if (streetName != null && streetName.isEmpty()) {
			verificationErrors.add("Street Name Empty");
		}

		if (start != null && start < 0) {
			verificationErrors.add("start not correct");
		}

		if (limit != null && limit <= 0) {
			verificationErrors.add("limit not correct");
		}

		return verificationErrors;
	}

}
