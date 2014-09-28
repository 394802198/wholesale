package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.CustomerInvoice;
import com.tm.wholesale.model.Page;

public interface CustomerInvoiceMapper {

/**
 * mapping customer_invoice, customerInvoice DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<CustomerInvoice> selectCustomerInvoices(CustomerInvoice ci);
	List<CustomerInvoice> selectCustomerInvoicesByPage(Page<CustomerInvoice> page);
	int selectCustomerInvoicesSum(Page<CustomerInvoice> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertCustomerInvoice(CustomerInvoice ci);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateCustomerInvoice(CustomerInvoice ci);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteCustomerInvoiceById(int id);

	/* // END DELETE AREA */

}
