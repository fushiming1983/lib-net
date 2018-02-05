package com.android.volley.annotation;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class IgnoreExclusionStrategy implements ExclusionStrategy{

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		return arg0.getAnnotation(JsonIgnore.class) != null;
	}

}
