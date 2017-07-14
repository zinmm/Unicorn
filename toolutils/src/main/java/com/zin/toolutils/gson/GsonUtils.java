/**
 * Copyright (c) 2015, Spencer , ChinaSunHZ (www.spencer-dev.com).
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
package com.zin.toolutils.gson;

import com.google.gson.Gson;

import java.util.List;

/**
 * Gson Utils
 * Created by zinmm (www.zinmm-dev.com) on 15/2/3.
 */
public class GsonUtils {

    private GsonUtils() {

    }

    /**
     * object converter String
     *
     * @param t   t
     * @param <T> t
     * @return json
     */
    public static <T> String objectToJson(T t) {
        if (t == null) {
            throw new IllegalArgumentException("t can not null");
        }
        Gson gson = new Gson();
        return gson.toJson(t);
    }


    /**
     * string converter object
     *
     * @param json   json
     * @param clazzT converter to class
     * @param <T>    t
     * @return object
     */
    public static <T> T jsonToObject(String json, Class<T> clazzT) {
        if (json != null && json.equals("") || clazzT == null) {
            throw new IllegalArgumentException("json can not null");
        }
        Gson gson = new Gson();
        return gson.fromJson(json, clazzT);
    }

    /**
     * json converter list
     *
     * @param json   json
     * @param classT List of generic type needs such as: List T, classT parameters in place, fill T.class
     * @param <T>    t
     * @return List T t
     */
    public static <T> List<T> jsonToObjectList(String json, Class<T> classT) {
        if (json != null && json.equals("") || classT == null) {
            throw new IllegalArgumentException("json can not null");
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new ListOfSomething<T>(classT));
    }
}