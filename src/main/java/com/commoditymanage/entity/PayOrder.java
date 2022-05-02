package com.commoditymanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayOrder {

	/**
	 * 主键 订单号
	 */
	@ApiModelProperty(value = "主键 订单号")
	@TableId(value = "order_id",type = IdType.AUTO)
	private Integer orderId;

	/**
	 * 所属用户Id
	 */
	@ApiModelProperty(value = "所属用户Id")
	@TableField("user_id")
	private Integer userId;

	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号")
	@TableField("order_no")
	private String orderNo;

	/**
	 * 订单价钱
	 */
	@ApiModelProperty(value = "订单价钱")
	@TableField("order_price")
	private java.math.BigDecimal orderPrice;

	/**
	 * 收货地址
	 */
	@ApiModelProperty(value = "收货地址")
	@TableField("address_id")
	private Integer addressId;

	/**
	 * 支付时间
	 */
	@ApiModelProperty(value = "支付时间")
	@TableField("pay_time")
	private String payTime;

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
	 * 所属用户名称
	 */
	@ApiModelProperty(value = "所属用户名称")
	private String userName;

	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量")
	private Integer num;

}
