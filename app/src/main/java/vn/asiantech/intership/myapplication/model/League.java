package vn.asiantech.intership.myapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by igianhtran on 20/10/2015.
 */

@Table(name = "league")
public class League extends SugarRecord {
    @Column(name = "name")
    String name;
    @Column(name = "logo")
    String logo;

    public League() {

    }

    public League(String name, String logo) {
        this.name = name;
        this.logo = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getId() {
        return getId();
    }
}
