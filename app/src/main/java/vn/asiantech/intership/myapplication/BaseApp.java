package vn.asiantech.intership.myapplication;

import com.orm.SugarApp;

import vn.asiantech.intership.myapplication.model.League;

/**
 * Created by igianhtran on 20/10/2015.
 * Edited by gianhtran on 23/10/2015
 */
public class BaseApp extends SugarApp {

//
//    public void createDummyData(){
//        for (int i = 0; i < 20; i++) {
//            League league = new League("FA CUP" + i, "img_league");
//            league.save();
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
