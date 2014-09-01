package com.tm.wholesale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ComboDetailMapper;
import com.tm.wholesale.mapper.ComboMapper;
import com.tm.wholesale.mapper.ComboWholesalerMapper;
import com.tm.wholesale.mapper.MaterialCategoryMapper;
import com.tm.wholesale.mapper.MaterialGroupMapper;
import com.tm.wholesale.mapper.MaterialMapper;
import com.tm.wholesale.mapper.MaterialTypeMapper;
import com.tm.wholesale.mapper.MaterialWholesalerMapper;
import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.ComboDetail;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.MaterialWholesaler;

@Service
public class MaterialService {
	
	private MaterialMapper MaterialMapper;
	private MaterialGroupMapper MaterialGroupMapper;
	private MaterialTypeMapper MaterialTypeMapper;
	private MaterialCategoryMapper MaterialCategoryMapper;
	private MaterialWholesalerMapper MaterialWholesalerMapper;
	private WholesalerMapper WholesalerMapper;
	private ComboMapper ComboMapper;
	private ComboDetailMapper ComboDetailMapper;
	private ComboWholesalerMapper ComboWholesalerMapper;
	
	@Autowired
	public MaterialService(MaterialMapper MaterialMapper,
			MaterialGroupMapper MaterialGroupMapper,
			MaterialTypeMapper MaterialTypeMapper,
			MaterialCategoryMapper MaterialCategoryMapper,
			MaterialWholesalerMapper MaterialWholesalerMapper,
			WholesalerMapper WholesalerMapper,
			ComboMapper ComboMapper,
			ComboDetailMapper ComboDetailMapper,
			ComboWholesalerMapper ComboWholesalerMapper) {
		this.MaterialMapper = MaterialMapper;
		this.MaterialGroupMapper = MaterialGroupMapper;
		this.MaterialTypeMapper = MaterialTypeMapper;
		this.MaterialWholesalerMapper = MaterialWholesalerMapper;
		this.WholesalerMapper = WholesalerMapper;
		this.ComboMapper = ComboMapper;
		this.ComboDetailMapper = ComboDetailMapper;
		this.ComboWholesalerMapper = ComboWholesalerMapper;
		this.MaterialCategoryMapper = MaterialCategoryMapper;
	}
	
	/**
	 * BEGIN Material
	 */
	
	public List<Material> queryMaterials(Material m){
		return this.MaterialMapper.selectMaterials(m);
	}
	
	public Page<Material> queryMaterialsByPage(Page<Material> page){
		page.setResults(this.MaterialMapper.selectMaterialsByPage(page));
		page.setTotalRecord(this.MaterialMapper.selectMaterialsSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterial(Material m){
		this.MaterialMapper.updateMaterial(m);
	}
	
	@Transactional
	public void removeMaterialById(int id){
		this.MaterialMapper.deleteMaterialById(id);
	}
	
	@Transactional
	public void createMaterial(Material m){
		this.MaterialMapper.insertMaterial(m);
	}
	
	/**
	 * END Material
	 */
	
	/**
	 * BEGIN MaterialType
	 */
	
	public List<MaterialType> queryMaterialTypes(MaterialType mt){
		return this.MaterialTypeMapper.selectMaterialTypes(mt);
	}
	
	public Page<MaterialType> queryMaterialTypesByPage(Page<MaterialType> page){
		page.setResults(this.MaterialTypeMapper.selectMaterialTypesByPage(page));
		page.setTotalRecord(this.MaterialTypeMapper.selectMaterialTypesSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialType(MaterialType mt){
		this.MaterialTypeMapper.updateMaterialType(mt);
	}
	
	@Transactional
	public void removeMaterialTypeById(int id){
		this.MaterialTypeMapper.deleteMaterialTypeById(id);
	}
	
	@Transactional
	public void createMaterialType(MaterialType mt){
		this.MaterialTypeMapper.insertMaterialType(mt);
	}
	
	/**
	 * END MaterialType
	 */
	
	/**
	 * BEGIN MaterialGroup
	 */
	
	public List<MaterialGroup> queryMaterialGroups(MaterialGroup mg){
		return this.MaterialGroupMapper.selectMaterialGroups(mg);
	}
	
	public Page<MaterialGroup> queryMaterialGroupsByPage(Page<MaterialGroup> page){
		page.setResults(this.MaterialGroupMapper.selectMaterialGroupsByPage(page));
		page.setTotalRecord(this.MaterialGroupMapper.selectMaterialGroupsSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialGroup(MaterialGroup mg){
		this.MaterialGroupMapper.updateMaterialGroup(mg);
	}
	
	@Transactional
	public void removeMaterialGroupById(int id){
		this.MaterialGroupMapper.deleteMaterialGroupById(id);
	}
	
	@Transactional
	public void createMaterialGroup(MaterialGroup mg){
		this.MaterialGroupMapper.insertMaterialGroup(mg);
	}
	
	/**
	 * END MaterialGroup
	 */
	
	/**
	 * BEGIN MaterialCategory
	 */
	
	public List<MaterialCategory> queryMaterialCategorys(MaterialCategory mg){
		return this.MaterialCategoryMapper.selectMaterialCategorys(mg);
	}
	
	public Page<MaterialCategory> queryMaterialCategorysByPage(Page<MaterialCategory> page){
		page.setResults(this.MaterialCategoryMapper.selectMaterialCategorysByPage(page));
		page.setTotalRecord(this.MaterialCategoryMapper.selectMaterialCategorysSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialCategory(MaterialCategory mg){
		this.MaterialCategoryMapper.updateMaterialCategory(mg);
	}
	
	@Transactional
	public void removeMaterialCategoryById(int id){
		this.MaterialCategoryMapper.deleteMaterialCategoryById(id);
	}
	
	@Transactional
	public void createMaterialCategory(MaterialCategory mg){
		this.MaterialCategoryMapper.insertMaterialCategory(mg);
	}
	
	/**
	 * END MaterialCategory
	 */
	
	/**
	 * BEGIN MaterialWholesaler
	 */
	
	public List<MaterialWholesaler> queryMaterialWholesalers(MaterialWholesaler mw){
		return this.MaterialWholesalerMapper.selectMaterialWholesalers(mw);
	}
	
	public Page<MaterialWholesaler> queryMaterialWholesalersByPage(Page<MaterialWholesaler> page){
		page.setResults(this.MaterialWholesalerMapper.selectMaterialWholesalersByPage(page));
		page.setTotalRecord(this.MaterialWholesalerMapper.selectMaterialWholesalersSum(page));
		return page;
	}
	
	@Transactional
	public void editMaterialWholesaler(MaterialWholesaler mw){
		this.MaterialWholesalerMapper.updateMaterialWholesaler(mw);
	}
	
	@Transactional
	public void removeMaterialWholesalerById(int id){
		this.MaterialWholesalerMapper.deleteMaterialWholesalerById(id);
	}
	
	@Transactional
	public void createMaterialWholesaler(MaterialWholesaler mw){
		this.MaterialWholesalerMapper.insertMaterialWholesaler(mw);
	}
	
	/**
	 * END MaterialWholesaler
	 */
	
	/**
	 * BEGIN Combo
	 */
	
	public List<Combo> queryCombos(Combo c){
		return this.ComboMapper.selectCombos(c);
	}
	
	public Page<Combo> queryCombosByPage(Page<Combo> page){
		page.setResults(this.ComboMapper.selectCombosByPage(page));
		page.setTotalRecord(this.ComboMapper.selectCombosSum(page));
		return page;
	}
	
	@Transactional
	public void editCombo(Combo c){
		this.ComboMapper.updateCombo(c);
	}
	
	@Transactional
	public void removeComboById(int id){
		this.ComboMapper.deleteComboById(id);
	}
	
	@Transactional
	public void createCombo(Combo c){
		this.ComboMapper.insertCombo(c);
	}
	
	/**
	 * END Combo
	 */
	
	/**
	 * BEGIN ComboDetail
	 */
	
	public List<ComboDetail> queryCombosDetail(ComboDetail cd){
		return this.ComboDetailMapper.selectComboDetails(cd);
	}
	
	public Page<ComboDetail> queryComboDetailsByPage(Page<ComboDetail> page){
		page.setResults(this.ComboDetailMapper.selectComboDetailsByPage(page));
		page.setTotalRecord(this.ComboDetailMapper.selectComboDetailsSum(page));
		return page;
	}
	
	@Transactional
	public void editComboDetail(ComboDetail cd){
		this.ComboDetailMapper.updateComboDetail(cd);
	}
	
	@Transactional
	public void removeComboDetailById(int id){
		this.ComboDetailMapper.deleteComboDetailById(id);
	}
	
	@Transactional
	public void createComboDetail(ComboDetail cd){
		this.ComboDetailMapper.insertComboDetail(cd);
	}
	
	/**
	 * END ComboDetail
	 */
	
	/**
	 * BEGIN ComboWholesaler
	 */
	
	public List<ComboWholesaler> queryComboWholesalers(ComboWholesaler cw){
		return this.ComboWholesalerMapper.selectComboWholesalers(cw);
	}
	
	public Page<ComboWholesaler> queryComboWholesalersByPage(Page<ComboWholesaler> page){
		page.setResults(this.ComboWholesalerMapper.selectComboWholesalersByPage(page));
		page.setTotalRecord(this.ComboWholesalerMapper.selectComboWholesalersSum(page));
		return page;
	}
	
	@Transactional
	public void editComboWholesaler(ComboWholesaler cw){
		this.ComboWholesalerMapper.updateComboWholesaler(cw);
	}
	
	@Transactional
	public void removeComboWholesalerById(int id){
		this.ComboWholesalerMapper.deleteComboWholesalerById(id);
	}
	
	@Transactional
	public void createComboWholesaler(ComboWholesaler cw){
		this.ComboWholesalerMapper.insertComboWholesaler(cw);
	}
	/**
	 * END ComboWholesaler
	 */

}
