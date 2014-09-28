package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.InvoiceDetail;
import com.tm.wholesale.model.Page;

public interface InvoiceDetailMapper {

/**
 * mapping invoice_detail, invoiceDetail DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<InvoiceDetail> selectInvoiceDetails(InvoiceDetail id);
	List<InvoiceDetail> selectInvoiceDetailsByPage(Page<InvoiceDetail> page);
	int selectInvoiceDetailsSum(Page<InvoiceDetail> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertInvoiceDetail(InvoiceDetail id);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateInvoiceDetail(InvoiceDetail id);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteInvoiceDetailById(int id);

	/* // END DELETE AREA */

}
