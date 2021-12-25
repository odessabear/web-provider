package com.odebar.webprovider.form;

public class TariffForm {

    private Integer tariffId;
    private Integer count;

    public TariffForm(Integer tariffId, Integer count) {
        this.tariffId = tariffId;
        this.count = count;
    }

    public TariffForm() {
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
