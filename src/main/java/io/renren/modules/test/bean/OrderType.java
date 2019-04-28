package io.renren.modules.test.bean;

public enum OrderType {
    OUT("PICKING_ORDER",1),IN_ONLINE("REP_ORDER_ONLINE",2),IN_OFFLINE("REP_ORDER_OFFLINE",3),COUNT("COUNT_ORDER",4);
    private String name;
    private int index;

    OrderType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
