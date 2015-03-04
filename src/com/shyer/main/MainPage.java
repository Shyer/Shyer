package com.shyer.main;

import java.util.ArrayList;

import com.shyer.fragment.MainFragmentStatePagerAdapter;
import com.shyer.fragment.RecordListFragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 
 * @author aohuijun
 *
 */
public class MainPage extends ActionBarActivity {
	
	private ArrayList<Fragment> fragmentsList;
	private RecordListFragment chatFragment, chatListFragment, matchFragment;
	private ImageView chatButton, chatListButton, connectButton, settingButton;
	private FragmentManager manager = getSupportFragmentManager();
	private MainFragmentStatePagerAdapter mainAdapter;
	private ViewPager mainViewPager;
	/*
	 * The drawer variables. 
	 */
	private ActionBarDrawerToggle drawerToggle;
	private DrawerLayout mainDrawerLayout;
	private ListView drawerList;
	private String[] testTitles;
	private ArrayAdapter<String> settingPageAdapter;
	private boolean isOpened = false;
	
	private long exitTime = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    	initActionBar();
    	initItems();
    	initFragments();
    	initSettingPage();
    	
    }
    
	private void initActionBar() {
    	Toolbar mainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
    	setSupportActionBar(mainToolbar);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.title_bar);
	}
    
	private void initItems() {
		chatButton = (ImageView)findViewById(R.id.chatBtn);
		chatListButton = (ImageView)findViewById(R.id.chatListBtn);
		connectButton = (ImageView)findViewById(R.id.matchBtn);
		settingButton = (ImageView)findViewById(R.id.settingBtn);
		chatButton.setOnClickListener(new FragmentChangeClickListener(0));
		chatListButton.setOnClickListener(new FragmentChangeClickListener(1));
		connectButton.setOnClickListener(new FragmentChangeClickListener(2));
		settingButton.setOnClickListener(new SettingButtonClickListener());
	}
	
	private void initFragments() {
		chatFragment = new RecordListFragment();
		chatListFragment = new RecordListFragment();
		matchFragment = new RecordListFragment();
		
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(chatFragment);
		fragmentsList.add(chatListFragment);
		fragmentsList.add(matchFragment);
		
		mainAdapter = new MainFragmentStatePagerAdapter(manager, fragmentsList);
		mainViewPager = (ViewPager)findViewById(R.id.viewPager);
		mainViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mainViewPager.setAdapter(mainAdapter);
	}
	
	private void initSettingPage() {
		testTitles = getResources().getStringArray(R.array.setting_list_array);
		settingPageAdapter = new ArrayAdapter<String>(this, R.layout.setting_item, testTitles);
    	mainDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
    	drawerList = (ListView)findViewById(R.id.setting_drawer);
    	drawerList.setAdapter(settingPageAdapter);
    	drawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, null, 0, 0) {
    		
    		@Override
    		public void onDrawerOpened(View drawerView) {
    			isOpened = true;
    			super.onDrawerOpened(drawerView);
    		}
    		
    		@Override
    		public void onDrawerClosed(View drawerView) {
    			isOpened = false;
    			super.onDrawerClosed(drawerView);
    		}
    	};
    	mainDrawerLayout.setDrawerListener(drawerToggle);
	}

	public class FragmentChangeClickListener implements OnClickListener {
		
		private int index = 0;
		public FragmentChangeClickListener(int i) {	index = i;	}
		@Override
		public void onClick(View v) {
			mainViewPager.setCurrentItem(index);
		}
		
	}
	
	public class SettingButtonClickListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			try {
				mainDrawerLayout.openDrawer(drawerList);
				isOpened = true;
			} catch (ClassCastException e) {
				throw new ClassCastException(this.toString() + "must implement SettingButtonClickListener. ");
			}
		}
	}
	
    public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
    		if (isOpened == true) {
				mainDrawerLayout.closeDrawer(drawerList);
				isOpened = false;
			}else if (isOpened == false) {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "请再按一次退出Shyer。", Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
				}
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
