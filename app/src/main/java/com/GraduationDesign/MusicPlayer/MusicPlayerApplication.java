package com.GraduationDesign.MusicPlayer;

import android.app.Application;
import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/31/16
 * Time: 9:32 PM
 * Desc: MusicPlayerApplication
 */
public class MusicPlayerApplication extends Application {

    private static MusicPlayerApplication sInstance;
    private ExecutorService mFixedThreadPool;
    private static Handler handler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());//初始化线程池
        // Custom fonts
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Monospace-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }
    /**
     * 主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static MusicPlayerApplication getInstance() {
        return sInstance;
    }
    public void newThread(Runnable runnable) {

        try {
            mFixedThreadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
            mFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());//初始化线程池
            mFixedThreadPool.execute(runnable);
        }
    }
    public void shutdownThreadPool(){
        mFixedThreadPool.shutdownNow();
    }

}
