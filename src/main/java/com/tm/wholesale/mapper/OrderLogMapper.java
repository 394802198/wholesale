package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.OrderLog;
import com.tm.wholesale.model.Page;

public interface OrderLogMapper {

	/* SELECT AREA */

	List<OrderLog> selectOrderLogs(OrderLog ol);
	List<OrderLog> selectOrderLogsByPage(Page<OrderLog> page);
	int selectOrderLogsSum(Page<OrderLog> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertOrderLog(OrderLog ol);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateOrderLog(OrderLog ol);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteOrderLogById(int id);

	/* // END DELETE AREA */

}
