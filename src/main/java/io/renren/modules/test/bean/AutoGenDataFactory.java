package io.renren.modules.test.bean;

public class AutoGenDataFactory extends DataFactory {

    DataTemplate dataTemplate;

    public AutoGenDataFactory(DataTemplate dataTemplate) {
        this.dataTemplate=dataTemplate;
    }

    @Override
    public void genData() {
        if (dataTemplate instanceof PickAutoTemplate){

            return;
        }else if (dataTemplate instanceof  PickCustomTemplate){
            return;
        }else if (dataTemplate instanceof RepAutoTemplate){
            return;
        }else if (dataTemplate instanceof RepCustomTemplate){
            return;
        }else {
            return;
        }


    }
}
