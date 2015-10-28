package vn.asiantech.intership.myapplication;

import com.orm.SugarApp;

import java.util.List;
import java.util.Random;

import vn.asiantech.intership.myapplication.common.Common;
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
//    Random mRd = new Random();
//    // 15 item
//    String mFamilyNames[] = {
//            "Suzuki","Takahashi","Nakamura ","Yoshida","Kimura","Trần","Nguyễn","Lê","Phan"," Lee "," Choi "," Kang","Smith","Brown","Thomas"
//    };
//    //15 item
//    String mNames[] = {
//            "Côté","Hùng","Dũng","Châu","Đức","Thomas","Jackson","White","da Silva","Rodríguez","Öztürk","Nambissan","Kimura","Shimizu","Zo"
//    };
//    // 4 item
//    String mCups[] = {
//            "Cup","League","Championship","Amateur"
//    };
//    // 10 item
//    String mLocations[] = {
//            "Afghanistan", "Armenia ","Asian","Japan","LIGA","Premier","Việt Nam","FA","National","Football"
//    };
//    // 10 item
//    String mTeams[] = {
//            "U19","U20","U21","U22","U23","U24","U18","U17","U16","National"
//    };
//
//    public void createDummyLeagueData() {
//        for (int i = 0; i < 20; i++) {
//            League league = new League(mLocations[mRd.nextInt(10)]+" "+mCups[mRd.nextInt(4)], "img_league");
//            league.save();
//        }
//    }
//
//    public void createDummyFootballTeamData() {
//        List<League> leagues = League.listAll(League.class);
//        for (League league : leagues) {
//            for (int i = 1; i < 20; i++) {
//                FootballTeam footballTeam = new FootballTeam(mLocations[mRd.nextInt(10)]+" "+mTeams[mRd.nextInt(10)], league.getId(), league.getName() + " Team descripstion " + i, "img_mu");
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
//                    Player player = new Player(footballTeam.getId(),mFamilyNames[mRd.nextInt(15)] + " " + mNames[mRd.nextInt(15)], 40f, 100f, "0000/00/00", i, Position.POSITISON.GK, Common.ARR[mRd.nextInt(3)], "img_messi");
//                    player.save();
//                } else {
//                    Player player = new Player(footballTeam.getId(), mFamilyNames[mRd.nextInt(15)] + " " + mNames[mRd.nextInt(15)], 45f, 90f, "1111/11/11", i, Position.POSITISON.CM,Common.ARR[mRd.nextInt(3)], "img_messi");
//                    player.save();
//                }
//            }
//            Coach coach = new Coach(mFamilyNames[mRd.nextInt(15)] + " " + mNames[mRd.nextInt(15)], "0000/00/00", footballTeam.getId(),Common.ARR[mRd.nextInt(3)], "img_coach");
//            coach.save();
//
//        }
//    }
//
//    public void createPlayerFree(){
//        for (int i = 0; i <10; i++) {
//            Player player = new Player(-1l,mFamilyNames[mRd.nextInt(15)] + " " + mNames[mRd.nextInt(15)], 40f, 100f, "0000/00/00", i, Position.POSITISON.GK,Common.ARR[mRd.nextInt(3)], "img_messi");
//            player.save();
//        }
//
//    }

    @Override
    public void onCreate() {
         super.onCreate();
//        createDummyLeagueData();
//        createDummyFootballTeamData();
//        createDummyPeopleData();
//        createPlayerFree();
    }
}
