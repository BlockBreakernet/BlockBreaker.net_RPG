package net.blockbreaker.rpg.api.log;

import net.blockbreaker.rpg.system.Main;

import java.lang.Enum;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lukas on 27.03.2015.
 */
public class Logger {

    private static String logprefix = Main.consolePrefix.substring(0, Main.consolePrefix.length()-2);
    private static String logdate;

    LoggerFile file = new LoggerFile();


    public static void log(String log, Enum state) {

        //Log in Console
        Date current = new Date();
        DateFormat datetime = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        logdate = datetime.format(current);

        StringBuilder logmsgcons = new StringBuilder();


        if(state == LoggerState.INFO) {
            logmsgcons.append(logprefix + "Logger | Date: " + logdate + "] [INFO] " + log);
        }

        if(state == LoggerState.WARNING) {
            logmsgcons.append(logprefix + "Logger | Date: " + logdate + "] [WARNING] " + log);
        }

        if(state == LoggerState.HACkING) {
            logmsgcons.append(logprefix + "Logger | Date: " + logdate + "] [HACKING] " + log);
        }

        if(state == LoggerState.CRASH) {
            logmsgcons.append(logprefix + "Logger | Date: " + logdate + "] [CRASH] " + log);
        }


        String finallogcons = logmsgcons.toString();

        System.out.println(finallogcons);


        //Log in File
        StringBuilder logmsg = new StringBuilder();

        logmsg.append(log);

        String finallog = logmsg.toString();

        if(state == LoggerState.INFO) {
            LoggerFile.logData("[ " + logdate + " ] [INFO]",finallog);
        }

        if(state == LoggerState.WARNING) {
            LoggerFile.logData("[ " + logdate + " ] [WARNING]",finallog);
        }

        if(state == LoggerState.HACkING) {
            LoggerFile.logData("[ " + logdate + " ] [HACKING]",finallog);
        }

        if(state == LoggerState.CRASH) {
            LoggerFile.logData("[ " + logdate + " ] [CRASH]",finallog);
        }

    }
}
