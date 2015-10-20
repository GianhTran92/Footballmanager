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
    Date bird;
    @Column(name = "number")
    int number;
    @Column(name = "position")
    Position.POSITISON positison;
    @Column(name = "country")
    String country;
    @Column(name = "Avatar")
    String avatar;
    @Column(name = "teamid")
    Long teamId;

    public Player() {
    }

    public Player(Long teamId, String name, Float weight, Float height, Date bird, int number, Position.POSITISON positison, String country, String avatar) {
        this.teamId = teamId;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.bird = bird;
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

    public Date getBird() {
        return bird;
    }

    public void setBird(Date bird) {
        this.bird = bird;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
