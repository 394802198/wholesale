package com.tm.wholesale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ComboMapper;
import com.tm.wholesale.mapper.ComboWholesalerMapper;
import com.tm.wholesale.mapper.MaterialCategoryMapper;
import com.tm.wholesale.mapper.MaterialGroupMapper;
import com.tm.wholesale.mapper.MaterialMapper;
import com.tm.wholesale.mapper.MaterialTypeMapper;
import com.tm.wholesale.mapper.MaterialWholesalerMapper;
import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Page;

@Service
public class ProductService {
	
	private MaterialMapper materialMapper;
	private MaterialGroupMapper materialGroupMapper;
	private MaterialTypeMapper materialTypeMapper;
	private MaterialCategoryMapper materialCategoryMapper;
	private MaterialWholesalerMapper materialWholesalerMapper;
	private ComboMapper comboMapper;
	private ComboWholesalerMapper comboWholesalerMapper;
	
	@Autowired
	public ProductService(MaterialMapper materialMapper,
			MaterialGroupMapper materialGroupMapper,
			MaterialTypeMapper materialTypeMapper,
			MaterialCategoryMapper materialCategoryMapper,
			MaterialWholesalerMapper materialWholesalerMapper,
			ComboMapper comboMapper,
			ComboWholesalerMapper comboWholesalerMapper) {
		this.materialMapper = materialMapper;
		this.materialGroupMapper = materialGroupMapper;
		this.materialTypeMapper = materialTypeMapper;
		this.materialWholesalerMapper = materialWholesalerMapper;
		this.comboMapper = comboMapper;
		this.comboWholesalerMapper = comboWholesalerMapper;
		this.materialCategoryMapper = materialCategoryMapper;
	}
	
	/**
	 * BEGIN Material
	 */
	
	public List<Material> queryMaterials(Material m){
		return this.materialMapper.selectMaterials(m);
	}
	
	public Page<Material> queryMaterialsByPage(Page<Material> page){
		page.setResults(this.materialMapper.selectMaterialsByPage(page));
		page.setTotalRecord(this.materialMapper.selectMaterialsSum(page));
		return page;
	}
	
	public String[] queryMaterialCategories(){
		return this.materialMapper.selectMaterialCategories();
	}
	
	@Transactional
	public void editMaterial(Material m){
		this.materialMapper.updateMaterial(m);
	}
	
	@Transactional
	public void removeMaterialById(int id){
		this.materialMapper.deleteMaterialById(id);
	}
	
	@Transactional
	public void removeMaterialsById(String[] ids){
		for (String id : ids) {
			this.materialMapper.deleteMaterialById(Integer.parseInt(id));
		}
	}
	
	@Transactional
	public void createMaterial(Material m){
		this.materialMapper.insertMaterial(m);
	}
	
	/**
	 * END Material
	 */
	
	/**
	 * BEGIN MaterialType
	 */
	
	public List<MaterialType> queryMaterialTypes(MaterialType mt){
		return this.materialTypeMapper.selectMaterialTypes(mt);
	}
	
	public Page<MaterialType> queryMaterialTypesByPage(Page<MaterialType> page){
		page.setResults(this.materialTypeMapper.selectMaterialTypesByPage(page));
		page.setTotalRecord(this.materialTypeMapper.selectMaterialTypesSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialType(MaterialType mt){
		this.materialTypeMapper.updateMaterialType(mt);
	}
	
	@Transactional
	public void removeMaterialTypeById(int id){
		this.materialTypeMapper.deleteMaterialTypeById(id);
	}
	
	@Transactional
	public void createMaterialType(MaterialType mt){
		this.materialTypeMapper.insertMaterialType(mt);
	}
	
	/**
	 * END MaterialType
	 */
	
	/**
	 * BEGIN MaterialGroup
	 */
	
	public List<MaterialGroup> queryMaterialGroups(MaterialGroup mg){
		return this.materialGroupMapper.selectMaterialGroups(mg);
	}
	
	public Page<MaterialGroup> queryMaterialGroupsByPage(Page<MaterialGroup> page){
		page.setResults(this.materialGroupMapper.selectMaterialGroupsByPage(page));
		page.setTotalRecord(this.materialGroupMapper.selectMaterialGroupsSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialGroup(MaterialGroup mg){
		this.materialGroupMapper.updateMaterialGroup(mg);
	}
	
	@Transactional
	public void removeMaterialGroupById(int id){
		this.materialGroupMapper.deleteMaterialGroupById(id);
	}
	
	@Transactional
	public void createMaterialGroup(MaterialGroup mg){
		this.materialGroupMapper.insertMaterialGroup(mg);
	}
	
	/**
	 * END MaterialGroup
	 */
	
	/**
	 * BEGIN MaterialCategory
	 */
	
	public List<MaterialCategory> queryMaterialCategorys(MaterialCategory mg){
		return this.materialCategoryMapper.selectMaterialCategorys(mg);
	}
	
	public Page<MaterialCategory> queryMaterialCategorysByPage(Page<MaterialCategory> page){
		page.setResults(this.materialCategoryMapper.selectMaterialCategorysByPage(page));
		page.setTotalRecord(this.materialCategoryMapper.selectMaterialCategorysSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialCategory(MaterialCategory mg){
		this.materialCategoryMapper.updateMaterialCategory(mg);
	}
	
	@Transactional
	public void removeMaterialCategoryById(int id){
		this.materialCategoryMapper.deleteMaterialCategoryById(id);
	}
	
	@Transactional
	public void createMaterialCategory(MaterialCategory mg){
		this.materialCategoryMapper.insertMaterialCategory(mg);
	}
	
	/**
	 * END MaterialCategory
	 */
	
	/**
	 * BEGIN MaterialWholesaler
	 */
	
	public List<MaterialWholesaler> queryMaterialWholesalers(MaterialWholesaler mw){
		return this.materialWholesalerMapper.selectMaterialWholesalers(mw);
	}
	
	public Page<MaterialWholesaler> queryMaterialWholesalersByPage(Page<MaterialWholesaler> page){
		page.setResults(this.materialWholesalerMapper.selectMaterialWholesalersByPage(page));
		page.setTotalRecord(this.materialWholesalerMapper.selectMaterialWholesalersSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialWholesaler(MaterialWholesaler mw){
		this.materialWholesalerMapper.updateMaterialWholesaler(mw);
	}
	
	@Transactional
	public void removeMaterialWholesalerById(int id){
		this.materialWholesalerMapper.deleteMaterialWholesalerById(id);
	}
	
	@Transactional
	public void removeMaterialWholesalerByWholesalerId(int id){
		this.materialWholesalerMapper.deleteMaterialWholesalerByCompanyId(id);
	}
	
	@Transactional
	public void createMaterialWholesaler(MaterialWholesaler mw){
		this.materialWholesalerMapper.insertMaterialWholesaler(mw);
	}
	
	/**
	 * END MaterialWholesaler
	 */
	
	/**
	 * BEGIN Combo
	 */
	
	public Combo queryCombo(Combo c){
		return this.comboMapper.selectCombos(c)!=null&&this.comboMapper.selectCombos(c).size()>0 ?  this.comboMapper.selectCombos(c).get(0) : null;
	}
	
	public List<Combo> queryCombos(Combo c){
		return this.comboMapper.selectCombos(c);
	}
	
	public Page<Combo> queryCombosByPage(Page<Combo> page){
		page.setResults(this.comboMapper.selectCombosByPage(page));
		page.setTotalRecord(this.comboMapper.selectCombosSum(page));
		return page;
	}
	
	@Transactional
	public void editCombo(Combo c){
		this.comboMapper.updateCombo(c);
	}
	
	@Transactional
	public void removeComboById(int id){
		this.comboMapper.deleteComboById(id);
	}
	
	@Transactional
	public void removeCombosById(String[] ids){
		for (String id : ids) {
			this.comboMapper.deleteComboById(Integer.parseInt(id));
		}
	}
	
	@Transactional
	public void createCombo(Combo c){
		this.comboMapper.insertCombo(c);
	}
	
	/**
	 * END Combo
	 */
	
	/**
	 * BEGIN ComboWholesaler
	 */
	
	public List<ComboWholesaler> queryComboWholesalers(ComboWholesaler cw){
		return this.comboWholesalerMapper.selectComboWholesalers(cw);
	}
	
	public Page<ComboWholesaler> queryComboWholesalersByPage(Page<ComboWholesaler> page){
		page.setResults(this.comboWholesalerMapper.selectComboWholesalersByPage(page));
		page.setTotalRecord(this.comboWholesalerMapper.selectComboWholesalersSum(page));
		return page;
	}
	
	@Transactional
	public void editComboWholesaler(ComboWholesaler cw){
		this.comboWholesalerMapper.updateComboWholesaler(cw);
	}
	
	@Transactional
	public void removeComboWholesalerById(int id){
		this.comboWholesalerMapper.deleteComboWholesalerById(id);
	}
	
	@Transactional
	public void removeComboWholesalerByWholesalerId(int id){
		this.comboWholesalerMapper.deleteComboWholesalerByCompanyId(id);
	}
	
	@Transactional
	public void createComboWholesaler(ComboWholesaler cw){
		this.comboWholesalerMapper.insertComboWholesaler(cw);
	}
	/**
	 * END ComboWholesaler
	 */

}
