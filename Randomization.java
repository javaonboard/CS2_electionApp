package com.election;

import java.util.Random;

/**

 *Description: This class design to generate random number and votes

 *Class: Fall - COSC 2436.88448

 *Final Project

 *Date: 12/12/2019

 *@author  Mohsen Shoraki

 *@version 12.12.2019

 */

public class Randomization {

    private static final int VOTE_MAX = 50;
    public final static int STATE = 4;


    /**This method is design to generate random number of vote for each candidate for each state
     * @param Candidate[], int
     * @return none
     * @throws none
     */
    public static void voteGenerator(Candidate[] candidates, int voteNumber) {
        int votes_count = 0;
        while(votes_count<voteNumber){
            int random_state = generateNumber(STATE)+1;//eliminate the zero
            int random_votes = generateNumber(VOTE_MAX)+1;
            Vote vote = new Vote(random_state,random_votes); // intilaize the vote object
            int candidate_random = generateNumber(candidates.length); // get random cand
            candidates[candidate_random].updateVotes(vote); // add in candidate list
            votes_count += random_votes;//add-up the total votes that generated.
        }
    }

    /**This method is return the random number depends on given upper bound
     * @param Map<>, List<>, int
     * @return int
     * @throws none
     */
    public static int generateNumber(int upperBound){
        Random random = new Random();
       return  random.nextInt(upperBound);
    }

}
