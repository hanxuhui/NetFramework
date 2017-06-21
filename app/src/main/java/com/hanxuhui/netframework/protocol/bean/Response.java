package com.hanxuhui.netframework.protocol.bean;

import java.io.Serializable;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class Response<T> implements Serializable {

	public int returnCode;
	public String returnMsg;
	public T returnData;

}
