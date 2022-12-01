package com.zs.assignment1;

import java.io.File;

public class SystemInfo {
    public static void main(String[] args){
        System.out.println("Name of current User : "+ System.getProperty("user.name"));
        System.out.println("Home directory of current User : "+ System.getProperty("user.dir"));
        System.out.println("System Memory : "+ Runtime.getRuntime().maxMemory()/-(1024*1024*1024));
        System.out.println("System CPU/Cores : "+Runtime.getRuntime().availableProcessors());
        System.out.println("System disk size : "+ (new File("/").getTotalSpace())/-(1024*1024*1024));
        System.out.println("OS build : "+ System.getProperty("os.name"));
        System.out.println("OS Version : "+ System.getProperty("os.version"));
    }
}
