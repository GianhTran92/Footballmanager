package vn.asiantech.intership.myapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.sql.Date;

/**
 * Created by igianhtran on 20/10/2015.
 */
@Table(name = "coach")
public class Coach extends SugarRecord {
    @Column(name = "name")
    String name;
    @Column(name = "birthday")
    String birthday;
    @Column(name = "teamid")
    Long teamId;
    @Column(name = "country")
    String country;
    @Column(name = "avatar")
    String avatar;

    public Coach() {
    }

    public Coach(String name, String birth, Long teamId, String country, String avatar) {
        this.name = name;
        this.birthday = birth;
        this.teamId = teamId;
        this.country = country;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
