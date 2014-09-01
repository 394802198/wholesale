package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.model.Page;

public interface WholesalerMapper {

	/* SELECT AREA */

	List<Wholesaler> selectWholesalers(Wholesaler wholesaler);
	List<Wholesaler> selectWholesalersByPage(Page<Wholesaler> page);
	int selectWholesalersSum(Page<Wholesaler> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertWholesaler(Wholesaler wholesaler);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateWholesaler(Wholesaler wholesaler);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteWholesalerById(int id);

	/* // END DELETE AREA */

}
