package com.craftsvilla.backendtest.foodtrucks.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.permit.dao.PermitDao;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.AddPermitRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.UpdatePermitRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.GetPermitsRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.response.PermitResponseVO;

/**
 * @author parag
 *
 *Manager for permit management. Adding and getting is supported right now.
 */
@Service
public class PermitManager {

	@Autowired
	PermitDao permitDao;

	Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

	public List<PermitResponseVO> getPermits(
			GetPermitsRequest getPermitsRequest, Boolean isSearch)
			throws Throwable {

		List<Permit> permits = permitDao.getPermits(getPermitsRequest.getId(),
				getPermitsRequest.getApplicantName(),
				getPermitsRequest.getBeforeExpirationDate(),
				getPermitsRequest.getAfterExpirationDate(),
				getPermitsRequest.getBeforeCreationDate(),
				getPermitsRequest.getAfterCreationDate(),
				getPermitsRequest.getStreetName(),
				getPermitsRequest.getStatus(),
				getPermitsRequest.getStart(), getPermitsRequest.getLimit(),
				isSearch);

		return getPermitResponseVO(permits);

	}

	private List<PermitResponseVO> getPermitResponseVO(List<Permit> permits) {

		List<PermitResponseVO> permitResponseVOs = new ArrayList<PermitResponseVO>();

		for (Permit permit : permits) {
			PermitResponseVO permitResponseVO = mapper.map(permit,
					PermitResponseVO.class);
			permitResponseVOs.add(permitResponseVO);
		}

		return permitResponseVOs;
	}

	public PermitResponseVO addPermit(AddPermitRequest addPermitRequest) throws Exception {
		Permit permit = addPermitRequest.toPermit();
		permit.setStatus(PermitStatus.REQUESTED);
		permit.setReceived(new Date(System.currentTimeMillis()));
		permitDao.create(permit);
		
		PermitResponseVO permitResponseVO = mapper.map(permit, PermitResponseVO.class);
		return permitResponseVO;
	}
	
	public PermitResponseVO updatePermitStatus(UpdatePermitRequest updatePermitRequest) throws Exception {
		Permit permit = permitDao.getById(updatePermitRequest.getPermitId());
		if(permit == null){
			throw new IllegalArgumentException("No permit request found for id:" + updatePermitRequest.getPermitId());
		}
		
		if(permit.getStatus().equals(PermitStatus.INACTIVE) || permit.getStatus().equals(PermitStatus.EXPIRED)){
			throw new IllegalArgumentException("No action permitted on inactive/expired requests :" + updatePermitRequest.getPermitId());
		}
		
		switch(updatePermitRequest.getStatus()) {
		case ONHOLD:
			permit.setStatus(PermitStatus.ONHOLD);
			permit.setNOISent(new Date(System.currentTimeMillis()));
			break;
		case SUSPEND:
			permit.setStatus(PermitStatus.SUSPEND);
			break;
		case APPROVED:
			permit.setStatus(PermitStatus.APPROVED);
			permit.setApproved(new Date(System.currentTimeMillis()));
			break;
		default:
			throw new IllegalArgumentException("Permit Status type not supported:");
		}
		
		permitDao.update(permit);
		
		PermitResponseVO permitResponseVO = mapper.map(permit, PermitResponseVO.class);
		return permitResponseVO;
	}	

	public void addPermit(Permit permit) throws Exception {
		permitDao.create(permit);
	}
}
