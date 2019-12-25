package com.election;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**

 *Description: This class is represent the Candidate object

 *Class: Fall - COSC 2436.88448

 *Final Project

 *Date: 12/12/2019

 *@author  Mohsen Shoraki

 *@version 12.12.2019

 */

public class Candidate implements Comparable<Candidate>{

     private int candidateID;
     private String candidateName;
     private Map<Integer,List<Vote>> votes;

    /**This method is constructor
     * @param int, String
     * @return none
     * @throws none
     */

    public Candidate(int candidateID, String candidateName) {
        this.candidateID = candidateID;
        this.candidateName = candidateName;
        this.votes = new HashMap<>();
    }


    public String getCandidateName() {
        return candidateName;
    }


    /**This method is update the vote
     * @param Vote
     * @return none
     * @throws none
     */
    public void updateVotes(Vote vote){

        if(this.votes.containsKey(vote.getStateID()))votes.get(vote.getStateID()).add(vote);
        else{
            List<Vote> list = new ArrayList<>();
            list.add(vote);
            votes.put(vote.getStateID(),list);
        }
    }

    /**This method is return the total vote of each state fro each candidate
     * @param Map<>, List<>, int
     * @return int
     * @throws none
     */
    public static int getTotalVote(Map<Integer,List<Vote>> votes, int state){
        int total = 0 ;
        for(int i = 0; i < votes.get(state).size();i++){
            total += votes.get(state).get(i).getVotesNumbers();
        }
        return total;
    }

    /**This method is return the votes Map<>
     * @param none
     * @return Map<>
     * @throws none
     */
    public Map<Integer, List<Vote>> getVotes(){
        return this.votes;
    }

    public boolean checkName(Candidate cand){
        return compareTo(cand)==0?false:true;
    }

    @Override
    public int compareTo(Candidate o) {
        return this.candidateName.compareTo(o.candidateName);
    }
}
