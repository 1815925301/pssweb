package com.sinosoft.business.uilt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.PssShopCar;

public class ShopCarUilt {

	public PssShopCar getPssShopCar(PssProduct product){
		
		PssShopCar shop=new PssShopCar();
		//shop.setOrderid();
		shop.setSceneid(product.getSceneid());
		shop.setOrdertype(2);
		shop.setProductlevel(product.getProductlevel());
		shop.setProductid(product.getProductid());
		shop.setSatelliteid(product.getSatelliteid());
		shop.setSensorid(product.getSensorid());
		shop.setProducttype(product.getProducttype());
		
		shop.setDataupperleftlat(Double.parseDouble(product.getDataupperleftlat()));
		shop.setDataupperleftlong(Double.parseDouble(product.getDataupperleftlong()));
		shop.setDataupperrightlat(Double.parseDouble(product.getDataupperrightlat()));
		shop.setDataupperrightupperlong(Double.parseDouble(product.getDatalowerghtlong()));
		shop.setDatalowerleftlat(Double.parseDouble(product.getDatalowerleftlat()));
		shop.setDatalowerleftlong(Double.parseDouble(product.getDatalowerleftlong()));
		shop.setDatalowerrightlat(Double.parseDouble(product.getDatalowerrightlat()));
		shop.setDatalowerrightlong(Double.parseDouble(product.getDatalowerrightlong()));
		shop.setProductcate(1);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date = product.getAddshopcardate();
		Date orderdate = null;
		try {
			orderdate = sim.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shop.setOrderdate(orderdate);
		//shop.setArea();
		//shop.setPrice(Double.parseDouble("1000"));
      return shop;
	}
	public PssShopCar getPssOrderInfoToPssShopCar(PssOrderInfo order){
		PssShopCar shop=new PssShopCar();
		shop.setSceneid(order.getSceneid().toString());
		shop.setOrdertype(2);
		shop.setProductlevel(order.getProductlevel());
		shop.setProductid(order.getProductid());
		shop.setSatelliteid(order.getSatelliteid());
		shop.setSensorid(order.getSensorid());
		shop.setProducttype(order.getProducttype());
		//shop.setArea();
		shop.setDataupperleftlat(order.getDataupperleftlat());
		shop.setDataupperleftlong(order.getDataupperleftlong());
		shop.setDataupperrightlat(order.getDataupperrightlat());
		shop.setDataupperrightupperlong(order.getDataupperrightupperlong());
		shop.setDatalowerleftlat(order.getDatalowerleftlat());
		shop.setDatalowerleftlong(order.getDatalowerleftlong());
		shop.setDatalowerrightlat(order.getDatalowerrightlat());
		shop.setDatalowerrightlong(order.getDatalowerrightlong());
	
		
      return shop;
	}
}
