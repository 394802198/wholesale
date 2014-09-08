package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.Page;

public interface MaterialMapper {

/**
 * mapping material, material DAO component
 * 
 * @author CyberPark
 * 
  */

	/* SELECT AREA */

	List<Material> selectMaterials(Material m);
	List<Material> selectMaterialsByPage(Page<Material> page);
	int selectMaterialsSum(Page<Material> page);
	String[] selectMaterialCategories();

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterial(Material m);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterial(Material m);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialById(int id);

	/* // END DELETE AREA */

}
