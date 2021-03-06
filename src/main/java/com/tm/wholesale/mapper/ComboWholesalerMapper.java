package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.Page;

public interface ComboWholesalerMapper {

/**
 * mapping combo_wholesaler, comboWholesaler DAO component
 * 
 * @author CyberPark
 * 
  */

	/* SELECT AREA */

	List<ComboWholesaler> selectComboWholesalers(ComboWholesaler cw);
	List<ComboWholesaler> selectComboWholesalersByPage(Page<ComboWholesaler> page);
	int selectComboWholesalersSum(Page<ComboWholesaler> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertComboWholesaler(ComboWholesaler cw);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateComboWholesaler(ComboWholesaler cw);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteComboWholesalerById(int id);
	void deleteComboWholesalerByCompanyId(int id);
	void deleteComboWholesalerByMaterialId(int id);
	void deleteComboWholesalerByComboId(int id);
	
	/* // END DELETE AREA */

}
