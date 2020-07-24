package org.framework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.totoroshootinggame.Enemy.IBossState;
import org.totoroshootinggame.Player.Player;
import org.totoroshootinggame.State.GameState;

public class AppManager {

    public IntroActivity introActivity;

    private GameView m_gameView;    // main gameView
    private Resources m_resources;  // main gameView의 resource
    private WindowManager m_windowManager;

    private int displayWidth, displayHeight;

    private SensorManager sensorManager;
    private Sensor gyroScopeSensor, accelSensor;

    public GameState m_gameState;

    public IBossState bossState1 = null;
    public IBossState bossState2 = null;
    public IBossState bossState3 = null;

    public Player player = null;
    public Player player_state = null;

    public int topBar = 100;


    public WindowManager getWindowManager() {
        return m_windowManager;
    }

    public void setWindowManager(WindowManager m_windowManager) {
        this.m_windowManager = m_windowManager;
    }

    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resources, r);
    }

    public GameView getGameView() { return m_gameView; }

    public void setGameView(GameView m_gameView) { this.m_gameView = m_gameView; }

    public Resources getResources() { return m_resources; }

    public void setResources(Resources m_resources) { this.m_resources = m_resources; }

    public int getDisplayWidth() { return getResources().getDisplayMetrics().widthPixels; }

    public void setDisplayWidth(int displayWidth) { this.displayWidth = displayWidth; }

    public int getDisplayHeight() { return getResources().getDisplayMetrics().heightPixels; }

    public void setDisplayHeight(int displayHeight) { this.displayHeight = displayHeight; }

    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public Sensor getGyroScopeSensor() {
        return gyroScopeSensor;
    }

    public void setGyroScopeSensor(Sensor gyroSensor) {
        this.gyroScopeSensor = gyroSensor;
    }

    public Sensor getAccelSensor() {
        return accelSensor;
    }

    public void setAccelSensor(Sensor accelSensor) {
        this.accelSensor = accelSensor;
    }

    private static AppManager s_instance;

    public static AppManager getInstance(){
        if(s_instance == null) s_instance = new AppManager();
        return s_instance;
    }
}
