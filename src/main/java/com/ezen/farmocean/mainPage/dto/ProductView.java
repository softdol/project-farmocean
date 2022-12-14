package com.ezen.farmocean.mainPage.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ProductView {	
	
	Integer prod_idx;
	String member_id;
	String prod_name;
	Integer cate_idx;
	String prod_info;
	String prod_price;
	String prod_sell;
	Timestamp prod_sell_deadline;//java.sql.Timestamp
	Integer prod_heartnum;
	Integer prod_delete;
	Integer prod_stock;
	Timestamp prod_written_date;
	String img_url;

	// member
	String member_name;
	String member_nickName;
	String member_email;
	String member_image;

}

