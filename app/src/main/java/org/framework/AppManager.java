package org.framework;

public class AppManager {

    private static AppManager s_instance;

    public static AppManager getInstance(){
        if(s_instance == null) s_instance = new AppManager();
        return s_instance;
    }
}
