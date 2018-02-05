package com.android.volley.pojos.params;

import com.android.volley.pojos.parser.DefaultArrayParser;
import com.android.volley.pojos.parser.IParser;

/**
 * Created by gongchaobin on 16/8/20.
 */
public abstract class JListParam extends AbstractParams{

    @Override
    public IParser getParser() {
        return new DefaultArrayParser();
    }
}
