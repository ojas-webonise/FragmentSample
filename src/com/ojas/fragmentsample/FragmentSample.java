package com.ojas.fragmentsample;

import com.ojas.fragmentsample.helper.FragmentOperator;
import com.ojas.fragmentsample.helper.NewFragmentOperator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class FragmentSample extends FragmentActivity {

	public static FragmentOperator mFragmentOperator;
	public static NewFragmentOperator mNewFragmentOperator;

//	private boolean isFromPop = false;
//	public TabManager mTabManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_sample_layout);
		mFragmentOperator = new FragmentOperator(FragmentSample.this);
		mNewFragmentOperator = new NewFragmentOperator(FragmentSample.this);
		((Button)findViewById(R.id.new_fragment)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				//FragmentOne.instantiate(FragmentSample.this, FragmentOne.class.getName())
				mNewFragmentOperator.addFragment(new FragmentOne());
//				mFragmentOperator.addFragment(R.id.sample_fragment, FragmentOne.class.getName(), "FragmentOne");
//				attachFragment(new FragmentOne());
			}
		});
		
		((Button)findViewById(R.id.pop_all_fragments)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mNewFragmentOperator.popAllFragmentsFromStack();
			}
		});
		
		((Button)findViewById(R.id.pop_fragment)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mNewFragmentOperator.popFragmentFromStack();
			}
		});
		
		((Button)findViewById(R.id.pop_upto_fragment)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mNewFragmentOperator.popFragmentsUptoFromStack(FragmentTwo.class.getName());
//				mNewFragmentOperator.popFragmentsUpto(FragmentOne.class.getName());
			}
		});
	}

	public Fragment addFragment(int viewId, String klass, String tag) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
//		ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

//		if ( mTabManager.mLastTab.fragment != null ) {
//			Fragment oldFragment = mTabManager.mLastTab.fragment;
//			ft.detach(oldFragment);
//		}

		Fragment fragment = Fragment.instantiate(this, klass);
		ft.add(viewId, fragment, tag);
		addFragToBackStack(fragment);
//		mTabManager.mLastTab.fragment = fragment;
		ft.commit();

		return fragment;
	}
	
	public void attachFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
//		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

//		if ( mTabManager.mLastTab.fragment != null ) {
//			Fragment oldFragment = mTabManager.mLastTab.fragment;
//			ft.detach(oldFragment);
//		}

		ft.attach(fragment);
//		mTabManager.mLastTab.fragment = fragment;
		ft.commit();
	}
	
	public void popFragmentFromStack() {

		int iFragCnt = this.getSupportFragmentManager().getBackStackEntryCount();

		if ( iFragCnt > 0 ) {
			FragmentManager.BackStackEntry frg_last = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);

			Fragment lastFragment = this.getSupportFragmentManager().findFragmentByTag(frg_last.getName());
			this.getSupportFragmentManager().popBackStackImmediate();
			iFragCnt = this.getSupportFragmentManager().getBackStackEntryCount();
//			if ( iFragCnt == 0 ) {
//				isFromPop=true;
//				this.mTabManager.onTabChanged(this.mTabHost.getCurrentTabTag());
//			} else {
				FragmentManager.BackStackEntry frg = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
				Fragment currFrag = this.getSupportFragmentManager().findFragmentByTag(frg.getName());
				FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
				this.attachFragment(currFrag);
				ft.detach(lastFragment);
				ft.commit();
//			}
		}
	}
	  
	// Add fragments to backstack
	public void addFragToBackStack(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.addToBackStack(fragment.getTag());
		ft.commit();
	}
	
	@Override
	public void onBackPressed() {
//		super.onBackPressed();
		mNewFragmentOperator.popFragmentFromStack();
//		mFragmentOperator.popFragmentFromStack();
	}

	/*
	public Fragment addFragment(int viewId, String klass, String tag, Bundle bundle) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
//		ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

		if ( mTabManager.mLastTab.fragment != null ) {
			Fragment oldFragment = mTabManager.mLastTab.fragment;
			ft.detach(oldFragment);
		}

		Fragment fragment = Fragment.instantiate(this, klass, bundle);
		ft.add(viewId, fragment, tag);
		mTabManager.mLastTab.fragment = fragment;
		addFragToBackStack(fragment);
		ft.commit();

		return fragment;
	}

	public void attachFragment(Fragment fragment, String tag) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
//		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

		if ( mTabManager.mLastTab.fragment != null ) {
			Fragment oldFragment = mTabManager.mLastTab.fragment;
			ft.detach(oldFragment);
		}

		ft.attach(fragment);
		addFragToBackStack(fragment);
		mTabManager.mLastTab.fragment = fragment;
		ft.commit();
	}

	public void popFragmentFromStack() {

		int iFragCnt = this.getFragmentManager().getBackStackEntryCount();

		if ( iFragCnt > 0 ) {
			FragmentManager.BackStackEntry frg_last = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);

			Fragment lastFragment = this.getSupportFragmentManager().findFragmentByTag(frg_last.getName());
			this.getFragmentManager().popBackStackImmediate();
			iFragCnt = this.getFragmentManager().getBackStackEntryCount();
			if ( iFragCnt == 0 ) {
				isFromPop=true;
				this.mTabManager.onTabChanged(this.mTabHost.getCurrentTabTag());
			} else {
				FragmentManager.BackStackEntry frg = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
				Fragment currFrag = this.getSupportFragmentManager().findFragmentByTag(frg.getName());
				FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
				this.attachFragment(currFrag);
				ft.detach(lastFragment);
				ft.commit();
			}
		}
	}

	public Fragment getLastFragment() {
		return mTabManager.mLastTab.fragment;
	}

	public void setLastFragment(Fragment f) {
		mTabManager.mLastTab.fragment = f;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ( keyCode == KeyEvent.KEYCODE_BACK ) {
			int iFragCnt = this.getSupportFragmentManager().getBackStackEntryCount();
			if ( iFragCnt > 0 ) {
				FragmentManager.BackStackEntry frg_last = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
				Fragment lastFragment = this.getSupportFragmentManager().findFragmentByTag(frg_last.getName());
				this.getSupportFragmentManager().popBackStackImmediate();
				iFragCnt = this.getSupportFragmentManager().getBackStackEntryCount();

				if ( iFragCnt == 0 ) {
					isFromPop=true;
					this.mTabManager.onTabChanged(this.mTabHost.getCurrentTabTag());
				} else {
					FragmentManager.BackStackEntry frg = this.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
					Fragment currFrag = this.getSupportFragmentManager().findFragmentByTag(frg.getName());
					FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
					this.attachFragment(currFrag);
					ft.detach(lastFragment);
					ft.commit();
				}
			} else {
				ShowMessageBox();
			}

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}*/
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_sample, menu);
		return true;
	}
	
}
