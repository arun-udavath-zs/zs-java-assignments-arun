package com.zs.assignment5.utils;

public class Pair {
    public String date;
    public int todayCommits;
    public int tillTodayCommits;

    public Pair(String date, int todayCommits, int tillTodayCommits) {
        this.date = date;
        this.todayCommits = todayCommits;
        this.tillTodayCommits = tillTodayCommits;
    }

    public String toString() {
        return date + " " + todayCommits + " " + tillTodayCommits;
    }
}