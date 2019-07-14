package com.cookandroid.projectmycinema;

import android.graphics.drawable.Drawable;

public class DailyBoxOffice {
    private Drawable iconDrawable;

    String rank;
    String movieCd;
    String movieNm;
    String openDt;
    String audiAcc;
    String genreNm;
    String nationsNm;
    String peopleNm;
    String watchGradeNm;
    String showTm;
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setRank(String rank) {
        this.rank = rank ;
    }
    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getRank() {
        return this.rank ;
    }
    public String getMovieNm() {
        return this.movieNm;
    }
    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm ;
    }

    public String getMovieCd() {
        return this.movieCd ;
    }
    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd ;
    }
    public String getOpenDt() {
        return this.openDt ;
    }
    public void setOpenDt(String openDt) {
        this.movieCd = openDt ;
    }
    public String getGenreNm() {
        return this.genreNm ;
    }
    public void setGenreNm(String genreNm) {
        this.genreNm = genreNm ;
    }
    public String getNationsNm() {
        return this.nationsNm ;
    }
    public void setNationsNm(String nationsNm) {
        this.nationsNm = nationsNm ;
    }
    public String getPeopleNm() {
        return this.peopleNm ;
    }
    public void setPeopleNm(String peopleNm) {
        this.peopleNm = peopleNm ;
    }
    public String getaudiAcc() {
        return this.audiAcc ;
    }
    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc ;
    }
    public String getWatchGradeNm() {
        return this.watchGradeNm ;
    }
    public void setWatchGradeNm(String watchGradeNm) {
        this.watchGradeNm = watchGradeNm ;
    }
    public String getShowTm() {
        return this.showTm ;
    }
    public void setShowTm(String showTm) {
        this.showTm = showTm ;
    }

}
