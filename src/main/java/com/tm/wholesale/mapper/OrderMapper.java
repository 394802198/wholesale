package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.Page;

public interface OrderMapper {

	/* SELECT AREA */

	List<Order> selectOrders(Order o);
	List<Order> selectOrdersByPage(Page<Order> page);
	int selectOrdersSum(Page<Order> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertOrder(Order o);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateOrder(Order o);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteOrderById(int id);

	/* // END DELETE AREA */

}
