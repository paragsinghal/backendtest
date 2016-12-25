package com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity;

import java.util.Date;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.entity.AbstractEntity;

public class Permit extends AbstractEntity {

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
	private Date NOISent;
	private Date approved;
	private Date received;
	private int priorPermit;
	private Date expirationDate;

	public Permit() {
		super();
	}

	public Permit(Long locationId, String applicant, String facilityType,
			Long cnn, String locationDescription, String address,
			String blockLot, String block, String lot, String permit,
			PermitStatus status, String foodItems, Double x, Double y,
			Double latitude, Double longitude, String schedule,
			String daysHours, Date nOISent, Date approved, Date received,
			int priorPermit, Date expirationDate) {
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
		this.permit = permit;
		this.status = status;
		this.foodItems = foodItems;
		this.x = x;
		this.y = y;
		this.latitude = latitude;
		this.longitude = longitude;
		this.schedule = schedule;
		this.daysHours = daysHours;
		NOISent = nOISent;
		this.approved = approved;
		this.received = received;
		this.priorPermit = priorPermit;
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

	public Date getNOISent() {
		return NOISent;
	}

	public void setNOISent(Date nOISent) {
		NOISent = nOISent;
	}

	public Date getApproved() {
		return approved;
	}

	public void setApproved(Date approved) {
		this.approved = approved;
	}

	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public int getPriorPermit() {
		return priorPermit;
	}

	public void setPriorPermit(int priorPermit) {
		this.priorPermit = priorPermit;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Permit [locationId=" + locationId + ", applicant=" + applicant
				+ ", facilityType=" + facilityType + ", cnn=" + cnn
				+ ", locationDescription=" + locationDescription + ", address="
				+ address + ", blockLot=" + blockLot + ", block=" + block
				+ ", lot=" + lot + ", permit=" + permit + ", status=" + status
				+ ", foodItems=" + foodItems + ", x=" + x + ", y=" + y
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", schedule=" + schedule + ", daysHours=" + daysHours
				+ ", NOISent=" + NOISent + ", approved=" + approved
				+ ", received=" + received + ", priorPermit=" + priorPermit
				+ ", expirationDate=" + expirationDate + "]";
	}

	public static class Constants {
		public static final String APPLICANT = "applicant";
		public static final String ADDRESS = "address";
		public static final String EXPIRATION_DATE = "expirationDate";
		public static String STATUS = "status";
	}

}
