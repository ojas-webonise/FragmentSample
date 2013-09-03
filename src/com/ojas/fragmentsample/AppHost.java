package com.ojas.fragmentsample;

import android.app.Application;
import android.util.Log;

public class AppHost extends Application {

	private Thread.UncaughtExceptionHandler androidDefaultUEH;

	private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread thread, Throwable ex) {
			Log.e("TestApplication", "Uncaught exception is: ", ex);
			// log it & phone home.
			androidDefaultUEH.uncaughtException(thread, ex);
		}
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(handler);
	};

}
