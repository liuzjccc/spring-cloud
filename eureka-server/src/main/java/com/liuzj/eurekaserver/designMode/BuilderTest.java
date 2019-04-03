package com.liuzj.eurekaserver.designMode;

/**
 * 建造者模式：将一些必要且繁琐的方法放到一个方法中，然后执行一个方法则将一些必要的方法全部执行
 */
public class BuilderTest {
    public static void main(String[] args) {
        new Builder(new Buildered()).build();
    }
}

class Buildered {

    public void one(){
        System.out.println(1);
    }

    public void two(){
        System.out.println(2);
    }

    public void three(){
        System.out.println(3);
    }
}


class Builder{

    private Buildered buildered;

    public Builder(Buildered buildered){
        this.buildered = buildered;
    }

    public void build(){
        buildered.one();
        buildered.two();
        buildered.three();
    }
}
