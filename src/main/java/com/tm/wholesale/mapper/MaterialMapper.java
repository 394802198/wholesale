package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.Page;

public interface MaterialMapper {

	/* SELECT AREA */

	List<Material> selectMaterials(Material material);
	List<Material> selectMaterialsByPage(Page<Material> page);
	int selectMaterialsSum(Page<Material> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterial(Material material);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterial(Material material);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialById(int id);

	/* // END DELETE AREA */

}
