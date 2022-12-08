package com.zs.assignment5;

import com.zs.assignment5.service.CommitHistory;
import com.zs.assignment5.service.EachDayCommits;
import com.zs.assignment5.service.FileException;
import com.zs.assignment5.service.TotalCommitService;
import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class ParseController {
    Logger logger = LoggerFactory.getLogger(ParseController.class);
    TotalCommitService totalCommitService= new TotalCommitService();
    EachDayCommits eachDayCommits= new EachDayCommits();
    CommitHistory commitHistory= new CommitHistory();

    public void parser() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int choice;
        String filePath;
        FileException fileProgram = new FileException();
        List<LogObject> logData;

        logger.info("Enter the log file path");
        filePath = sc.next();
        logData = fileProgram.openFile(filePath);

        logger.info("Enter choice\n1.Total Count of commits by each developer since date d\n" +
                "2. Count of commits by each developer since date d, for each day\n" +
                "3. List of developers who did not commit anything successively in 2 days\n" +
                "4. to exit");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                totalCommitService.totalCommitsByDev(logData);
                parser();
                break;
            case 2:
                eachDayCommits.commitsByDevEachDay(logData);
                parser();
                break;
            case 3:
                commitHistory.commitDetails(logData);
                parser();
                break;
            case 4:
                break;
            default:
                logger.info("please enter correct choice");
                parser();
        }
    }

}
