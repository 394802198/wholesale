package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.Page;

public interface MaterialGroupMapper {

	/* SELECT AREA */

	List<MaterialGroup> selectMaterialGroups(MaterialGroup mg);
	List<MaterialGroup> selectMaterialGroupsByPage(Page<MaterialGroup> page);
	int selectMaterialGroupsSum(Page<MaterialGroup> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertMaterialGroup(MaterialGroup mg);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateMaterialGroup(MaterialGroup mg);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteMaterialGroupById(int id);

	/* // END DELETE AREA */

}
