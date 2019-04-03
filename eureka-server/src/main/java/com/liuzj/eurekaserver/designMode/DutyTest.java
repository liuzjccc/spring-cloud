package com.liuzj.eurekaserver.designMode;

/**
 * 责任链模式
 */
public class DutyTest {
    public static void main(String[] args) {
        SingleDuty person1 = new Person1();
        person1.setSingleDuty(new Person2()).setSingleDuty(new Person3());
        person1.handleDuty(100);
    }
}

/**
 * 责任体
 */
abstract class SingleDuty{

    public SingleDuty singleDuty;

    public SingleDuty setSingleDuty(SingleDuty singleDuty) {
        this.singleDuty = singleDuty;
        return singleDuty;
    }

    public abstract void handleDuty(Integer num);
}

class Person1 extends SingleDuty{

    @Override
    public void handleDuty(Integer num) {
        if (0 <= num && num < 10) {
            System.out.println("person1 do it");
        } else {
            System.out.println("this is person2 duty");
            singleDuty.handleDuty(num);
        }
    }
}

class Person2 extends SingleDuty{

    @Override
    public void handleDuty(Integer num) {
        if (10 <= num && num < 20) {
            System.out.println("person2 do it");
        } else {
            System.out.println("this is person3 duty");
            singleDuty.handleDuty(num);
        }
    }
}

class Person3 extends SingleDuty{

    @Override
    public void handleDuty(Integer num) {
        System.out.println("person3 do it");
    }
}