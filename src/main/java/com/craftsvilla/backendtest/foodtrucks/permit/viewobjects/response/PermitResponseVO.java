package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.response;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.response.AbstractResponse;

/**
 * @author parag
 *
 *         Response view object for permit requests.
 */
public class PermitResponseVO extends AbstractResponse {

	private String id;
	private Long locationId;
	private String applicant;
	private String facilityType;
	private Long cnn;
	private String locationDescription;
	private String address;
	private String blockLot;
	private String block;
	private String lot;
	private String permit;
	private PermitStatus status;
	private String foodItems;
	private Double x;
	private Double y;
	private Double latitude;
	private Double longitude;
	private String schedule;
	private String daysHours;
	private String NOISent;
	private String approved;
	private String received;
	private int priorPermit;
	private String expirationDate;

	public PermitResponseVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public PermitStatus getStatus() {
		return status;
	}

	public void setStatus(PermitStatus status) {
		this.status = status;
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

	public String getNOISent() {
		return NOISent;
	}

	public void setNOISent(String nOISent) {
		NOISent = nOISent;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public int getPriorPermit() {
		return priorPermit;
	}

	public void setPriorPermit(int priorPermit) {
		this.priorPermit = priorPermit;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "PermitResponseVO [id=" + id + ", locationId=" + locationId
				+ ", applicant=" + applicant + ", facilityType=" + facilityType
				+ ", cnn=" + cnn + ", locationDescription="
				+ locationDescription + ", address=" + address + ", blockLot="
				+ blockLot + ", block=" + block + ", lot=" + lot + ", permit="
				+ permit + ", status=" + status + ", foodItems=" + foodItems
				+ ", x=" + x + ", y=" + y + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", schedule=" + schedule
				+ ", daysHours=" + daysHours + ", NOISent=" + NOISent
				+ ", approved=" + approved + ", received=" + received
				+ ", priorPermit=" + priorPermit + ", expirationDate="
				+ expirationDate + "]";
	}

}
