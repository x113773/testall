package com.ansel.testall.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "400Error")
public class Error400 {
	/**
	 * 错误反馈编码。 01 Missing parameter 缺失参数根据错误描述检查参数 02 Invalid parameter
	 * 非法参数根据错误描述检查参数 03 Token Expired token失效重新调用获取token接口获取新token 04 Timestamp
	 * timeout 时间戳与CM-OSP平台时间偏差 大于6分钟 检查服务时间是否有漂移 05 Subsystem Timeout
	 * 子系统超时短暂间隔（10s,30s,60s）后重试即可 06 System internal error
	 * 系统内部错误一般由非法输入参数引起，请对输入参数进行检查 07 App Call Exceeds Limited Frequency
	 * 调用频率超过限制CM-OSP平台会根据监控的实际情况做相应调整 08 Insufficient App Permissions
	 * 无该接口访问权限　检查是否有权访问该接口 09 Authorize reject 用户未授权访问调用获取authCode接口获取authCode
	 * 10 Authorize expired authCode失效调用获取authCode接口获取authCode 11 The interface
	 * is not running 接口未上线可联系CM-OSP平台了解详情 12 Unauthorized access to the local
	 * service 未受权访问该服务方可联系CM-OSP平台了解详情 13 System maintenance and upgrade
	 * 系统维护升级可联系CM-OSP平台获取实时进度 14 Business can not handle 业务无法处理
	 * 例如查询用户套餐信息，但该用户无套餐信息，导致无查 询结果，无需处理 ,
	 */
	@ApiModelProperty(value = "错误反馈编码。 01 Missing parameter 缺失参数根据错误描述检查参数 02 Invalid parameter 非法参数根据错误描述检查参数 03 Token Expired token失效重新调用获取token接口获取新token 04 Timestamp timeout 时间戳与CM-OSP平台时间偏差 大于6分钟 检查服务时间是否有漂移 05 Subsystem Timeout 子系统超时短暂间隔（10s,30s,60s）后重试即可 06 System internal error 系统内部错误一般由非法输入参数引起，请对输入参数进行检查 07 App Call Exceeds Limited Frequency 调用频率超过限制CM-OSP平台会根据监控的实际情况做相应调整 08 Insufficient App Permissions 无该接口访问权限　检查是否有权访问该接口 09 Authorize reject 用户未授权访问调用获取authCode接口获取authCode 10 Authorize expired authCode失效调用获取authCode接口获取authCode 11 The interface is not running 接口未上线可联系CM-OSP平台了解详情 12 Unauthorized access to the local service 未受权访问该服务方可联系CM-OSP平台了解详情 13 System maintenance and upgrade 系统维护升级可联系CM-OSP平台获取实时进度 14 Business can not handle 业务无法处理 例如查询用户套餐信息，但该用户无套餐信息，导致无查 询结果，无需处理 ,")
	private String respCode;

	/**
	 * 错误反馈描述
	 */
	@ApiModelProperty(value = "错误反馈描述")
	private String respDesc;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

}
