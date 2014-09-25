package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.WholesalerOrderTransaction;
import com.tm.wholesale.model.Page;

public interface WholesalerOrderTransactionMapper {

/**
 * mapping wholesaler_order_transaction, wholesalerOrderTransaction DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<WholesalerOrderTransaction> selectWholesalerOrderTransactions(WholesalerOrderTransaction wot);
	List<WholesalerOrderTransaction> selectWholesalerOrderTransactionsByPage(Page<WholesalerOrderTransaction> page);
	int selectWholesalerOrderTransactionsSum(Page<WholesalerOrderTransaction> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertWholesalerOrderTransaction(WholesalerOrderTransaction wot);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateWholesalerOrderTransaction(WholesalerOrderTransaction wot);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteWholesalerOrderTransactionById(int id);

	/* // END DELETE AREA */

}
