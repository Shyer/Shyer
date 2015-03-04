package com.shyer.fragment;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * 
 * @author aohuijun
 *
 */
public class MainFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<Fragment> fragmentsList;	

	public MainFragmentStatePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
		super(fm);
		this.fragmentsList = fragmentsList;
	}
	
	@Override
	public Fragment getItem(int position) {
		return fragmentsList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentsList.size();
	}

}
