package com.craftsvilla.backendtest.foodtrucks.controllers;

import io.swagger.annotations.Api;

import java.util.Date;
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
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.response.PermitResponseVO;
import com.craftsvilla.backendtest.foodtrucks.utils.LogFactory;

@RestController
@RequestMapping("/permit")
@Api(value = "Permit Apis", description = "Permit APIs")
public class PermitController {

	Logger logger = LogFactory.getLogger(PermitController.class);

	@Autowired
	PermitManager permitManager;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<PermitResponseVO> getPermits(
			@RequestParam(value = "applicantName", required = false) String applicantName,
			@RequestParam(value = "afterDate", required = false) Long afterDate,
			@RequestParam(value = "beforeDate", required = false) Long beforeDate,
			@RequestParam(value = "streetName", required = false) String streetName,
			@RequestParam(value = "status", required = false) PermitStatus status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "callingUserId", required = true) String callingUserId)
			throws Throwable {

		GetPermitsRequest getPermitsRequest = new GetPermitsRequest(
				callingUserId, applicantName, afterDate, beforeDate,
				streetName, status, start, limit);
		getPermitsRequest.verify();
		logger.info("Request received for getting permits for : "
				+ getPermitsRequest.toString());
		List<PermitResponseVO> result = permitManager.getPermits(
				getPermitsRequest, false);
		logger.info("result for getting permits  : " + result.toString());
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<PermitResponseVO> getPermitsSearch(
			@RequestParam(value = "applicantName", required = false) String applicantName,
			@RequestParam(value = "afterDate", required = false) Long afterDate,
			@RequestParam(value = "beforeDate", required = false) Long beforeDate,
			@RequestParam(value = "streetName", required = false) String streetName,
			@RequestParam(value = "status", required = false) PermitStatus status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "callingUserId", required = true) String callingUserId)
			throws Throwable {

		GetPermitsRequest getPermitsRequest = new GetPermitsRequest(
				callingUserId, applicantName, afterDate, beforeDate,
				streetName,status, start, limit);
		getPermitsRequest.verify();
		logger.info("Request received for getting permits for : "
				+ getPermitsRequest.toString());
		List<PermitResponseVO> result = permitManager.getPermits(
				getPermitsRequest, true);
		logger.info("result for getting permits  : " + result.toString());
		return result;

	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addPermit(@RequestBody AddPermitRequest addPermitRequest)
			throws Throwable {

		logger.info("Request received for adding permit for : "
				+ addPermitRequest.toString());
		addPermitRequest.verify();
		permitManager.addPermit(addPermitRequest);
		logger.info("result for added succesfully  : ");
	}

}
