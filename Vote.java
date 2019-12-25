package com.election;

/**

 *Description: This class is Vote object

 *Class: Fall - COSC 2436.88448

 *Final Project

 *Date: 12/12/2019

 *@author  Mohsen Shoraki

 *@version 12.12.2019

 */

public class Vote {

    private int stateID;
    private int votesNumbers;

    public Vote(int stateID, int votesNumbers) {
        this.stateID = stateID;
        this.votesNumbers = votesNumbers;
    }

    public int getStateID() {
        return stateID;
    }


    public int getVotesNumbers() {
        return votesNumbers;
    }

}
