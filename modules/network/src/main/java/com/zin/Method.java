//package com.zin;
//
//import android.support.annotation.StringDef;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//
//public class Method {
//
//    public static final String GET = "get";
//    public static final String POST = "post";
//
//    @DestroyState
//    private final String method;
//
//    public Method(@DestroyState String method) {
//        this.method = method;
//    }
//
//    @StringDef({GET, POST})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface DestroyState {
//
//    }
//}