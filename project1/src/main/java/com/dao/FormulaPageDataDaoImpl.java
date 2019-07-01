package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.FormulaPageDataEntity;
import com.entity.ProjectEntity;

/**
 * DAO of non-formula data in formula page
 * @author yipeng
 *
 */
@Repository
public class FormulaPageDataDaoImpl implements FormulaPageDataDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	// selections
	
		@Override
		public List<FormulaPageDataEntity> getFormulaPageDataByProject(ProjectEntity project) {
			
			int projectId = project.getId();
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where projectId=? order by resourceCode", FormulaPageDataEntity.class);
			query.setParameter(0, projectId);
			return query.getResultList();
		}

		@Override
		public List<FormulaPageDataEntity> getFormulaPageDataByProjectResourceCode(ProjectEntity project, String resourceCode) {
			
			int projectId = project.getId();
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where projectId=? and resourceCode=? order by columnName", FormulaPageDataEntity.class);
			query.setParameter(0, projectId);
			query.setParameter(1, resourceCode);
			return query.getResultList();
		}

		@Override
		public FormulaPageDataEntity getFormulaPageDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName) {
			
			int projectId = project.getId();
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where projectId=? and resourceCode=? and columnName=?", FormulaPageDataEntity.class);
			query.setParameter(0, projectId);
			query.setParameter(1, resourceCode);
			query.setParameter(2,  columnName);
			return query.getSingleResult();
		}


		@Override
		@SuppressWarnings("unchecked")
		public List<FormulaPageDataEntity> getFormulaPageDataByProjectAndRange(ProjectEntity project, int start, int end) {
			
			int projectId = project.getId();
			Session session = sessionFactory.getCurrentSession();
			String queryString = 
					"select *, row_num\n" + 
					" from \n" + 
					" (select *, row_number() over (partition by column_name order by resource_code, column_name) as row_num from FormulaPageData) as a \n" + 
					" where project_id=? having row_num between ? and ?;";
			Query<FormulaPageDataEntity> query = session.createSQLQuery(queryString);
			query.setParameter(0, projectId);
			query.setParameter(1, start);
			query.setParameter(2, end);
			return query.getResultList();
		}
		
		// insertion 
		
		@Override
		public void insertFormulaPageData(ProjectEntity project, String resourceCode, String columnName, String value, String type, int fromResource) {
			
			Session session = sessionFactory.getCurrentSession();
			FormulaPageDataEntity newFormulaPageData = new FormulaPageDataEntity(project, resourceCode, columnName, value, type, fromResource);
			session.saveOrUpdate(newFormulaPageData);
		}

		// update
		@Override
		public void updateFormulaPageData(FormulaPageDataEntity updatedFormulaPageData) {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(updatedFormulaPageData);
		}

		// deletion
		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageData(ProjectEntity project, String resourceCode, String column_name) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where projectId=? and resourceCode=? and columnName=?");
			query.setParameter(0, project.getId());
			query.setParameter(1, resourceCode);
			query.setParameter(2, column_name);
			return query.executeUpdate();
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageDataByProjectResourceId(ProjectEntity project, String resourceCode) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where projectId=? and resourceCode=? and columnName=?");
			query.setParameter(0, project.getId());
			query.setParameter(1, resourceCode);
			return query.executeUpdate();
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageDataByProject(ProjectEntity project) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where projectId=?");
			query.setParameter(0, project.getId());
			return query.executeUpdate();
		}

		@Override
		public void insertFormulaPageData(FormulaPageDataEntity newFormulaPageData) {
			
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(newFormulaPageData);
		}

}
