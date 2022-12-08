package com.zs.assignment5.service;

import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.zs.assignment5.utils.DateConversion.dateConverter;

public class CommitHistory {
    Logger logger = LoggerFactory.getLogger(CommitHistory.class);

    /**
     * shows developer commit history
     *
     * @param logData log data of commits
     * @throws ParseException throws exception if conversion of date fails
     */
    public void inActiveUser(List<LogObject> logData) throws ParseException {
        Scanner sc = new Scanner(System.in);
        logger.info("Enter date in yyy-mm-dd format");
        String inputDate = sc.next();
        Date date = dateConverter(inputDate);
        Map<String, Date> mapData = new HashMap<>();
        List<String> user = new ArrayList<>();

        for (LogObject object : logData) {
            if (object.date.after(date)) {
                if (mapData.containsKey(object.authorName)) {
                    Date lastCommitDate = mapData.get(object.authorName);
                    long duration = lastCommitDate.getTime() - object.date.getTime();
                    long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
                    if (diffInDays > 2)
                        user.add(object.authorName);
                    mapData.put(object.authorName, lastCommitDate);
                } else {
                    mapData.put(object.authorName, object.date);
                }
            }
        }
        if (user.size() == 0) {
            logger.info("No author found");
        } else {
            logger.info("Author Names: " + user);
        }
    }

}
