package com.ojas.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ojas.fragmentsample.helper.FragmentsManager;

public class FragmentOne extends Fragment {
	
	FragmentsManager mFragmentsManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ft = inflater.inflate(R.layout.fragment_one, null);
		this.mFragmentsManager = FragmentDemo.mFragmentsManager;
		((Button)ft.findViewById(R.id.nextFragment)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mFragmentsManager.addFragment(new FragmentTwo());
//				mFragmentOperator.addFragment(R.id.sample_fragment, FragmentTwo.class.getName(), "FragmentTwo");
			}
		});
		return ft;
	}
	
}
