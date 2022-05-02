package com.commoditymanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommodityCar {

	/**
	 * 主键 序号
	 */
	@ApiModelProperty(value = "主键 序号")
	@TableId(value = "car_id",type = IdType.AUTO)
	private Integer carId;

	/**
	 * 购物车编码
	 */
	@ApiModelProperty(value = "购物车编码")
	@TableField("car_no")
	private String carNo;

	/**
	 * 所属用户Id
	 */
	@ApiModelProperty(value = "所属用户Id")
	@TableField("user_id")
	private Integer userId;

	/**
	 * 商品Id
	 */
	@ApiModelProperty(value = "商品Id")
	@TableField("commodity_id")
	private Integer commodityId;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	@TableField("num")
	private Integer num;

	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价")
	@TableField("unit_price")
	private java.math.BigDecimal unitPrice;

	/**
	 * 总价
	 */
	@ApiModelProperty(value = "总价")
	@TableField("total_price")
	private java.math.BigDecimal totalPrice;

	/**
	 * 是否删除：0 未删除 1 已删除
	 */
	@ApiModelProperty(value = "是否删除：0 未删除 1 已删除")
	@TableField("is_deleted")
	private Integer isDeleted;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField("gmt_create")
	private java.util.Date gmtCreate;

	/**
	 * 最后修改时间
	 */
	@ApiModelProperty(value = "最后修改时间")
	@TableField("gmt_modify")
	private java.util.Date gmtModify;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	private String commodityName;

}
