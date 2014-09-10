package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.Page;

public interface ComboMapper {

/**
 * mapping combo, combo DAO component
 * 
 * @author CyberPark
 * 
  */

	/* SELECT AREA */

	List<Combo> selectCombos(Combo c);
	List<Combo> selectCombosByPage(Page<Combo> page);
	int selectCombosSum(Page<Combo> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertCombo(Combo c);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateCombo(Combo c);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteComboById(int id);
	void deleteComboByMaterialId(int id);

	/* // END DELETE AREA */

}
