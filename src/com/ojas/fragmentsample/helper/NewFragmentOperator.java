package com.ojas.fragmentsample.helper;

import java.util.Stack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ojas.fragmentsample.R;

@SuppressLint("Recycle")
public class NewFragmentOperator {
	
	private FragmentActivity mContext;
	private FragmentManager mFragmentManager;
	private static int iStackTop = 0;
	
	private Stack<Fragment> fragmentStack = new Stack<Fragment>();

	public NewFragmentOperator(Context context){
		mContext = (FragmentActivity) context;
		mFragmentManager = mContext.getSupportFragmentManager();
	}
	
	public void addFragment(Fragment mFragment){
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		
//		// first detach previous fragment
//		int iStackEntriesCount = mFragmentManager.getBackStackEntryCount();
//	    if ( iStackEntriesCount > 0){
//	        // Get the fragment fragment manager - and pop the back stack
//	    	FragmentManager.BackStackEntry frg_last = mFragmentManager.getBackStackEntryAt(iStackEntriesCount-1);
//	    	Fragment mFragmentToPop = mFragmentManager.findFragmentByTag(frg_last.getName());
//	    	fragmentStack.push(mFragmentToPop);
//	    }
		
		fragmentTransaction.addToBackStack(null)
        .replace( R.id.sample_fragment, mFragment, mFragment.getClass().getName())
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
		fragmentStack.push(mFragment);
		
		iStackTop++;
//		fragmentTransaction.addToBackStack(null)
//        .add( R.id.sample_fragment, mFragment, String.valueOf(iStackTop))
//        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).attach(mFragment).commit();
	}

	public void popFragment(){
	    // If the fragment exists and has some back-stack entry
		int iStackEntriesCount = mFragmentManager.getBackStackEntryCount();
	    if ( iStackEntriesCount > 0){
	        // Get the fragment fragment manager - and pop the back stack
	    	FragmentManager.BackStackEntry frg_last = mFragmentManager.getBackStackEntryAt(iStackEntriesCount-1);
	    	Fragment mFragmentToPop = mFragmentManager.findFragmentByTag(frg_last.getName());
	    	mFragmentManager.beginTransaction().remove(mFragmentToPop).commit();
	    	iStackTop--;
	    }
	}
	
	public void popFragmentFromStack(){
//		if (!fragmentStack.isEmpty() ) {
//			iStackTop--;
//			fragmentStack.pop();
//			if (!fragmentStack.isEmpty()) {
//				Fragment pickFragment = fragmentStack.peek();
//				mFragmentManager.beginTransaction().addToBackStack(null)
//				.replace( R.id.sample_fragment, pickFragment, pickFragment.getClass().getName())
//				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
//			} else {
//				mFragmentManager.popBackStack();
//			}
//		} else {
			mFragmentManager.popBackStack();
//		}
	}
	
	public void popAllFragmentsFromStack() {
		while (iStackTop > 0) {
			mFragmentManager.popBackStack();
			iStackTop--;
		}
	}
	
	public void popAllFragments(){
		int iStackEntriesCount = 0;
		while ( (iStackEntriesCount = mFragmentManager.getBackStackEntryCount()) > 0) {
//			mFragmentManager.beginTransaction().remove(mFragmentManager.findFragmentByTag(
//					mFragmentManager.getBackStackEntryAt(iStackEntriesCount-1).getName()));
			mFragmentManager.popBackStack(mFragmentManager.getBackStackEntryAt(iStackEntriesCount-1).getName(), 
	    			FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
	
	public void popFragmentsUptoFromStack(String strFragmentName) {
		boolean isTopCleared = false;
		while(!isTopCleared){
		    if ( iStackTop > 0){
		        // Get the fragment fragment manager - and pop the back stack
		    	String lastFragment = fragmentStack.peek().getClass().getName();
		    	System.out.println(" ==== "+lastFragment);
		    	if (lastFragment != null && strFragmentName.equalsIgnoreCase(lastFragment)) {
					isTopCleared = true;
					break;
				} else {
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
	
	public void popFragmentsUpto(String strFragmentName) {
		boolean isTopCleared = false;
		int iStackEntriesCount = mFragmentManager.getBackStackEntryCount();
		while(!isTopCleared){
		    if ( iStackEntriesCount > 0){
		        // Get the fragment fragment manager - and pop the back stack
		    	FragmentManager.BackStackEntry lastFagment = mFragmentManager.getBackStackEntryAt(iStackEntriesCount-1);
		    	System.out.println("Frgment to be at top == "+strFragmentName+"  last fragment == "+lastFagment.getName());
		    	if (strFragmentName.equalsIgnoreCase(lastFagment.getName())) {
					isTopCleared = true;
					break;
				} else {
					Fragment mFragmentToPop = mFragmentManager.findFragmentByTag(lastFagment.getName());
					mFragmentManager.beginTransaction().remove(mFragmentToPop);
					iStackEntriesCount--;
					isTopCleared = false;
				}
		    } else {
		    	isTopCleared = true;
		    }
		}
	}
	
}
