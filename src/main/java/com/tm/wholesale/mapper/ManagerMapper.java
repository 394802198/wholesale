package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Manager;
import com.tm.wholesale.model.Page;

public interface ManagerMapper {

	/* SELECT AREA */

	List<Manager> selectManagers(Manager m);
	List<Manager> selectManagersByPage(Page<Manager> page);
	int selectManagersSum(Page<Manager> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertManager(Manager m);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateManager(Manager m);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteManagerById(int id);

	/* // END DELETE AREA */

}
