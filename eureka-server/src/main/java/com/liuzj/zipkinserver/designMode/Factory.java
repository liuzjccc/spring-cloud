package com.liuzj.zipkinserver.designMode;

/**
 * 抽象工厂模式（简单工厂模式 -> 工厂方法模式 -> 静态工厂模式 -> 抽象工厂模式），场景：凡是出现了大量的产品需要创建，并且具有共同的接口时
 */
public class Factory {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.produce("顺丰快递").send();
    }
}

/**
 * 基础类
 */
interface Transport{
    void send();
}

class ShunFeng implements Transport{

    @Override
    public void send() {
        System.out.println("顺丰快递");
    }
}

class YuanTong implements Transport{

    @Override
    public void send() {
        System.out.println("圆通快递");
    }
}


// 简单工厂模式start ---------------
class SimpleFactory{

    public Transport produce(String name){
        switch (name){
            case "顺丰快递":
                return new ShunFeng();
            case "圆通快递":
                return new YuanTong();
            default:
                return null;
        }
    }
}
// 简单工厂模式end ---------------由于简单工厂依赖于传递进去的字串，如果字串有误则无法创建对象，于是采用工厂方法模式


// 工厂方法模式start ------------
class MoreMethodFactory{

    public Transport produceShunFeng(){
        return new ShunFeng();
    }

    public Transport produceYuanTong(){
        return new YuanTong();
    }
}
// 工厂方法模式end ------------ 省去创建对象，将方法设置为静态的直接调用


// 静态工厂模式start ------------
class StaticFactory{

    public static Transport produceShunFeng(){
        return new ShunFeng();
    }

    public static Transport produceYuanTong(){
        return new YuanTong();
    }
}
// 静态工厂模式end ------------

// 以上的工厂方法模式缺点：创建对象依赖于工厂，如果想要拓展程序，必须对工厂类进行修改，这违背了开闭原则，于是抽象工厂模式出现

// 抽象工厂模式start ------------
/**
 * 抽象工厂
 */
interface MyFactory{
    Transport produce();
}

/**
 * 工厂一
 */
class ShunFengFactory implements MyFactory{

    @Override
    public Transport produce() {
        return new ShunFeng();
    }
}

/**
 * 工厂二
 */
class YuanTongFactory implements MyFactory{

    @Override
    public Transport produce() {
        return new YuanTong();
    }
}
// 抽象工厂模式end ------------

