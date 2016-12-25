package com.craftsvilla.backendtest.foodtrucks.manager;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.craftsvilla.backendtest.foodtrucks.permit.dao.PermitDao;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.AddPermitRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.request.GetPermitsRequest;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.response.PermitResponseVO;

@Service
public class PermitManager {

	@Autowired
	PermitDao permitDao;

	Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

	public List<PermitResponseVO> getPermits(
			GetPermitsRequest getPermitsRequest, Boolean isSearch)
			throws Throwable {

		List<Permit> permits = permitDao.getPermits(null,
				getPermitsRequest.getApplicantName(),
				getPermitsRequest.getBeforeDate(),
				getPermitsRequest.getAfterDate(),
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

	public void addPermit(AddPermitRequest addPermitRequest) throws Exception {
		Permit permit = addPermitRequest.toPermit();
		permitDao.create(permit);
	}

	public void addPermit(Permit permit) throws Exception {
		permitDao.create(permit);
	}
}
