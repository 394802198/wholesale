package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Page;

public interface MaterialWholesalerMapper {

/**
 * mapping material_wholesaler, materialWholesaler DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<MaterialWholesaler> selectMaterialWholesalers(MaterialWholesaler mw);
	List<MaterialWholesaler> selectMaterialWholesalersByPage(Page<MaterialWholesaler> page);
	int selectMaterialWholesalersSum(Page<MaterialWholesaler> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterialWholesaler(MaterialWholesaler mw);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterialWholesaler(MaterialWholesaler mw);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialWholesalerById(int id);
	void deleteMaterialWholesalerByCompanyId(int id);
	void deleteMaterialWholesalerByMaterialId(int id);

	/* // END DELETE AREA */

}
