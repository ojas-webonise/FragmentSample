package com.ojas.fragmentsample;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppHost extends Application {

	private Thread.UncaughtExceptionHandler androidDefaultUEH;
	public PendingIntent intentRestart;
//	private Vibrator mVibrator;
//	private LayoutInflater inflater;
//	private View toastLayout;
//	private String strPrevMessage = "";
//	private long longPrevMessageTime;

	private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread thread, Throwable ex) {
			Log.e("TestApplication", "Uncaught exception is: ", ex);
			// log it & phone home.
			
            // here I do logging of exception to a db
            PendingIntent myActivity = PendingIntent.getActivity(getApplicationContext(),
                91188, new Intent(getApplicationContext(), FragmentDemo.class),
                PendingIntent.FLAG_ONE_SHOT);

            AlarmManager alarmManager;
            alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 15000, myActivity );
            System.exit(0);
            
			androidDefaultUEH.uncaughtException(thread, ex);
		}
	};
	
	public AppHost(){
		androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(handler);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
//		inflater = LayoutInflater.from(getApplicationContext());
//		toastLayout = inflater.inflate(R.layout.toast_layout, null);
	}
	
//	public void showToast(String strMessage) {
//	vibrate();
//	long now = System.currentTimeMillis();
//	if (strPrevMessage.equals(strMessage) && (now - longPrevMessageTime) > 10000) {
//		// avoid displaying same toast again and again
//	} else {
//		Toast toast = new Toast(getApplicationContext());
//		toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
//		toast.setDuration(3000);
//		((TextView)toastLayout.findViewById(R.id.textViewToastMessage)).setText(strMessage);
//		toast.setView(toastLayout);
//		toast.show();
//		strPrevMessage = strMessage;
//		longPrevMessageTime = System.currentTimeMillis();
//	}
//}

//public void vibrate() {
//	if (mVibrator == null) {
//		mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); 
//	}
//	mVibrator.vibrate(500);
//}
	
}
