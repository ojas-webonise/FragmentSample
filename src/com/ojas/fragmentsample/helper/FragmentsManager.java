package com.ojas.fragmentsample.helper;

import java.util.HashMap;
import java.util.Stack;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class FragmentsManager {
	
	private FragmentActivity mContext;
	private FragmentManager mFragmentManager;
	private static int iStackTop = 0;
	
	private static Stack<Fragment> fragmentStack = new Stack<Fragment>();
	private static HashMap<String, String> fragmentsMap = new HashMap<String, String>(); 
	
	private static String TAG = FragmentsManager.class.getName();
	private int iParentLayoutId;

	public FragmentsManager(Context context, int iParentLayoutId) {
		mContext = (FragmentActivity) context;
		mFragmentManager = mContext.getSupportFragmentManager();
		this.iParentLayoutId = iParentLayoutId;
	}
	
	public void addFragment(Fragment mFragment) {
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		
		String strFragmentName = mFragment.getClass().getName().toString();
		
		fragmentTransaction.addToBackStack(strFragmentName)
        .replace( iParentLayoutId, mFragment, strFragmentName)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        .commit();
		
		fragmentStack.push(mFragment);
		fragmentsMap.put(strFragmentName, strFragmentName);
		
		iStackTop++;
	}

	public void popFragment() {
		if (!fragmentStack.isEmpty()) {
			// Retrieve the last fragment name in stack
			String lastFragment = fragmentStack.peek().getClass().getName();
			fragmentsMap.remove(lastFragment);
			fragmentStack.pop();
			mFragmentManager.popBackStack();
		}
	}
	
	public void popAllFragments() {
		while (iStackTop > 0) {
			fragmentStack.pop();
			mFragmentManager.popBackStack();
			iStackTop--;
		}
		fragmentsMap.clear();
	}
	
	public void popFragmentsUpto(String strFragmentName) {
		if (isValidFragment(strFragmentName)) {
			boolean isTopCleared = false;
			while(!isTopCleared){
				if ( iStackTop > 0){
					// Retrieve the last fragment name in stack
					String lastFragment = fragmentStack.peek().getClass().getName();
					if (lastFragment != null && strFragmentName.equalsIgnoreCase(lastFragment)) {
						isTopCleared = true;
						break;
					} else {
						fragmentsMap.remove(lastFragment);
						fragmentStack.pop();
						mFragmentManager.popBackStack();
						iStackTop--;
						isTopCleared = false;
					}
				} else {
					isTopCleared = true;
				}
			}
		}
	}
	
	private boolean isValidFragment(String strFragmentName) {
		if (!fragmentsMap.isEmpty()) {
			if (fragmentsMap.containsKey(strFragmentName)) {
				return true;
			} else {
				Log.e(TAG, "Invalid fragment parameter. Fragment does't exists.");
			}
		} else {
			Log.e(TAG, "Fragments stack is empty. Unable to pop fragments.");
		}
		return false;
	}

	public void popPerticularFragment() {
		
	}
	
}
