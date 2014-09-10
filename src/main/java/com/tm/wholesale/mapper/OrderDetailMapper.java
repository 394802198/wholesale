package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.OrderDetail;
import com.tm.wholesale.model.Page;

public interface OrderDetailMapper {

	/* SELECT AREA */

	List<OrderDetail> selectOrderDetails(OrderDetail od);
	List<OrderDetail> selectOrderDetailsByPage(Page<OrderDetail> page);
	int selectOrderDetailsSum(Page<OrderDetail> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertOrderDetail(OrderDetail od);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateOrderDetail(OrderDetail od);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteOrderDetailById(int id);

	/* // END DELETE AREA */

}
