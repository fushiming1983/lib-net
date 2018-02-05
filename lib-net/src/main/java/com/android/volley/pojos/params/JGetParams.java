package com.android.volley.pojos.params;

/**
 * @author 13120678
 *
 * GET请求的JSON解析参数
 */
public abstract class JGetParams extends JParams{

	@Override
	public int getDoType() {
		return IParams.DO_GET;
	}
}
