package com.commoditymanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayOrderRelation {

	/**
	 * 主键 序号
	 */
	@ApiModelProperty(value = "主键 序号")
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	@TableField("order_id")
	private Integer orderId;

	/**
	 * 购物车号
	 */
	@ApiModelProperty(value = "购物车号")
	@TableField("car_id")
	private Integer carId;


}
