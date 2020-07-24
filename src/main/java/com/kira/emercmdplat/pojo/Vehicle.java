package com.kira.emercmdplat.pojo;

import lombok.Data;

@Data
public class Vehicle extends BaseObject {
	
	private Integer id;
	
	private String number;
	
	private Integer mid;
	
	private String pic;
	
	private String cellNum;
	
	private Double lng;
	
	private Double lat;
	
	private String commonIcon = host + "/img/vehicle-common.png";
	
	private String activeIcon = host + "/img/vehicle-active.png";

}
