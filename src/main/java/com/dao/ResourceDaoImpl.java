package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ProjectEntity;
import com.entity.ResourceEntity;

/**
 * DAO of resource information of projects
 * @author yipeng
 *
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ResourceEntity> getResourcesByProject(ProjectEntity project) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ResourceEntity> query = session.createQuery("from ResourceEntity where project=:project order by resourceCode", ResourceEntity.class);
		query.setParameter("project", project);
		return query.getResultList();
	}
	
	@Override
	public ResourceEntity getResourcesByProjectResourceCode(ProjectEntity project, String resourceCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ResourceEntity> query = session.createQuery("from ResourceEntity where project=:project and resourceCode=:resourceCode order by resourceCode", ResourceEntity.class);
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
		return query.getSingleResult();
	}

	@Override
	public ResourceEntity getResourceByProjectResourceCode(ProjectEntity project, String resourceCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ResourceEntity> query = session.createQuery("from ResourceEntity where project=:project and resourceCode=:resourceCode", ResourceEntity.class);
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
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

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from ResourceEntity where project=:project and resourceCode=:resourceCode");
		query.setParameter("project", project);
		query.setParameter("resourceCode", resourceCode);
		query.executeUpdate();
	}

	@Override
	public void insertResource(ResourceEntity newResource) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(newResource);
	}

}
