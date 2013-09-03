package com.ojas.fragmentsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ojas.fragmentsample.helper.FragmentsManager;

public class FragmentTwo extends Fragment {
	
	FragmentsManager mFragmentsManager;
	private static String TAG = "FragmentTwo";
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, " >>> Fragment lifecycle started...");
		Log.i(TAG, " >>> onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, " >>> onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, " >>> onCreateView");
		View ft = inflater.inflate(R.layout.fragment_two, null);
		this.mFragmentsManager = FragmentDemo.mFragmentsManager;
		((Button)ft.findViewById(R.id.nextbutton)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mFragmentsManager.addFragment(new FragmentThree());
			}
		});
		return ft;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, " >>> onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, " >>> onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, " >>> onResume");
		Log.i(TAG, " >>> Now Fragment is active...");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, " >>> onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, " >>> onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, " >>> onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, " >>> onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, " >>> onDetach");
		Log.i(TAG, " >>> Now Fragment is Destroyed...");
	}
	
}
