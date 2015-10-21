package vn.asiantech.intership.myapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.sql.Date;

/**
 * Created by igianhtran on 20/10/2015.
 */
@Table(name = "player")
public class Player extends SugarRecord {
    @Column(name = "name")
    String name;
    @Column(name = "weight")
    Float weight;
    @Column(name = "height")
    Float height;
    @Column(name = "birthday")
    String birthday;
    @Column(name = "number")
    int number;
    @Column(name = "position")
    Position.POSITISON positison;
    @Column(name = "country")
    String country;
    @Column(name = "Avatar")
    String avatar;
    @Column(name = "teamid")
    long teamId;

    public Player() {
    }

    public Player(long teamId, String name, Float weight, Float height, String bird, int number, Position.POSITISON positison, String country, String avatar) {
        this.teamId = teamId;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.birthday = bird;
        this.number = number;
        this.positison = positison;
        this.country = country;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getBird() {
        return birthday;
    }

    public void setBird(String bird) {
        this.birthday = bird;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Position.POSITISON getPositison() {
        return positison;
    }

    public void setPositison(Position.POSITISON positison) {
        this.positison = positison;
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

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
