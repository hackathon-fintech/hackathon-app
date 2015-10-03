package cl.tavor.bancointeligente;

import android.app.Application;
import android.graphics.Bitmap;

import com.estimote.sdk.EstimoteSDK;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by gustavo on 10/3/15.
 */

public class App extends Application {

    public static final String ESTIMOTE_APP_ID = "app_1g8ts8twhk";
    public static final String ESTIMOTE_APP_TOKEN = "77eb17285d7fe1ac6b0db2850d40f36d";

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        // Initializes Estimote SDK with your App ID and App Token from Estimote Cloud.
        // You can find your App ID and App Token in the
        // Apps section of the Estimote Cloud (http://cloud.estimote.com).
        EstimoteSDK.initialize(this, ESTIMOTE_APP_ID, ESTIMOTE_APP_TOKEN);
        // Configure verbose debug logging.
        EstimoteSDK.enableDebugLogging(true);
        if (BuildConfig.DEBUG){

        }
        else {

        }
    }

    public App() {
        super();
    }

    /**
     * Initializes the Image Loader.
     */
    public void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new FadeInBitmapDisplayer(10))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                        //.tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

}
