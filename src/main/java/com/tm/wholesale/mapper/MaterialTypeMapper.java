package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.Page;

public interface MaterialTypeMapper {

	/* SELECT AREA */

	List<MaterialType> selectMaterialTypes(MaterialType mt);
	List<MaterialType> selectMaterialTypesByPage(Page<MaterialType> page);
	int selectMaterialTypesSum(Page<MaterialType> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterialType(MaterialType mt);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterialType(MaterialType mt);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialTypeById(int id);

	/* // END DELETE AREA */

}
