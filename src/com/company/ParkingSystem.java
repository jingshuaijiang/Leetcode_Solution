package com.company;
import java.util.*;

public class ParkingSystem {
    private int big_space;
    private int medium_space;
    private int small_space;

    public ParkingSystem(int big, int medium, int small) {
        big_space = big;
        medium_space = medium;
        small_space = small;
    }

    public boolean addCar(int carType) {
        boolean ans = false;
        if(carType==1)
        {
            ans = big_space>0;
            big_space--;
        }
        else if(carType==2)
        {
            ans = medium_space>0;
            medium_space--;
        }
        else
        {
            ans = small_space>0;
            small_space--;
        }
        return ans;
    }

}
