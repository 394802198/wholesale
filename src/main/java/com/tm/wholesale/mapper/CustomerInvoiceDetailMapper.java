package com.tm.wholesale.mapper;


import java.util.List;

import com.tm.wholesale.model.CustomerInvoiceDetail;
import com.tm.wholesale.model.Page;

public interface CustomerInvoiceDetailMapper {

/**
 * mapping customer_invoice_detail, customerInvoiceDetail DAO component
 * 
 * @author Total Mobile Solution
 * 
  */

	/* SELECT AREA */

	List<CustomerInvoiceDetail> selectCustomerInvoiceDetails(CustomerInvoiceDetail cid);
	List<CustomerInvoiceDetail> selectCustomerInvoiceDetailsByPage(Page<CustomerInvoiceDetail> page);
	int selectCustomerInvoiceDetailsSum(Page<CustomerInvoiceDetail> page);

	/* // END SELECT AREA */
	/* =================================================================================== */
	/* INSERT AREA */

	void insertCustomerInvoiceDetail(CustomerInvoiceDetail cid);

	/* // END INSERT AREA */
	/* =================================================================================== */
	/* UPDATE AREA */

	void updateCustomerInvoiceDetail(CustomerInvoiceDetail cid);

	/* // END UPDATE AREA */
	/* =================================================================================== */
	/* DELETE AREA */

	void deleteCustomerInvoiceDetailById(int id);

	/* // END DELETE AREA */

}
