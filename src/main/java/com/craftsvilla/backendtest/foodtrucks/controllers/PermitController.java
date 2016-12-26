package com.craftsvilla.backendtest.foodtrucks.controllers;

import io.swagger.annotations.Api;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.manager.PermitManager;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.AddPermitRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.GetPermitsRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.UpdatePermitRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.response.PermitResponseVO;
import com.craftsvilla.backendtest.foodtrucks.utils.LogFactory;

/**
 * @author parag
 * 
 *         Controller to get and add permits for Food trucks.
 *
 */
@RestController
@RequestMapping("/permit")
@Api(value = "Permit Apis", description = "Permit APIs")
public class PermitController {

	Logger logger = LogFactory.getLogger(PermitController.class);

	@Autowired
	PermitManager permitManager;

	
	/**
	 * @param id
	 * @param applicantName
	 * @param afterExpirationDate
	 * @param beforeExpirationDate
	 * @param afterCreationDate
	 * @param beforeCreationDate
	 * @param streetName
	 * @param status
	 * @param start
	 * @param limit
	 * @param callingUserId
	 * @return
	 * @throws Throwable
	 * 
	 * Method to get permits based on above params. Only
	 *             callingUserId is mandatory. Full Applicant name
	 *             will be searched.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<PermitResponseVO> getPermits(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "applicantName", required = false) String applicantName,
			@RequestParam(value = "afterExpirationDate", required = false) Long afterExpirationDate,
			@RequestParam(value = "beforeExpirationDate", required = false) Long beforeExpirationDate,
			@RequestParam(value = "afterCreationDate", required = false) Long afterCreationDate,
			@RequestParam(value = "beforeCreationDate", required = false) Long beforeCreationDate,
			@RequestParam(value = "streetName", required = false) String streetName,
			@RequestParam(value = "status", required = false) PermitStatus status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "callingUserId", required = true) String callingUserId)
			throws Throwable {

		GetPermitsRequest getPermitsRequest = new GetPermitsRequest(id,
				callingUserId, applicantName, afterExpirationDate,
				beforeExpirationDate, afterCreationDate, beforeCreationDate,
				streetName, status, start, limit);
		getPermitsRequest.verify();
		logger.info("Request received for getting permits for : "
				+ getPermitsRequest.toString());
		List<PermitResponseVO> result = permitManager.getPermits(
				getPermitsRequest, false);
		logger.info("result for getting permits  : " + result.toString());
		return result;
	}


	/**
	 * @param id
	 * @param applicantName
	 * @param afterExpirationDate
	 * @param beforeExpirationDate
	 * @param afterCreationDate
	 * @param beforeCreationDate
	 * @param streetName
	 * @param status
	 * @param start
	 * @param limit
	 * @param callingUserId
	 * @return
	 * @throws Throwable
	 * 
	 * Method to get permits based on above params. Only
	 *             callingUserId is mandatory. Partial/wilcard Applicant name
	 *             will be searched.
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<PermitResponseVO> getPermitsSearch(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "applicantName", required = false) String applicantName,
			@RequestParam(value = "afterExpirationDate", required = false) Long afterExpirationDate,
			@RequestParam(value = "beforeExpirationDate", required = false) Long beforeExpirationDate,
			@RequestParam(value = "afterCreationDate", required = false) Long afterCreationDate,
			@RequestParam(value = "beforeCreationDate", required = false) Long beforeCreationDate,
			@RequestParam(value = "streetName", required = false) String streetName,
			@RequestParam(value = "status", required = false) PermitStatus status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "callingUserId", required = true) String callingUserId)
			throws Throwable {

		GetPermitsRequest getPermitsRequest = new GetPermitsRequest(id,
				callingUserId, applicantName, afterExpirationDate,
				beforeExpirationDate, afterCreationDate, beforeCreationDate,
				streetName, status, start, limit);
		getPermitsRequest.verify();
		logger.info("Request received for getting permits for : "
				+ getPermitsRequest.toString());
		List<PermitResponseVO> result = permitManager.getPermits(
				getPermitsRequest, true);
		logger.info("result for getting permits  : " + result.toString());
		return result;

	}

	/**
	 * @param addPermitRequest
	 * @throws Throwable
	 * 
	 *             Method to add a permit.
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PermitResponseVO addPermit(
			@RequestBody AddPermitRequest addPermitRequest) throws Throwable {

		logger.info("Request received for adding permit for : "
				+ addPermitRequest.toString());
		addPermitRequest.verify();
		PermitResponseVO permitResponseVO = permitManager
				.addPermit(addPermitRequest);
		logger.info("result for added succesfully  : "
				+ permitResponseVO.toString());
		return permitResponseVO;
	}

	/**
	 * @param updatePermitRequest
	 * @throws Throwable
	 * 
	 *             Method to update Permit status
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PermitResponseVO updatePermitStatus(
			@RequestBody UpdatePermitRequest updatePermitRequest)
			throws Throwable {

		logger.info("Request received for update permit status for : "
				+ updatePermitRequest.toString());
		updatePermitRequest.verify();
		PermitResponseVO permitResponseVO = permitManager
				.updatePermitStatus(updatePermitRequest);
		logger.info("updated status succesfully  : "
				+ permitResponseVO.toString());
		return permitResponseVO;
	}

}
