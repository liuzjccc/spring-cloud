package com.liuzj.zipkinserver.designMode;

/**
 * 装饰模式其实就是对类的层层增强(典型应用就是IO流)
 */
public class DecorateTest {
    public static void main(String[] args) {
        new TankImplTwo(new TankImplOne(new BaseTank())).hit();
    }
}

interface Tank{
    void hit();
}

class BaseTank implements Tank{
    @Override
    public void hit() {
        System.out.println("射击~~~");
    }
}

class TankImplOne extends BaseTank{
    
    private Tank tank;

    public TankImplOne(Tank tank){
        this.tank = tank;
    }

    @Override
    public void hit() {
        System.out.println("装载导弹");
        tank.hit();
    }
}

class TankImplTwo extends BaseTank{

    private Tank tank;

    public TankImplTwo(Tank tank){
        this.tank = tank;
    }

    @Override
    public void hit() {
        System.out.println("装载烟雾弹");
        tank.hit();
    }
}
