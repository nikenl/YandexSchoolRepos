package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.content.Context;

/**
 * Created by nikenl on 24.04.2016.
 */
public class CorrectNumberSpelling {

    static String albSpelling(Context ctx, int n){
        switch(n%10){
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return ctx.getString(R.string.alb5);
            case 2:
            case 3:
            case 4:
                if((n==12)||(n==13)||(n==14))return ctx.getString(R.string.alb5);
                return ctx.getString(R.string.alb2);
            case 1:
            default:
                if(n==11)return ctx.getString(R.string.alb5);
                return ctx.getString(R.string.alb1);
        }
    }
    static String songSpelling(Context ctx, int n){
        switch(n%10){
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return ctx.getString(R.string.song5);
            case 2:
            case 3:
            case 4:
                if((n==12)||(n==13)||(n==14))return ctx.getString(R.string.song5);
                return ctx.getString(R.string.song2);
            case 1:
            default:
                if(n==11)return ctx.getString(R.string.song5);
                return ctx.getString(R.string.song1);
        }
    }
}
