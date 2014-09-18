package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.Page;

public interface MaterialCategoryMapper {

/**
 * mapping material_category, materialCategory DAO component
 * 
 * @author CyberPark
 * 
  */

	/* SELECT AREA */

	List<MaterialCategory> selectMaterialCategories(MaterialCategory mc);
	List<MaterialCategory> selectMaterialCategoriesByPage(Page<MaterialCategory> page);
	int selectMaterialCategoriesSum(Page<MaterialCategory> page);
	List<MaterialCategory> selectMaterialCategoriesUpperLowerName();

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterialCategory(MaterialCategory mc);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterialCategory(MaterialCategory mc);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialCategoryById(int id);

	/* // END DELETE AREA */

}
