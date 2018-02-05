/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import com.suning.statistics.CloudytraceStatisticsProcessor;

import java.util.HashMap;
import java.util.Map;

public class NetworkStatistic {

    /**
     * 业务线枚举
     */
    public enum ProductLine {
        VIP("1001"),
        LIVE("1002"),
        NEWS("1003");

        private String mProduct;
        ProductLine(String product) {
            mProduct = product;
        }

        public String getValue() {
            return mProduct;
        }
    }

    /**
     * 对应业务线模块枚举
     */
    public enum Module {
        VIP_BUY("1001"),
        GOLD_BUY("1002");

        private String mModule;
        Module(String module) {
            mModule = module;
        }

        public String getValue() {
            return mModule;
        }
    }

    /**
     * 错误类型枚举
     */
    public enum ErrorCode {
        ERRCODE_1001("1001"),
        ERRCODE_1002("1002");

        private String mCode;
        ErrorCode(String code) {
            mCode = code;
        }

        public String getValue() {
            return mCode;
        }
    }

    /**
     * 云迹上传自定义日志
     * @param productLine
     * @param module
     * @param code
     * @param errormsg
     */
    public static void setCustomData(ProductLine productLine, Module module, ErrorCode code, String errormsg) {

        Map<String, String> map = new HashMap<>();
        map.put("productline", productLine.getValue());
        map.put("module", module.getValue());
        map.put("errorcode", code.getValue());

        if (errormsg != null && errormsg.length() > 100) {
            errormsg = errormsg.substring(0, 100);
        }
        map.put("errormsg", errormsg);

        CloudytraceStatisticsProcessor.setCustomData("info", "ppsports_business_statistics", map, true, true);
    }
}
