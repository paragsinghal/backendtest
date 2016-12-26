package com.craftsvilla.backendtest.foodtrucks.permit.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.craftsvilla.backendtest.foodtrucks.dao.AbstractDao;
import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.permit.dao.PermitDao;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.entity.AbstractEntity;

/**
 * @author parag
 *
 *         Permit Dao Implementation.
 */
@Service
public class PermitDaoImpl extends AbstractDao implements PermitDao {

	@Autowired
	AbstractDao abstractDao;

	public PermitDaoImpl() {
		super();
	}

	public void create(Permit p) throws Exception {
		p.setCreationTime(System.currentTimeMillis());
		p.setLastUpdatedTime(System.currentTimeMillis());
		try {
			if (p != null) {
				saveEntity(p);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void update(Permit p) {
		p.setLastUpdatedTime(System.currentTimeMillis());
		try {
			if (p != null) {
				saveEntity(p);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateAll(List<Permit> p) {
		try {
			if (p != null && !p.isEmpty()) {
				saveAllEntities(p, Permit.class.getSimpleName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Permit getById(String id) {
		Permit feature = null;
		try {
			if (!StringUtils.isEmpty(id)) {
				feature = (Permit) getEntityById(id, Permit.class);
			}
		} catch (Exception ex) {
			// log Exception
			feature = null;
		}
		return feature;
	}

	public void update(Permit p, Query q, Update u) {
		try {
			if (p != null) {
				updateEntity(p, q, u);
			}
		} catch (Exception ex) {
		}
	}

	public int deleteById(Long id) {
		int result = 0;
		try {
			result = deleteEntityById(id, Permit.class);
		} catch (Exception ex) {
			// throw Exception;
		}

		return result;
	}

	public List<Permit> getPermits(String id, String applicantName,
			Long beforeExpirationDate, Long afterExpirationDate,
			Long beforeCreationDate, Long afterCreationDate, String streetName,
			PermitStatus status, Integer start, Integer limit, Boolean isSearch)
			throws Throwable {

		try {
			Query query = new Query();
			if (id != null) {
				query.addCriteria(Criteria.where("id").is(id));
			} else {
				if (applicantName != null) {
					if (isSearch) {
						query.addCriteria(Criteria.where(
								Permit.Constants.APPLICANT).regex(
								applicantName, "i"));
					} else {
						query.addCriteria(Criteria.where(
								Permit.Constants.APPLICANT).is(applicantName));
					}
				}

				if (beforeExpirationDate != null && afterExpirationDate != null) {
					query.addCriteria(Criteria
							.where(Permit.Constants.EXPIRATION_DATE)
							.lt(new Date(beforeExpirationDate))
							.gte(new Date(afterExpirationDate)));
				} else {
					if (beforeExpirationDate != null) {
						query.addCriteria(Criteria.where(
								Permit.Constants.EXPIRATION_DATE).lt(
								new Date(beforeExpirationDate)));
					}
					if (afterExpirationDate != null) {
						query.addCriteria(Criteria.where(
								Permit.Constants.EXPIRATION_DATE).gte(
								new Date(afterExpirationDate)));
					}
				}

				if (beforeCreationDate != null && afterCreationDate != null) {
					query.addCriteria(Criteria
							.where(AbstractEntity.Constants.CREATION_TIME)
							.lt(beforeCreationDate)
							.gte(afterCreationDate));
				} else {
					if (beforeCreationDate != null) {
						query.addCriteria(Criteria.where(
								AbstractEntity.Constants.CREATION_TIME).lt(
								beforeCreationDate));
					}
					if (afterCreationDate != null) {
						query.addCriteria(Criteria.where(
								AbstractEntity.Constants.CREATION_TIME).gte(
								afterCreationDate));
					}
				}
				if (streetName != null) {
					query.addCriteria(Criteria.where(Permit.Constants.ADDRESS)
							.regex(streetName, "i"));
				}

				if (status != null) {
					query.addCriteria(Criteria.where(Permit.Constants.STATUS)
							.is(status));
				}
			}

			if (limit != null) {
				query.limit(limit);
			}

			if (start != null) {
				query.skip(start);
			} else {
				query.skip(0);
			}
			// query.with(new Sort(Sort.Direction.DESC,
			// AbstractEntity.Constants.CREATION_TIME));

			List<Permit> result = abstractDao.runQuery(query, Permit.class);

			return result;
		} catch (Throwable e) {
			throw e;
		}
	}

}
