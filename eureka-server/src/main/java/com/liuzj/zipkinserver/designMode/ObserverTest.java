package com.liuzj.zipkinserver.designMode;

import java.util.ArrayList;

/**
 * 观察者模式就是被观察对象将所有观察者存起来，然后发生动作循环通知
 */
public class ObserverTest {
    public static void main(String[] args) {
        Observered observered = new Observered();

        Observer observer = new ObserverImpl();
        observered.addObserver(observer);
        observered.setNum(4);
        ObserverTest observerTest = new ObserverTest();
        System.out.println(observerTest.getClass().getName());
    }
}

/**
 * 被观察者
 */
class Observered {
    private ArrayList<Observer> registers = new ArrayList();

    private Integer num = 0;

    public void setNum(Integer num) {
        this.num = num;
        notifyObservers();
    }

    private Integer getNum() {
        return this.num;
    }

    public void addObserver(Observer observer) {
        registers.add(observer);
    }

    public void notifyObservers() {
        registers.forEach(register -> register.receive(this.num));
    }

}

/**
 * 观察者
 */
interface Observer {
    void receive(Integer num);
}

class ObserverImpl implements Observer {
    @Override
    public void receive(Integer num) {
        System.out.println("num changed to " + num);
    }
}


