/*
 * Copyright (C) 2016 AdvancingPainters (https://github.com/AdvancingPainters).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zin.unicorn.util;

import android.text.TextUtils;

/**
 * access token utils
 * Created by zinmm on 17/3/20.
 */
public class AccessTokenUtils {

    private static String sAccessToken;

    private AccessTokenUtils() {

    }

    public static String getAccessToken() {
        if (TextUtils.isEmpty(sAccessToken)) {
            sAccessToken = SharePreferencesUtils.getInstance().getString(Constants.GitHubLogin.ACCESS_TOKEN).get();
        }
        return sAccessToken;
    }

    public static synchronized void initAccessToken(String accessToken) {
        SharePreferencesUtils.getInstance().getString(Constants.GitHubLogin.ACCESS_TOKEN).set(String.format("token %s", accessToken));
    }
}
