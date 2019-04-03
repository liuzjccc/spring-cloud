package com.liuzj.eurekaserver.designMode;

public class AdaptortTest {
    public static void main(String[] args) {
        ExpectApi expectApi = new ApiAdaptor(new RealApi());
        expectApi.getJob();
    }
}

class ExpectApi{
    public String getJob(){
        return "job";
    }
}

class RealApi{
    public String getRealApi(){
        return "this can not be use";
    }
}

/**
 * 适配器
 */
class ApiAdaptor extends ExpectApi{
    private RealApi realApi;
    public ApiAdaptor(RealApi realApi){
        this.realApi = realApi;
    }

    @Override
    public String getJob(){
        return realApi.getRealApi();
    }

}

