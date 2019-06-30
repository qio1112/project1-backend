package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ProjectEntity;
import com.entity.ResourceEntity;

@Repository
public class ResourceDaoImpl implements ResourceDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ResourceEntity> getResourcesByProjectId(ProjectEntity project) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query<ResourceEntity> query = session.createQuery("select * from resource where project_id=? order by resource_code", ResourceEntity.class);
		query.setParameter(0, projectId);
		return query.getResultList();
	}

	@Override
	public ResourceEntity getResourceByProjectIdResourceCode(ProjectEntity project, String resourceCode) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query<ResourceEntity> query = session.createQuery("select * from resource where project_id=? and resource_code=?", ResourceEntity.class);
		query.setParameter(0, projectId);
		query.setParameter(1, resourceCode);
		return query.getSingleResult();
	}

	@Override
	public void insertResource(ProjectEntity project, String resourceCode, String resourceName) {
		
		Session session = sessionFactory.getCurrentSession();
		ResourceEntity newResource = new ResourceEntity(project, resourceCode, resourceName);
		session.saveOrUpdate(newResource);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteResource(ProjectEntity project, String resourceCode) {

		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete resource where project_id=? and resource_code=?");
		query.setParameter(0, projectId);
		query.setParameter(1, resourceCode);
		query.executeUpdate();
	}

}
