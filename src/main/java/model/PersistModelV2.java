package model;

/**
 * Created by Hale on 23.04.2017.
 */
public class PersistModelV2 extends PersistModel {


    private String city;

    public PersistModelV2(PersistModel persistModel) {
        super(persistModel.getName());
        city = "";
    }

    public PersistModelV2(String name) {
        super(name);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
