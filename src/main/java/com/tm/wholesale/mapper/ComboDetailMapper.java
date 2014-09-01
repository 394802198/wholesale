package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.ComboDetail;
import com.tm.wholesale.model.Page;

public interface ComboDetailMapper {

/**
 * mapping combo_detail, comboDetail DAO component
 * 
 * @author CyberPark
 * 
  */

	/* SELECT AREA */

	List<ComboDetail> selectComboDetails(ComboDetail cd);
	List<ComboDetail> selectComboDetailsByPage(Page<ComboDetail> page);
	int selectComboDetailsSum(Page<ComboDetail> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertComboDetail(ComboDetail cd);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateComboDetail(ComboDetail cd);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteComboDetailById(int id);

	/* // END DELETE AREA */

}
