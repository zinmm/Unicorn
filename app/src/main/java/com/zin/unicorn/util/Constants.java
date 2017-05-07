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

import com.zin.unicorn.BuildConfig;

/**
 * Constants
 * Created by Spencer on 4/1/16.
 */
public class Constants {

    public static class Extras {
        public static final String FRAGMENT_TITLE = "FRAGMENT_TITLE";
        public static final String FRAGMENT_CLASS_NAME = "FRAGMENT_CLASS_NAME";
        public static final String FRAGMENT_ARGUMENTS = "FRAGMENT_ARGUMENTS";
    }

    public static class GitHubLogin {
        public static final String BASE_URL = "https://api.github.com/";

        public static String LOGIN_TOKEN_URL(String code) {
            return String.format("%s&code=%s", LOGIN_TOKEN_URL, code);
        }

        private static final String LOGIN_TOKEN_URL = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s", BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_SECRET, BuildConfig.GITHUB_CALLBACK_URL);

        public static final String LOGIN_AUTH_URL = String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s", BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_CALLBACK_URL, GitHubLogin.SCOPE);
        public static final String SCOPE = "user public_repo repo notifications";

        public static final String HEADER_AUTHORIZATION = "Authorization";
        public static final String CODE = "code";
        public static final String ACCESS_TOKEN = "access_token";
    }

    public static class Common{
        public static final String ERROR_PAGE_URL = "file:///android_asset/web_page/error_page.html";
        public static final String SUCCESS_PAGE_URL = "file:///android_asset/web_page/success_page.html";
    }

    public static class User {
        public static final String USER_INFO = "USER_INFO";
    }
}
