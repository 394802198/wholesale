package com.tm.wholesale.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.OrderDetail;

public class TestAll {
	
	public static void main(String[] args){
		
//		OrderingFormPDFCreator ofPDFCreator = new OrderingFormPDFCreator();
		
		Order co = new Order();
		OrderDetail cod = new OrderDetail();
		List<OrderDetail> cods = new ArrayList<OrderDetail>();
		
		// CUSTOMER type
		co.setCustomer_type("business");
		// ORDER Broadband Type
		// Necessary if broadband type is transition
		co.setBroadband_type("transition");
		co.setTransition_provider_name("Telecom");
		co.setTransition_account_holder_name("David Li");
		co.setTransition_account_number("1234 4321 1234 4321");
		co.setTransition_porting_number("9876 6789 9876 6789");
		
		// set customer
		co.setTitle("Mr");
		co.setFirst_name("Dong");
		co.setLast_name("Chen");
		co.setEmail("davidli@gmail.com");
		co.setMobile("021 1234567");
		co.setPhone("021 1234567");
		co.setAddress("7 Skeates Ave, Mt roskill, Auckland");
		
		// set org
		co.setCompany_name("CyberPark");
		co.setTrade_name("NZ Limited");

		// set order detail
		// SET PLAN DETAIL
		cod.setName("ADSL Naked 150 GB Plan");
		cod.setType("plan-term");
		cod.setPrice(89.0d);
		cod.setPrice_enduser(100d);
		cod.setUnit(3);
		cods.add(cod);
		
		// SET ADD ON DETAIL
		cod = new OrderDetail();
		cod.setName("Broadband New Connection");
		cod.setType("new-connection");
		cod.setPrice(99.0d);
		cod.setPrice_enduser(100d);
		cod.setUnit(1);
		cods.add(cod);
		
		cod = new OrderDetail();
		cod.setName("TP - LINK 150Mbps Wireless N ADSL2+ Modem Router");
		cod.setType("hardware-router");
		cod.setPrice(49.0d);
		cod.setPrice_enduser(100d);
		cod.setUnit(2);
		cods.add(cod);
		
		// SET DISCOUNT ON DETAIL
		cod = new OrderDetail();
		cod.setName("Plan Discount");
		cod.setDesc("3% off the total price of plan");
		cod.setType("discount");
		cod.setPrice(16.008d);
		cod.setPrice_enduser(60d);
		cod.setUnit(1);
		cods.add(cod);
		
		// set order
		co.setId(7000000);
		co.setCreate_date(new Date());
		co.setOds(cods);
		
		// call OrderPDFCreator
		OrderingWholesalerPDFCreator oWholesalerPDFCreator = new OrderingWholesalerPDFCreator();
		OrderingCustomerPDFCreator oCustomerPDFCreator = new OrderingCustomerPDFCreator();
		oWholesalerPDFCreator.setOrder(co);
		oCustomerPDFCreator.setOrder(co);
		
		// create order PDF
		try {
			System.out.println(oWholesalerPDFCreator.create());
			System.out.println(oCustomerPDFCreator.create());
		} catch (DocumentException | IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
