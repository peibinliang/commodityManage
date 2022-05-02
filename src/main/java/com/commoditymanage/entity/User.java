package com.commoditymanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class User extends Model<User> {

	/**
	 * 主键 用户Id
	 */
	@ApiModelProperty(value = "主键 用户Id")
	@TableId(value = "user_id",type = IdType.AUTO)
	private Integer userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	@TableField("user_name")
	private String userName;

	/**
	 * 用户昵称
	 */
	@ApiModelProperty(value = "用户昵称")
	@TableField("nick_name")
	private String nickName;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	@TableField("password")
	private String password;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	@TableField("phone_number")
	private String phoneNumber;

	/**
	 * 用户角色 ：1 管理员 2 普通用户
	 */
	@ApiModelProperty(value = "用户角色 ：1 管理员 2 普通用户")
	@TableField("user_role")
	private Integer userRole;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	@TableField("note")
	private String note;

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
