package com.android.volley.pojos.params;

import com.android.volley.pojos.parser.DefaultJSONParser;
import com.android.volley.pojos.parser.IParser;

public abstract class JParams extends AbstractParams {

    @Override
    public IParser getParser() {
        return new DefaultJSONParser();
    }

}
