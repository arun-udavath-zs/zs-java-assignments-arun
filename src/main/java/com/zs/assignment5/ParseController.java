package com.zs.assignment5;

import com.zs.assignment5.service.FileProgram;

public class ParseController {
      public void parser(){
          FileProgram fileProgram= new FileProgram();
          fileProgram.openFile();
          fileProgram.totalCommits();
          fileProgram.eachDayCommit();
          fileProgram.commitHistory();
      }

}
