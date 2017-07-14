package com.zin.toolutils;


import java.util.Random;

/**
 * Created by zhujinming on 2017/6/23.
 */
public class Test {

    @org.junit.Test
    public void addition_isCorrect() throws Exception {
        addSpace("13718762478");
        System.out.println(getRandomProbability());

        String s = "http: //106.38.181.152/dd.myjfaslkfnadsklnfkljdasnklfndaslknflkdasnflkdasnfkldsajlkfndaklsapp.com/16891/604CBE1CAAF54033884B9894107B2A69.apk?mkey=5954ffe2b05f039f&f=5893&c=0&fsname=com.baile.fish_1.1.6_6.apk&appid=1105956653&qz_gdt=3hlfiwj6aiahjqki4rkq&p=.apk";
        String a = "fsname=";
        String b = ".apk";
        int n1=s.indexOf(a);       //n1=0
        String str2 = s.substring(n1, n1 + a.length() + 50);
        int n3=str2.indexOf(a);
        int n4=str2.indexOf(b);
        System.out.println(str2.substring(n3 + a.length(), n4));
    }

    public void addSpace(String str) {
//        (str.length() / 3 == 0)

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a").append("b").append("c").append("");
        System.out.println(stringBuffer.toString());
    }

    public static int getRandomProbability() {
        Random r = new Random();
        int randNum = r.nextInt(2);
        return randNum;
    }
}
