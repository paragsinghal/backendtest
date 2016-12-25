package com.craftsvilla.backendtest.foodtrucks.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.craftsvilla.backendtest.foodtrucks.database.mongo.AbstractMongoConfiguration;
import com.craftsvilla.backendtest.foodtrucks.viewobjects.entity.AbstractEntity;
import com.mongodb.WriteResult;

@Service
public class AbstractDao extends AbstractMongoConfiguration {

	public static final String ORDER_DESC = "desc";
	public static final String ORDER_ASC = "asc";
	public static final String QUERY_AND_OPERATOR = "&&";

	public static final long NO_START = 0;
	public static final long NO_LIMIT = -1;
	public static final long UNINITIALIZED = -1L;

	public AbstractDao() {
		super();
	}

	protected <E extends AbstractEntity> void saveEntity(E entity) {
		setEntityDefaultProperties(entity);
		getMongoOperations().insert(entity, entity.getClass().getSimpleName());
	}

	protected <E extends AbstractEntity> void saveAllEntities(
			Collection<E> entities, String collectionName) {
		setEntityDefaultProperties(entities);
		if (entities != null && !entities.isEmpty()) {
			for (E entity : entities) {
				getMongoOperations().save(entity, collectionName);
			}
		}
	}

	protected <E extends AbstractEntity> void updateEntity(E entity, Query q,
			Update u) {
		setEntityDefaultProperties(entity);
		getMongoOperations().upsert(q, u, entity.getClass().getSimpleName());
	}

	public <T extends AbstractEntity> T findAndModifyEntity(Query q, Update u,
			FindAndModifyOptions o, Class<T> calzz) {
		return getMongoOperations().findAndModify(q, u, o, calzz,
				calzz.getSimpleName());
	}

	public <T extends AbstractEntity> List<T> getEntities(Class<T> calzz) {
		return getMongoOperations().findAll(calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> T getEntityById(String id, Class<T> calzz) {
		Query query = new Query(Criteria.where("_id").is(id));
		query.addCriteria(Criteria.where("enabled").is(true));
		return getMongoOperations()
				.findOne(query, calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> List<T> runQuery(Query query,
			Class<T> calzz) {
		return getMongoOperations().find(query, calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> int queryCount(Query query, Class<T> calzz) {
		return getMongoOperations().find(query, calzz, calzz.getSimpleName())
				.size();
	}

	public <T> int deleteEntityById(Long id, Class<T> calzz) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = getMongoOperations().remove(query, calzz,
				calzz.getSimpleName());
		return result.getN();
	}

	public static <E extends AbstractEntity> void setEntityDefaultProperties(
			Collection<E> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (E entity : entities) {
				setEntityDefaultProperties(entity);
			}
		}
	}

	public static <E extends AbstractEntity> void setEntityDefaultProperties(
			E entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setCreationTime(System.currentTimeMillis());
			entity.setCreatedBy(entity.getCreatedBy());
		}
		entity.setLastUpdatedTime(System.currentTimeMillis());
	}
}
