package com.election;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;


/**

 *Description: This class is result that control the election program flow

 *Class: Fall - COSC 2436.88448

 *Final Project

 *Date: 12/12/2019

 *@author  Mohsen Shoraki

 *@version 12.12.2019

 */

public class Result {

   public static Candidate[] candidates;

    /**This method is main method that invoke by JVM that design to interact with user to execute the election program
     * @param String[] args
     * @return none
     * @throws none
     */
    public static void main(String[] args) throws IOException {

        JOptionPane.showMessageDialog(null, "Welcome the RichLand College Election vote system!\nPlease answer the question in next section to complete this polling.");
        int candidateNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of candidate that going to be at this election? "));
        candidates = new Candidate[candidateNumber];
        for(int i = 0; i < candidateNumber; i++){
            String name = JOptionPane.showInputDialog("Please Enter the name of candidate number "+ (i+1) +" : ");
            Candidate candidate = new Candidate((1000+i), name);
            candidates[i] = candidate;
        }
        sort(candidates);//sort candidate respective to their name
        int voteNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter the desire number for maximum number of votes should be generated in all 4 state: "));
        Randomization.voteGenerator(candidates,voteNumber);
        writeInFile(candidates);
        readFromFile();

    }


    /**This method is design to read the content of election from file and print it out with calculation
     * @param none
     * @return none
     * @throws none
     */
    private static void readFromFile() throws IOException {
        File file = new File("db.txt");
        Scanner sc = new Scanner(file);
        int count = 0;
        int totalCand = 0;
        int total = 0;
        String winner = "";
        int maxTotal = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Name          State_1          State_2          State_3          State_4          Total\n");
        while(sc.hasNext()){
            //System.out.println(sc.nextLine());
             String [] st1 = sc.nextLine().split(",");

                if(count==0) {
                    sb.append(st1[0] + "               "+st1[2]);
                }else{
                    sb.append("                  "+st1[2]);
                }
            totalCand+= Integer.parseInt(st1[2]);
            count++;
                if(count==4) {
                    //find the winner
                    if(totalCand>maxTotal){
                        maxTotal = totalCand;
                        winner = st1[0];
                    }
                    sb.append("          "+totalCand+"\n");
                    totalCand = 0;
                    count = 0;
                }
              total+= Integer.parseInt(st1[2]);
        }
        sb.append("-------------------------------------------------------------\n");
        sb.append("Total polled Votes: "+total+"\n");
        sb.append("Winner: ****"+ winner+"****\n");
        sb.append("Thanks For Using this Program!");
        JOptionPane.showMessageDialog(null,sb.toString());

    }



    /**This method is design to write the generated data of election in file
     * @param Candidate[]
     * @return none
     * @throws none
     */
    public static void writeInFile(Candidate[] candidates) throws IOException
    {
        File file = new File("db.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

            for (int i = 0; i < candidates.length; i++) {
                for(int j = 1; j<5; j++) { // for each state 1 - 4
                    int total_state = candidates[i].getTotalVote(candidates[i].getVotes(),j);
                    fw.append(candidates[i].getCandidateName() + "," + j + ","+ total_state+"\n");

                 }
        }

            fw.flush();
            fw.close();
    }


    /**This method is following quic sort algorithm to sort the candidate respective to their name
     * @param Candidate[]
     * @return none
     * @throws none
     */
    public static void sort(Candidate[] candidates){
        quickSort(candidates,0,candidates.length-1);
    }

    /**This method is helper for the sort method to complete the task recursively
     * @param Candidate[], int, int
     * @return none
     * @throws none
     */
    public static void quickSort(Candidate[] cand, int low, int high){

        int i = low;
        int j = high;
        int pivot = cand[low + (high - low)/2].getCandidateName().charAt(0);
        //System.out.println(cand[low + (high - low)/2].getCandidateName());
        while(i<j){
            while(cand[i].getCandidateName().charAt(0)<pivot){
                i++;
            }
            while(cand[j].getCandidateName().charAt(0)>pivot){
                j--;
            }
            if(i<=j){
                Candidate temp = cand[i];
                cand[i] = cand[j];
                cand[j] = temp;
                i++;
                j--;
            }
            if(low<j)quickSort(cand,low,j);
            if(i<high)quickSort(cand,i,high);
        }


    }

}
