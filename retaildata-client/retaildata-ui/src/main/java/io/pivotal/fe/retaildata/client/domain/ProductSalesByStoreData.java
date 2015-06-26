package io.pivotal.fe.retaildata.client.domain;

import javax.persistence.*;
import java.io.Serializable;
 
@Entity
@Table(name = "product_sales_by_store", schema="retail_demo")
public class ProductSalesByStoreData implements Serializable{
	/**
	create view retail_demo.product_sales_by_store
	as
	select product_name, store_id, sum(item_quantity::integer) total_sold_quantity
	, row_number() over () as rownum
	from retail_demo.order_lineitems
	group by 1,2
	limit 50
	;	 
	*/ 
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rownum;
	
	@Column(name="product_name")
	private String productName;

	@Column(name="store_id")
	private int storeId;

	@Column(name="total_sold_quantity")
	private int totalSoldQuantity;

	public ProductSalesByStoreData() {
		super();
	}

	public Long getRownum() {
		return rownum;
	}

	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getTotalSoldQuantity() {
		return totalSoldQuantity;
	}

	public void setTotalSoldQuantity(int totalSoldQuantity) {
		this.totalSoldQuantity = totalSoldQuantity;
	}

}
