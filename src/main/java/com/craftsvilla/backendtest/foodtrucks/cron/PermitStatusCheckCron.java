package com.craftsvilla.backendtest.foodtrucks.cron;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.permit.dao.PermitDao;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.craftsvilla.backendtest.foodtrucks.utils.LogFactory;

/**
 * @author parag
 *
 *Crons for updating status of Permit. 
 *Status is marked expired after expiry date. This cron runs every hour.
 *Status for REQUESTED/ONHOLD permits which are still pending after an year are marked Inactive.
 */
@Component
public class PermitStatusCheckCron {

	@Autowired
	PermitDao permitDao;

	Logger logger = LogFactory.getLogger(PermitStatusCheckCron.class);
	
	@Scheduled(cron = "0 0 * * * *")
	public void expiryCheck() {

		try {
			List<Permit> permits = permitDao.getPermits(null, null,
					System.currentTimeMillis(), null, null, null, null,
					PermitStatus.APPROVED, null, null, false);

			logger.info(permits.toString());
			for (Permit permit : permits) {
				permit.setStatus(PermitStatus.EXPIRED);
			}
			permitDao.updateAll(permits);

		} catch (Throwable e) {
			logger.error("marking expired failed for:" + new Date());
		}

	}
	
	/**
	 * 
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void inactiveCheck() {

		try {
			List<Permit> permits = permitDao.getPermits(null, null,
					System.currentTimeMillis(), null, null, null, null,
					PermitStatus.REQUESTED, null, null, false);
			permits.addAll(permitDao.getPermits(null, null,
					System.currentTimeMillis(), null, null, null, null,
					PermitStatus.ONHOLD, null, null, false));

			for (Permit permit : permits) {
				permit.setStatus(PermitStatus.INACTIVE);
			}
			permitDao.updateAll(permits);

		} catch (Throwable e) {
			logger.error("marking inactive failed for:" + new Date());
		}

	}

}
