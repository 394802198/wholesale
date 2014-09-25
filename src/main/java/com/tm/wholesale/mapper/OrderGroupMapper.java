package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.OrderGroup;
import com.tm.wholesale.model.Page;

public interface OrderGroupMapper {

/**
 * mapping order_group, orderGroup DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<OrderGroup> selectOrderGroups(OrderGroup og);
	List<OrderGroup> selectOrderGroupsByPage(Page<OrderGroup> page);
	int selectOrderGroupsSum(Page<OrderGroup> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertOrderGroup(OrderGroup og);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateOrderGroup(OrderGroup og);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteOrderGroupById(int id);

	/* // END DELETE AREA */

}
