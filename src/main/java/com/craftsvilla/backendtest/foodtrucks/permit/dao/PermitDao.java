package com.craftsvilla.backendtest.foodtrucks.permit.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;

public interface PermitDao {

	public void create(Permit p) throws Exception;

	public void update(Permit p);

	public void updateAll(List<Permit> p);

	public Permit getById(String id);

	public void update(Permit p, Query q, Update u);

	public int deleteById(Long id);

	public List<Permit> getPermits(String id, String applicantName,
			Long beforeDate, Long afterDate, String streetName, PermitStatus status, Integer start,
			Integer limit, Boolean isSearch) throws Throwable;

}
