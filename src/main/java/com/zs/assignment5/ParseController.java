package com.zs.assignment5;

import com.zs.assignment5.service.CommitHistory;
import com.zs.assignment5.service.EachDayCommits;
import com.zs.assignment5.service.FileException;
import com.zs.assignment5.service.TotalCommitService;
import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseController {
    Logger logger = LoggerFactory.getLogger(ParseController.class);
    private final TotalCommitService totalCommitService;
    private final EachDayCommits eachDayCommits;
    private final CommitHistory commitHistory;

    public ParseController() {
        this.totalCommitService = new TotalCommitService();
        this.eachDayCommits = new EachDayCommits();
        this.commitHistory = new CommitHistory();
    }

    public void parser() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int choice,n;
        String filePath;
        FileException fileProgram = new FileException();
        List<LogObject> logData;
        List<String> authorList = new ArrayList<>();
        logger.info("Enter the log file");
        filePath = sc.next();
        logger.info("Enter no.of authors and author names in log file");
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            authorList.add(sc.next());
        }

        logData = fileProgram.openFile(filePath, authorList);

        do {
            logger.info("Enter choice\n1.Total Count of commits by each developer since date d\n" +
                    "2. Count of commits by each developer since date d, for each day\n" +
                    "3. List of developers who did not commit anything successively in 2 days\n" +
                    "4. to exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    totalCommitService.totalCommitsByDev(logData);
                    break;
                case 2:
                    eachDayCommits.commitsByDevEachDay(logData);
                    break;
                case 3:
                    commitHistory.inActiveUser(logData);
                    break;
                case 4:
                    break;
                default:
                    logger.info("please enter correct choice");
            }
        } while (choice != 4);
    }

}
