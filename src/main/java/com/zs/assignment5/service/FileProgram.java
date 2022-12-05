package com.zs.assignment5.service;

import com.zs.assignment5.repository.Repository;
import com.zs.assignment5.utils.LogObject;
import com.zs.assignment5.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FileProgram {
    final static Logger logger = LoggerFactory.getLogger(FileProgram.class);
    public static Map<String, List<Pair>> map = new HashMap<>();

    public void openFile() {
        try {
            File file = new File("/home/lenovo/Documents/zs-java-assignments/src/main/resources/log.txt");
            List<LogObject> list = new ArrayList<>();
            Repository repo = new Repository();
            repo.readFile(file, list);

            for (LogObject object : list) {
                if (object.commitId.length() != 40) {
                    logger.error("commitId is incomplete");
                }
                if (!object.authorName.equals("arunudavath") && !object.authorName.equals("iliyaz-ali") && !object.authorName.equals("md-iqbal")) {
                    logger.error("Author name is incorrect");
                }
                if (object.date.length() < 1) {
                    logger.error("date format incomplete");
                }
            }
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
        }
    }

    /*
     * to count the total commits by each developer till day
     */
    public void totalCommits() {
        Scanner sc = new Scanner(System.in);
        int i;
        logger.info("Total Count of commits by each developer since date d, enter date");
        String date = sc.next(); // 24-Nov-2022

        for (Map.Entry<String, List<Pair>> entry : map.entrySet()) {
            List<Pair> list = entry.getValue();
            for (i = 0; i < list.size(); i++) {
                if (list.get(i).date.equals(date)) {
                    int count = list.get(i).tillTodayCommits;
                    logger.info("total commits by " + entry.getKey() + " are : " + count);
                    break;
                }
            }
            if (i == list.size()) {
                logger.info("date not found in logs " + entry.getKey());
            }
        }
    }

    /*
     * developer commit by each day from given date
     */

    public void eachDayCommit() {
        Scanner sc = new Scanner(System.in);
        int i;
        logger.info("Count of commits by each developer since date d, for each day");
        String date = sc.next();

        for (Map.Entry<String, List<Pair>> entry : map.entrySet()) {
            List<Pair> list = entry.getValue();
            for (i = 0; i < list.size(); i++) {
                if (list.get(i).date.equals(date)) {
                    for (int j = i; j >= 0; j--) {
                        System.out.println(entry.getKey() + " on " + list.get(j).date + " commits are : " + list.get(j).todayCommits);
                    }
                    break;

                }
            }
            if (i == list.size()) {
                logger.info("date not found in logs for " + entry.getKey());
            }
        }
    }

    /*
     * to list the developer who didn't commit anything two days in a row
     */
    public void commitHistory() {
        Scanner sc = new Scanner(System.in);
        logger.info("List of developers who did not commit anything successively in 2 days f and d,enter d");
        String d = sc.next();

        for (Map.Entry<String, List<Pair>> entry : map.entrySet()) {
            List<Pair> list = entry.getValue();
            int index1 = 0;
            for (int i = 0; i < list.size(); i++) {
                if (d.equals(list.get(i).date)) {
                    index1 = i;
                    break;
                }
            }
            int prev = Integer.valueOf(list.get(index1).date.substring(0, 2)), curr;
            for (int i = index1 - 1; i >= 0; i--) {
                curr = Integer.valueOf(list.get(i).date.substring(0, 2));
                if (curr - prev >= 2) logger.info(entry.getKey() + " is not commited two days in row");
                prev = curr;
            }
        }
    }

}

