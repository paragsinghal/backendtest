package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request;

import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.craftsvilla.backendtest.foodtrucks.utils.CollectionUtils;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.request.AbstractRequest;

/**
 * @author parag
 *
 *Request pojo for add permit request. 
 */
public class AddPermitRequest extends AbstractRequest{

	private Long locationId;
	private String applicant;
	private String facilityType;
	private Long cnn;
	private String locationDescription;
	private String address;
	private String blockLot;
	private String block;
	private String lot;
	private String foodItems;
	private Double x;
	private Double y;
	private Double latitude;
	private Double longitude;
	private String schedule;
	private String daysHours;
	private int priorPermit;
	private String permit;
	private Long expirationDate;
	
	public AddPermitRequest() {
		super();
	}
	
	public AddPermitRequest(String callingUserId) {
		super(callingUserId);
	}

	public AddPermitRequest(Long locationId, String applicant,
			String facilityType, Long cnn, String locationDescription,
			String address, String blockLot, String block, String lot,
			String foodItems, Double x, Double y, Double latitude,
			Double longitude, String schedule, String daysHours,
			int priorPermit, String permit, Long expirationDate) {
		super();
		this.locationId = locationId;
		this.applicant = applicant;
		this.facilityType = facilityType;
		this.cnn = cnn;
		this.locationDescription = locationDescription;
		this.address = address;
		this.blockLot = blockLot;
		this.block = block;
		this.lot = lot;
		this.foodItems = foodItems;
		this.x = x;
		this.y = y;
		this.latitude = latitude;
		this.longitude = longitude;
		this.schedule = schedule;
		this.daysHours = daysHours;
		this.priorPermit = priorPermit;
		this.permit = permit;
		this.expirationDate = expirationDate;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public Long getCnn() {
		return cnn;
	}

	public void setCnn(Long cnn) {
		this.cnn = cnn;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBlockLot() {
		return blockLot;
	}

	public void setBlockLot(String blockLot) {
		this.blockLot = blockLot;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDaysHours() {
		return daysHours;
	}

	public void setDaysHours(String daysHours) {
		this.daysHours = daysHours;
	}

	public int getPriorPermit() {
		return priorPermit;
	}

	public void setPriorPermit(int priorPermit) {
		this.priorPermit = priorPermit;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public Long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Long expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "AddPermitRequest [locationId=" + locationId + ", applicant="
				+ applicant + ", facilityType=" + facilityType + ", cnn=" + cnn
				+ ", locationDescription=" + locationDescription + ", address="
				+ address + ", blockLot=" + blockLot + ", block=" + block
				+ ", lot=" + lot + ", foodItems=" + foodItems + ", x=" + x
				+ ", y=" + y + ", latitude=" + latitude + ", longitude="
				+ longitude + ", schedule=" + schedule + ", daysHours="
				+ daysHours + ", priorPermit=" + priorPermit + ", permit="
				+ permit + ", expirationDate=" + expirationDate + "]";
	}

	public Permit toPermit() {
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		Permit permit = mapper.map(this, Permit.class);
		return permit;
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
		
		if(applicant==null || applicant.isEmpty()){
			verificationErrors.add("applicant name is mandatory");
		}
		if(address==null || address.isEmpty()){
			verificationErrors.add("address is mandatory");
		}
		if(blockLot==null || blockLot.isEmpty()){
			verificationErrors.add("blockLot is mandatory");
		}
		if(schedule==null || schedule.isEmpty()){
			verificationErrors.add("schedule is mandatory");
		}
		if(permit==null || permit.isEmpty()){
			verificationErrors.add("permit is mandatory");
		}
		if(expirationDate==null || expirationDate<0){
			verificationErrors.add("expirationDate is mandatory");
		}
		
		return verificationErrors;
	}
	
}
