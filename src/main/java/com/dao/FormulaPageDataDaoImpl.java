package com.dao;

import java.util.ArrayList;
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
			
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where project=:project order by resourceCode", FormulaPageDataEntity.class);
			query.setParameter("project", project);
			return query.getResultList();
		}

		@Override
		public List<FormulaPageDataEntity> getFormulaPageDataByProjectResourceCode(ProjectEntity project, String resourceCode) {
			
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where project=:project and resourceCode=:resourceCode order by columnName", FormulaPageDataEntity.class);
			query.setParameter("project", project);
			query.setParameter("resourceCode", resourceCode);
			return query.getResultList();
		}

		@Override
		public FormulaPageDataEntity getFormulaPageDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName) {
			
			Session session = sessionFactory.getCurrentSession();
			Query<FormulaPageDataEntity> query = session.createQuery("from FormulaPageDataEntity where project=:project and resourceCode=:resourceCode and columnName=:columnName", FormulaPageDataEntity.class);
			query.setParameter("project", project);
			query.setParameter("resourceCode", resourceCode);
			query.setParameter("columnName", columnName);
			return query.getSingleResult();
		}


		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<FormulaPageDataEntity> getFormulaPageDataByProjectAndRange(ProjectEntity project, int start, int end) {
			
			int projectId = project.getId();
			Session session = sessionFactory.getCurrentSession();
			String queryString = 
						"select project_id, resource_code, column_name, value, type, from_resource from(\n" + 
						"select * from \n" + 
						"	(select *, row_number() over (partition by column_name order by resource_code, column_name) as row_num \n" + 
						"    from data) as a  \n" + 
						"where project_id=:projectId\n" + 
						"having row_num between :start and :end) as b";
			Query query = session.createSQLQuery(queryString);
			query.setParameter("projectId", projectId);
			query.setParameter("start", start);
			query.setParameter("end", end);
			List<FormulaPageDataEntity> result = new ArrayList<>();
			List<Object[]> rawResult = query.getResultList();
			for (Object[] resultItem : rawResult) {
				FormulaPageDataEntity curDataEntity = new FormulaPageDataEntity(project, (String)resultItem[1], (String)resultItem[2], (String)resultItem[3], (String)resultItem[4], (Integer)resultItem[5]);
				result.add(curDataEntity);
			}
			return result;
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
			Query query = session.createQuery("delete from FormulaPageDataEntity where project=:project and resourceCode=:resourceCode and columnName=:columnName");
			query.setParameter("project", project);
			query.setParameter("resourceCode", resourceCode);
			query.setParameter("columnName", column_name);
			return query.executeUpdate();
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageDataByProjectResourceCode(ProjectEntity project, String resourceCode) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where project=:project and resourceCode=:resourceCode");
			query.setParameter("project", project);
			query.setParameter("resourceCode", resourceCode);
			return query.executeUpdate();
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageDataByProject(ProjectEntity project) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where project=:project");
			query.setParameter("project", project);
			return query.executeUpdate();
		}

		@Override
		public void insertFormulaPageData(FormulaPageDataEntity newFormulaPageData) {
			
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(newFormulaPageData);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int deleteFormulaPageDataByProjectColumnName(ProjectEntity project, String columnName) {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from FormulaPageDataEntity where project=:project and column_name=:columnName");
			query.setParameter("project", project);
			query.setParameter("columnName", columnName);
			return query.executeUpdate();
		}

}
