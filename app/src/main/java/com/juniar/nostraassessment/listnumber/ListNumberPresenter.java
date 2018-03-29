package com.juniar.nostraassessment.listnumber;

import java.util.ArrayList;

public class ListNumberPresenter {
    ListNumberView callback;

    public ListNumberPresenter(ListNumberView callback) {
        this.callback = callback;
    }

    void generateListNumber(ArrayList<Integer> listNumber){
        ArrayList<String> newFilteredNumber=new ArrayList<>();
        for(int number:listNumber){
            if(number%3==0 && number%4==0){
                newFilteredNumber.add("yeay!");
            }else if(number%3==0 || number%4==0){
                newFilteredNumber.add(String.valueOf(number));
            }
        }
        callback.onGenerateListNumber(newFilteredNumber);
    }
}
