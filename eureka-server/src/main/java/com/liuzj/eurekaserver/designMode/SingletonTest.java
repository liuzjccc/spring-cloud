package com.liuzj.eurekaserver.designMode;

public class SingletonTest {
    public static void main(String[] args){

    }
}

/**
 * 懒汉式（非安全；在多线程情况下会产生多个实例，所以得加上锁：synchronized，加上了同步锁，这样降低了效率）
 */
class SingleTon1{
    private static SingleTon1 singleTon1 = null;

    public static synchronized SingleTon1 getInstence(){
        if(singleTon1 == null) {
            return new SingleTon1();
        }
        return singleTon1;
    }
}

/**
 * 饿汉式（安全；类加载的时候就创建了实例）
 */
class SingleTon2{
    private static SingleTon2 singleTon2 = new SingleTon2();

    public static synchronized SingleTon2 getInstence(){
        return singleTon2;
    }
}