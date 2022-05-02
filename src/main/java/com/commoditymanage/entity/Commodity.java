package com.commoditymanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
@TableName("tb_commodity")
public class Commodity {

	/**
	 * 主键 商品id
	 */
	@ApiModelProperty(value = "主键 商品id")
	@TableId(value = "commodity_id",type = IdType.AUTO)
	private Integer commodityId;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	@TableField("commodity_name")
	private String commodityName;

	/**
	 * 商品类型
	 */
	@ApiModelProperty(value = "商品类型")
	@TableField("commodity_type")
	private String commodityType;

	/**
	 * 商品编号
	 */
	@ApiModelProperty(value = "商品编号")
	@TableField("commodity_no")
	private String commodityNo;

	/**
	 * 商品介绍
	 */
	@ApiModelProperty(value = "商品介绍")
	@TableField("commodity_desc")
	private String commodityDesc;

	/**
	 * 价格
	 */
	@ApiModelProperty(value = "价格")
	@TableField("price")
	private java.math.BigDecimal price;

	/**
	 * 促销价(可为空)
	 */
	@ApiModelProperty(value = "促销价(可为空)")
	@TableField("discount_price")
	private java.math.BigDecimal discountPrice;

	/**
	 * 库存
	 */
	@ApiModelProperty(value = "库存")
	@TableField("stock")
	private Integer stock;

	/**
	 * 状态：0 未上架 1 上架
	 */
	@ApiModelProperty(value = "状态：0 未上架 1 上架")
	@TableField("status")
	private Integer status;

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
	private String gmtCreate;

	/**
	 * 最后修改时间
	 */
	@ApiModelProperty(value = "最后修改时间")
	@TableField("gmt_modify")
	private String gmtModify;


}
