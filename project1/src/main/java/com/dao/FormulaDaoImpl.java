package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.FormulaEntity;
import com.entity.ProjectEntity;

@Repository
public class FormulaDaoImpl implements FormulaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FormulaEntity> getFormulaByProject(ProjectEntity project) {
		
		int projectId = project.getId();
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from formula where project_id=?", FormulaEntity.class)
			.setParameter(0, projectId)
			.getResultList();
	}

	@Override
	public void insertFormula(ProjectEntity project, String formulaName, String formula) {
		
		Session session = sessionFactory.getCurrentSession();
		FormulaEntity newFormula = new FormulaEntity(project, formulaName, formula);
		session.saveOrUpdate(newFormula);
	}

	@Override
	public int deleteFormula(ProjectEntity project, String formulaName) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from formula where project_id=? and formula_name=?");
		query.setParameter(0, project.getId());
		query.setParameter(1, formulaName);
		return query.executeUpdate();
	}

	@Override
	public int deleteAllFormulas() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from formula");
		return query.executeUpdate();
	}

}
