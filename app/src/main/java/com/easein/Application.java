package com.easein;

import android.content.Context;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseObject;

public class Application extends android.app.Application {
  // Debugging switch
  public static final boolean APPDEBUG = false;

  // Debugging tag for the application
  public static final String APPTAG = "EaseIn";

  // Used to pass location from MainActivity to PostActivity
  public static final String INTENT_EXTRA_LOCATION = "location";

  // Key for saving the search distance preference
  private static final String KEY_SEARCH_DISTANCE = "searchDistance";

  private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;

  private static SharedPreferences preferences;

  private static ConfigHelper configHelper;

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

    ParseObject.registerSubclass(Post.class);
    Parse.initialize(this, "u458dxTIdxn0GVwMOzXBD8NzHaCZvAm62zXScLVY", "vTbHt9HEvvNrWwpEmqFDqe60ugJYpk3falqvHge8");

    preferences = getSharedPreferences("com.parse", Context.MODE_PRIVATE);

    ParseObject testObject = new ParseObject("TestObject");
    testObject.put("foo", "bar");
    testObject.saveInBackground();

    configHelper = new ConfigHelper();
    configHelper.fetchConfigIfNeeded();
  }

  public static float getSearchDistance() {
    return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
  }

  public static ConfigHelper getConfigHelper() {
    return configHelper;
  }

  public static void setSearchDistance(float value) {
    preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
  }
//TODO
}
