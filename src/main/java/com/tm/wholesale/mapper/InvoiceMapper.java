package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.Invoice;
import com.tm.wholesale.model.Page;

public interface InvoiceMapper {

/**
 * mapping invoice, invoice DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<Invoice> selectInvoices(Invoice i);
	List<Invoice> selectInvoicesByPage(Page<Invoice> page);
	int selectInvoicesSum(Page<Invoice> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertInvoice(Invoice i);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateInvoice(Invoice i);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteInvoiceById(int id);

	/* // END DELETE AREA */

}
