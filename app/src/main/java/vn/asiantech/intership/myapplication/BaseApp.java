package vn.asiantech.intership.myapplication;

import com.orm.SugarApp;

import java.util.List;

import vn.asiantech.intership.myapplication.model.Coach;
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.model.League;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * Created by igianhtran on 20/10/2015.
 * Edited by gianhtran on 23/10/2015
 */
public class BaseApp extends SugarApp {

//
//    public void createDummyLeagueData() {
//        for (int i = 0; i < 2; i++) {
//            League league = new League("FA CUP" + i, "img_league");
//            league.save();
//        }
//    }
//
//    public void createDummyFootballTeamData() {
//        List<League> leagues = League.listAll(League.class);
//        for (League league : leagues) {
//            for (int i = 1; i < 2; i++) {
//                FootballTeam footballTeam = new FootballTeam(league.getName() + " Team " + i, league.getId(), league.getName() + " Team descripstion " + i, "img_mu");
//                footballTeam.save();
//            }
//        }
//
//    }
//
//    public void createDummyPeopleData() {
//        List<FootballTeam> footballTeams = FootballTeam.listAll(FootballTeam.class);
//        for (FootballTeam footballTeam : footballTeams) {
//            for (int i = 0; i < 10; i++) {
//                if (i % 2 == 0) {
//                    Player player = new Player(footballTeam.getId(), footballTeam.getName() + " player " + i, 40f, 100f, "0000/00/00", i, Position.POSITISON.GK, "Viet Nam", "img_messi");
//                    player.save();
//                } else {
//                    Player player = new Player(footballTeam.getId(), footballTeam.getName() + " player " + i, 45f, 90f, "1111/11/11", i, Position.POSITISON.CM, "Thai Lan", "img_messi");
//                    player.save();
//                }
//            }
//            Coach coach = new Coach(footballTeam.getName() + "Coach", "0000/00/00", footballTeam.getId(), "no country", "img_coach");
//            coach.save();
//
//        }
//    }


    @Override
    public void onCreate() {
         super.onCreate();
//        createDummyLeagueData();
//        createDummyFootballTeamData();
//        createDummyPeopleData();
    }
}
