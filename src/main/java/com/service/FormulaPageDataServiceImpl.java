package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FormulaDao;
import com.dao.FormulaPageDataDao;
import com.entity.FormulaEntity;
import com.entity.FormulaPageDataEntity;
import com.entity.ProjectEntity;
import com.model.FormulaPageData;

/**
 * service for formula page data
 * @author yipeng
 *
 */
@Service
@Transactional
public class FormulaPageDataServiceImpl implements FormulaPageDataService{
	
	@Autowired
	private FormulaPageDataDao formulaPageDataDao; 
	
	@Autowired
	private FormulaDao formulaDao;

	@Override
	public FormulaPageData getFormulaPageDataByProject(ProjectEntity project) {
		
		List<FormulaPageDataEntity> formulaPageData = formulaPageDataDao.getFormulaPageDataByProject(project);
		List<FormulaEntity> formulas = formulaDao.getFormulaByProject(project);
		
		return new FormulaPageData(project, formulaPageData.toArray(new FormulaPageDataEntity[formulaPageData.size()]), formulas.toArray(new FormulaEntity[formulas.size()]));
	}

	@Override
	public void insertFormulaPageData(FormulaPageData newFormulaPage) {
		
		FormulaPageDataEntity[] formulaPageData = newFormulaPage.getFormulaPageData();
		FormulaEntity[] formulas = newFormulaPage.getFormulas();
		
		for(FormulaPageDataEntity data : formulaPageData) {
			formulaPageDataDao.insertFormulaPageData(data);
		}
		
		for(FormulaEntity formula : formulas) {
			formulaDao.insertFormula(formula);
		}
	}

	@Override
	public void updateFormulaPageData(FormulaPageData newFormulaPage) {
		
		FormulaPageDataEntity[] newFormulaPageData = newFormulaPage.getFormulaPageData();
		
		FormulaEntity[] newFormulas = newFormulaPage.getFormulas();
		
		for(FormulaPageDataEntity data : newFormulaPageData) {
			formulaPageDataDao.updateFormulaPageData(data);
		}
		
		for(FormulaEntity formula : newFormulas) {
			formulaDao.updateFormula(formula);
		}
	}

	@Override
	public int deleteFormulaPageDataByProject(ProjectEntity project) {
		
		return formulaPageDataDao.deleteFormulaPageDataByProject(project);
	}

	@Override
	public int deleteFormulaPageColumn(ProjectEntity project, String columnName) {
		
		return formulaPageDataDao.deleteFormulaPageDataByProjectColumnName(project, columnName);
	}

	@Override
	public int deleteFormulaPageRow(ProjectEntity project, String resourceCode) {

		return formulaPageDataDao.deleteFormulaPageDataByProjectResourceCode(project, resourceCode);
	}

}
