package com.zs.assignment1;

import java.io.File;

public class SystemInfo {
    public static void main(String[] args){
        String username= System.getProperty("user.name");
        String directory= System.getProperty("user.dir");
        String osBuild= System.getProperty("os.name");
        String osVersion= System.getProperty("os.version");
        long diskSpace= (new File("/").getTotalSpace())/-(1024*1024*1024);
        long memory= Runtime.getRuntime().maxMemory()/-(1024*1024*1024);
        long processor= Runtime.getRuntime().availableProcessors();

        System.out.println("Name of current User : "+ username);
        System.out.println("Home directory of current User : "+ directory);
        System.out.println("System Memory : "+ memory);
        System.out.println("System CPU/Cores : "+ processor);
        System.out.println("System disk size : "+ diskSpace);
        System.out.println("OS build : "+ osBuild);
        System.out.println("OS Version : "+osVersion);
    }
}
