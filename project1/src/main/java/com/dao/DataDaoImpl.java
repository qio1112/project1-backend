package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.DataEntity;
import com.entity.ProjectEntity;

@Repository
public class DataDaoImpl implements DataDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	// selections
	
	@Override
	public List<DataEntity> getDataByProject(ProjectEntity project) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where projectId=? order by resourceCode", DataEntity.class);
		query.setParameter(0, projectId);
		return query.getResultList();
	}

	@Override
	public List<DataEntity> getDataByProjectResourceCode(ProjectEntity project, String resourceCode) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where projectId=? and resourceCode=? order by columnName", DataEntity.class);
		query.setParameter(0, projectId);
		query.setParameter(1, resourceCode);
		return query.getResultList();
	}

	@Override
	public DataEntity getDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query<DataEntity> query = session.createQuery("from DataEntity where projectId=? and resourceCode=? and columnName=?", DataEntity.class);
		query.setParameter(0, projectId);
		query.setParameter(1, resourceCode);
		query.setParameter(2,  columnName);
		return query.getSingleResult();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<DataEntity> getDataByProjectAndRange(ProjectEntity project, int start, int end) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		String queryString = 
				"select *, row_num\n" + 
				" from \n" + 
				" (select *, row_number() over (partition by column_name order by resource_code, column_name) as row_num from data) as a \n" + 
				" where project_id=? having row_num between ? and ?;";
		Query<DataEntity> query = session.createSQLQuery(queryString);
		query.setParameter(0, projectId);
		query.setParameter(1, start);
		query.setParameter(2, end);
		return query.getResultList();
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
	public int deleteData(ProjectEntity project, String resourceCode, String column_name) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where projectId=? and resourceCode=? and columnName=?");
		query.setParameter(0, project.getId());
		query.setParameter(1, resourceCode);
		query.setParameter(2, column_name);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteDataByProjectIdResourceId(ProjectEntity project, String resourceCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where projectId=? and resourceCode=?");
		query.setParameter(0, project.getId());
		query.setParameter(1, resourceCode);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteDataByProject(ProjectEntity project) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from DataEntity where projectId=?");
		query.setParameter(0, project.getId());
		return query.executeUpdate();
	}

	@Override
	public void insertData(DataEntity newDataEntity) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(newDataEntity);
	}

}
