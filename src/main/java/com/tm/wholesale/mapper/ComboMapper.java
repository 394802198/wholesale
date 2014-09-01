package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.Page;

public interface ComboMapper {

	/* SELECT AREA */

	List<Combo> selectCombos(Combo combo);
	List<Combo> selectCombosByPage(Page<Combo> page);
	int selectCombosSum(Page<Combo> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertCombo(Combo combo);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateCombo(Combo combo);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteComboById(int id);

	/* // END DELETE AREA */

}
