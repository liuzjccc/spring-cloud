package com.liuzj.eurekaserver.designMode;

/**
 * 桥接模式：将抽象和实现分离，即抽象的方法改动不会影响到实现
 */
public class Bridge {
    public static void main(String[] args) {
        BridgeClass bridgeClass = new BridgeClassImpl();
        bridgeClass.setIntefe(new intefeImpl());
        bridgeClass.say();
    }
}

/**
 * 抽象
 */
interface Intefe{
    void say();
}

/**
 * 实现一
 */
class intefeImpl implements Intefe{

    @Override
    public void say() {
        System.out.println("intefeImpl");
    }
}

/**
 * 实现二
 */
class intefeImpl2 implements Intefe{

    @Override
    public void say() {
        System.out.println("intefeImpl2");
    }
}

/**
 * 桥接类
 */
abstract class BridgeClass{

    private Intefe intefe;

    public Intefe getIntefe() {
        return intefe;
    }

    public void setIntefe(Intefe intefe) {
        this.intefe = intefe;
    }

    public void say(){
        intefe.say();
    }
}

/**
 * 桥的实现类
 */
class BridgeClassImpl extends BridgeClass{

    @Override
    public void say(){
        super.getIntefe().say();
    }
}