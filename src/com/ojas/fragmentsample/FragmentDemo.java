package com.ojas.fragmentsample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ojas.fragmentsample.helper.FragmentsManager;

@SuppressLint("SimpleDateFormat")
public class FragmentDemo extends FragmentActivity implements OnClickListener {

	public static FragmentsManager mFragmentsManager;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_sample_layout);
		
		mFragmentsManager = new FragmentsManager(FragmentDemo.this, R.id.sample_fragment);
		
		((Button)findViewById(R.id.new_fragment)).setOnClickListener(this);
		
		((Button)findViewById(R.id.pop_all_fragments)).setOnClickListener(this);
		
		((Button)findViewById(R.id.pop_fragment)).setOnClickListener(this);
		
		((Button)findViewById(R.id.pop_upto_fragment)).setOnClickListener(this); 
		
	}
	
	@Override
	public void onClick(View v) {
		int iViewId = v.getId();
		switch (iViewId) {
		
		case R.id.new_fragment:
			mFragmentsManager.addFragment(new FragmentOne());
			break;
			
		case R.id.pop_fragment:
			mFragmentsManager.popFragment();
			break;
			
		case R.id.pop_all_fragments:
			mFragmentsManager.popAllFragments();
			break;
			
		case R.id.pop_upto_fragment:
			mFragmentsManager.popFragmentsUpto(FragmentOne.class.getName());
			break;
			
		default:
			break;
		}
	}

	
	@Override
	public void onBackPressed() {
		boolean isFragmentPopped = mFragmentsManager.popFragment();
		if (!isFragmentPopped) {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_sample, menu);
		return true;
	}
	
}
