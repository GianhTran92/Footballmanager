package vn.asiantech.intership.myapplication.model;

/**
 * Created by igianhtran on 20/10/2015.
 */
public class Position {
    public enum POSITISON {
        GK("GoalKeeper"),
        CB("CentreBack"),
        RB("RightBack"),
        LB("LeftBack"),
        CM("CentreMidfield"),
        RM("RightMidfield"),
        LM("LeftMidfield"),
        CF("CentreForward");
        private String thePosition;

        POSITISON(String aState) {
            thePosition = aState;
        }

        @Override public String toString() {
            return thePosition;
        }

    }
}
