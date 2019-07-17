package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.DataEntity;
import com.entity.ProjectEntity;

/**
 * Project (resource) DAO
 * @author yipeng
 *
 */
@Repository
public class DataDaoImpl implements DataDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	// selections
	
	@Override
	public List<DataEntity> getDataByProject(ProjectEntity project) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where project=:project order by resourceCode", DataEntity.class);
		query.setParameter("project", project);
		return query.getResultList();
	}

	@Override
	public List<DataEntity> getDataByProjectResourceCode(ProjectEntity project, String resourceCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where project=:project and resourceCode=:resourceCode order by columnName", DataEntity.class);
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
		return query.getResultList();
	}

	@Override
	public DataEntity getDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where project=:project and resourceCode=:resourceCode and columnName=:columnCode", DataEntity.class);
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
		query.setParameter("columnName", columnName);
		return query.getSingleResult();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<DataEntity> getDataByProjectAndRange(ProjectEntity project, int start, int end) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		String queryString = 
				"select project_id, resource_code, column_name, value, type from(\n" + 
				"select * from \n" + 
				"	(select *, row_number() over (partition by column_name order by resource_code, column_name) as row_num \n" + 
				"    from data) as a  \n" + 
				"where project_id=:projectId\n" + 
				"having row_num between :start and :end) as b";
		Query query = session.createNativeQuery(queryString);
		
		query.setParameter("projectId", projectId);
		query.setParameter("start", start);
		query.setParameter("end", end);
		List<DataEntity> result = new ArrayList<>();
		List<Object[]> rawResult = query.getResultList();
		for (Object[] resultItem : rawResult) {
			DataEntity curDataEntity = new DataEntity(project, (String)resultItem[1], (String)resultItem[2], (String)resultItem[3], (String)resultItem[4]);
			result.add(curDataEntity);
		}
		return result;
		
	}
	
	// insertion 
	
	@Override
	public void insertData(ProjectEntity project, String resourceCode, String columnName, String value, String type) {
		
		Session session = sessionFactory.getCurrentSession();
		DataEntity newData = new DataEntity(project, resourceCode, columnName, value, type);
		session.saveOrUpdate(newData);
	}

	// update
	@Override
	public void updateData(DataEntity updatedData) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(updatedData);
	}

	// deletion
	@SuppressWarnings("rawtypes")
	@Override
	public int deleteData(ProjectEntity project, String resourceCode, String columnName) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where project=:project and resourceCode=:resourceCode and columnName=:columnName");
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
		query.setParameter("columnName", columnName);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteDataByProjectIdResourceId(ProjectEntity project, String resourceCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where projectId=:projectId and resourceCode=:resourceCode");
		query.setParameter("projectId", project.getId());
		query.setParameter("resourceCode", resourceCode);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteDataByProject(ProjectEntity project) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where projectId=:projectId");
		query.setParameter("projectId", project.getId());
		return query.executeUpdate();
	}

	@Override
	public void insertData(DataEntity newDataEntity) {		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(newDataEntity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteDataByProjectColumnName(ProjectEntity project, String columnName) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where project=:project and column_name=:columnName");
		query.setParameter("project", project);
		query.setParameter("columnName", columnName);
		return query.executeUpdate();
	}

}
