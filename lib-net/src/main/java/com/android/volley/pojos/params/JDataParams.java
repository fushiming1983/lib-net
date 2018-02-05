package com.android.volley.pojos.params;

import com.android.volley.pojos.parser.DefaultDataParser;
import com.android.volley.pojos.parser.IParser;

/**
 * Created by 14074533 on 2016/9/1.
 */
public abstract class JDataParams extends AbstractParams {
    @Override
    public IParser getParser() {
        return new DefaultDataParser();
    }
}
