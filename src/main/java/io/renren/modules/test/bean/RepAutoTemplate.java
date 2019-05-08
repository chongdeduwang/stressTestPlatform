package io.renren.modules.test.bean;

public class RepAutoTemplate extends DataTemplate {
    private long gap;
    private long nonExeOrderMax;
    private boolean preSetInven0;
    private boolean preSetInven1000000;
    private boolean onlyGoodsBatch;
    private boolean allowGoodsOnBucketForbidden;
    private String repOrderState;
    public void setGap(long gap) {
        this.gap = gap;
    }
    public long getGap() {
        return gap;
    }

    public void setNonExeOrderMax(long nonExeOrderMax) {
        this.nonExeOrderMax = nonExeOrderMax;
    }
    public long getNonExeOrderMax() {
        return nonExeOrderMax;
    }

    public void setPreSetInven0(boolean preSetInven0) {
        this.preSetInven0 = preSetInven0;
    }
    public boolean getPreSetInven0() {
        return preSetInven0;
    }

    public void setPreSetInven1000000(boolean preSetInven1000000) {
        this.preSetInven1000000 = preSetInven1000000;
    }
    public boolean getPreSetInven1000000() {
        return preSetInven1000000;
    }

    public void setOnlyGoodsBatch(boolean onlyGoodsBatch) {
        this.onlyGoodsBatch = onlyGoodsBatch;
    }
    public boolean getOnlyGoodsBatch() {
        return onlyGoodsBatch;
    }

    public void setAllowGoodsOnBucketForbidden(boolean allowGoodsOnBucketForbidden) {
        this.allowGoodsOnBucketForbidden = allowGoodsOnBucketForbidden;
    }
    public boolean getAllowGoodsOnBucketForbidden() {
        return allowGoodsOnBucketForbidden;
    }

    public void setRepOrderState(String repOrderState) {
        this.repOrderState = repOrderState;
    }
    public String getRepOrderState() {
        return repOrderState;
    }

    @Override
    public void generateOrder() {
        
    }
}
