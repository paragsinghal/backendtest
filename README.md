# backendtest
Craftsvilla Backend Challenge App

Assumptions:

1) All the queries pertaining to date will have long argument.
2) Ignored Metadata fields of the dataset.
3) Ignored columns which were null for all the data.
4) Approved permits after expiry time should be marked expired and requested/onhold permits should be marked inactive. 

APIS

1) Get Permit API/Get Permit Search API:   (Path: GET /permit?inputs as query params  && GET /permit/search?inputs as query params)
    
    inputs:  
     1) id : Mongo id of the record
	 2) applicantName : applicant name (String)
	 3) afterExpirationDate : Date After which we want the expiring permits (Long)
	 4) beforeExpirationDate : Date before which we want the expiring permits (Long)
	 5) afterCreationDate : Date After which we want the permits by creation time (Long)
	 6) beforeCreationDate: Date before which we want the permits by creation time (Long)
	 7) streetName : street name of the applicant (String)
	 8) status : Permit status (APPROVED, EXPIRED, INACTIVE, ONHOLD, REQUESTED, SUSPEND)
	 9) start : start of the result set (Integer) for pagination
	 10) limit : size of the result set (Integer) for pagination
	 11) callingUserId : user id of the caller
     
* Only callingUserId is mandatory. Could be extended to use with user authentication.
* Rest all inputs can be null and if provided will give the intersection of results by given parameters.
* Street name query is a wildcard search on Address.
* In Get Permit search API, applicant name is also a wildcard search.

Example use cases:
1) Getting approved Permit based on Permit status whose expiry is after specified expiration date/in range after-before/ before some date.
2) Getting permits based on street name and applicant name.
3) Lots of cases for combination of all the inputs.

Error Cases:
Validation for dates, basic validations(Eg. empty checks, negative values) for applicant name, street name, size, limit.

2)Add Permit API: (POST : /permit/) content-type should be application json

    inputs: should be sent as body
    AddPermitRequest{
    Long locationId;
    String applicant;
	String facilityType;
	Long cnn;
	String locationDescription;
	String address;
	String blockLot;
	String block;
	String lot;
	String foodItems;
	Double x;
	Double y;
	Double latitude;
	Double longitude;
	String schedule;
	String daysHours;
	int priorPermit;
	String permit;
	Long expirationDate;
    String callingUserId
    }
    
    Eg Input:
    
    {
        "address": "TOWNSEND ST: 05TH ST to 06TH ST (400 - 499)",
        "applicant": "F & C Catering",
        "block": "3785",
        "blockLot": "3785004A",
        "callingUserId": "123",
        "cnn": 12664000,
        "daysHours": "Mo-Fr:10AM-11AM",
        "facilityType": "Truck",
        "foodItems": "Cold Truck: Hot/Cold Sandwiches: Water: Soda: Juice: Snacks: Milk: Candies: Canned Food: Soups: Cup of Noodles: Fruit: Salad",
        "latitude": 37.7748713162388,
        "locationDescription": "string",
        "locationId": 762182,
        "longitude": -122.398531708276,
        "lot": "004A",
        "priorPermit": 0,
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=16MFF-0027&ExportPDF=1&Filename=16MFF-0027_schedule.pdf",
        "x": 6012987.023,
        "y": 2110175.723,
        "expirationDate": 123456789,
        "permit": "16MFF-0027"
     }
     
     * Mandatory fields : applicant, address, blockLot, schedule, permit, expirationDate, callingUserId
     * While adding requested date is set and status is set as requested.
     * Response is returned with the id of the object saved.

3) Update Status API  (PUT:  /permit) content-type should be application json

    inputs: sent as body
    updatePermitRequest{
    String permitId : mongoid of the saved permit entry
    PermitStatus status : Permit status (APPROVED, ONHOLD, SUSPEND)
    }
    
    * update status to suspend/ONHOLD/APPROVED
    * if put onhold, NOISent date is updated.
    * if approved, approved date is set.
    * No operation allowed on Inactive/ expired entries.
    
CRONS

1) Setting Approved entries to expired after expiry date is passed. (runs every hour)
2) Setting Requested/Onhold entries to inactive after expiry date is passed. (runs every day)

Extras

1) Abstraction at every layer

Mongo Client Factory
Mongo Configuration
Abstract Dao
Abstract Request
Abstract Response

2) Logging using log4j.

3) profile specific configuration using properties. Properties are detached from the code.

4) Global exception handlers for spring. 

5) Maven is used for dependency management and build.
6) Swagger is used for easy access to APIs.
7) Dozer is used to remove a lot of boilerplate code.

etc.


DataDumper

Script to dump the unformatted json data into mongo.