package com.android.volley.pojos.params;

/**
 * @author 13120678
 *
 * POST的JSON解析参数
 */
public abstract class JPostParams extends JParams{

	@Override
	public int getDoType() {
		return IParams.DO_POST;
	}

	@Override
	public boolean isJson() {
		return true;
	}

}
