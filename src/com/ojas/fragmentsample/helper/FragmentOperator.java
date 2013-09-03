package com.ojas.fragmentsample.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentOperator {
	
	
	private FragmentActivity mContext;

	public FragmentOperator(Context context){
		mContext = (FragmentActivity) context;
	}
	
	public Fragment addFragment(int viewId, String klass, String tag) {
		FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
		Fragment fragment = Fragment.instantiate(mContext, klass);
		ft.add(viewId, fragment, tag);
		addFragToBackStack(fragment);
		ft.commit();
		return fragment;
	}
	
	public void attachFragment(Fragment fragment) {
		FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
		ft.attach(fragment);
		ft.commit();
	}

	public void popFragmentFromStack() {
		int iFragCnt = mContext.getSupportFragmentManager().getBackStackEntryCount();
		if ( iFragCnt > 0 ) {
			
			// removing current fragment
			FragmentManager.BackStackEntry frg_last = mContext.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
			Fragment lastFragment = mContext.getSupportFragmentManager().findFragmentByTag(frg_last.getName());
			mContext.getSupportFragmentManager().popBackStackImmediate();
			
			// now attaching the previous fragment in the stack
			FragmentManager.BackStackEntry frg = mContext.getSupportFragmentManager().getBackStackEntryAt(iFragCnt - 1);
			Fragment currFrag = mContext.getSupportFragmentManager().findFragmentByTag(frg.getName());
			FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
			attachFragment(currFrag);
			ft.detach(lastFragment);
			ft.commit();
		}
	}
	  
	// Add fragments to backstack
	public void addFragToBackStack(Fragment fragment) {
		FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
		ft.addToBackStack(fragment.getTag());
		ft.commit();
	}
}
