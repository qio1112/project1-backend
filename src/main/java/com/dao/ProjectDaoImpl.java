package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ProjectEntity;
import com.exception.DataNotFoundException;

/**
 * DAO of project information
 * @author yipeng
 *
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ProjectEntity> getProjects() throws DataNotFoundException {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity order by id", ProjectEntity.class);
		List<ProjectEntity> result = query.getResultList();
		if(result == null || result.size() == 0) {
			throw new DataNotFoundException("No project in database");
		}
		return result;
	}

	@Override
	public ProjectEntity getProjectById(int id) throws DataNotFoundException {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where id=:id", ProjectEntity.class);
		query.setParameter("id", id);
		ProjectEntity result = query.getSingleResult();
		if(result == null) {
			throw new DataNotFoundException("No such project found");
		}
		return result;
	}

	@Override
	public ProjectEntity getProjectByName(String projectName) throws DataNotFoundException {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where projectName=:projectName order by id", ProjectEntity.class);
		query.setParameter("projectName", projectName);
		ProjectEntity result = query.getSingleResult();
		if(result == null) {
			throw new DataNotFoundException("No such project found");
		}
		return result;
	}
	
	//insert
	@Override
	public void insertProject(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		ProjectEntity newProject = new ProjectEntity(name);
		session.saveOrUpdate(newProject);
	}

	@Override
	public void insertProject(ProjectEntity newProject) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(newProject);
	}

}
