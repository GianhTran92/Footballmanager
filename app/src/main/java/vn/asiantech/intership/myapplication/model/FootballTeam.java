package vn.asiantech.intership.myapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by igianhtran on 20/10/2015.
 */
@Table(name = "footballteam")
public class FootballTeam extends SugarRecord {
    @Column(name = "name")
    String name;
    @Column(name = "leagueId")
    long leagueId;
    @Column(name = "Descripsion")
    String descripstion;
    @Column(name = "logo")
    String logo;

    public FootballTeam() {
    }

    public FootballTeam(String name, long leagueId, String descripstion, String logo) {
        this.name = name;
        this.leagueId = leagueId;
        this.descripstion = descripstion;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
