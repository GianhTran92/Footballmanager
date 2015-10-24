package vn.asiantech.intership.myapplication;

import com.orm.SugarApp;

import java.util.List;

import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.model.League;

/**
 * Created by igianhtran on 20/10/2015.
 * Edited by gianhtran on 23/10/2015
 */
public class BaseApp extends SugarApp {


//    public void createDummyLeagueData(){
//        for (int i = 0; i < 2; i++) {
//            League league = new League("FA CUP" + i, "img_league");
//            league.save();
//        }
//    }
//    public void createDummyFootballTeamData(){
//        List<League> leagues = League.listAll(League.class);
//        for(League league : leagues) {
//            for (int i = 1; i< 2 ; i ++) {
//                FootballTeam footballTeam = new FootballTeam(league.getName()+ " Team " + i , league.getId(),league.getName()+ " Team descripstion " + i, "img_mu");
//                footballTeam.save();
//            }
//        }
//
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        createDummyLeagueData();
//        createDummyFootballTeamData();
    }
}
