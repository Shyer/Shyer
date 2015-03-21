package com.shyer.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shyer.main.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
/**
 * 
 * @author aohuijun
 *
 */
public class ContactListFragment extends ListFragment {
	
	private String[] testTitles = {
			"对话1", "对话2", "对话3", "对话4", "对话5"
			};
	private String[] testContents = {
			"I love Asian culture! Wanna meet some locals here! ", 
			"Anyone wanna hike this weekend? HKU to the Peak! Hit me up:)", 
			"Learning Japanese now. Want to meet some Japanese friends! Anyone who are from Japan or love Japan are welcome! ", 
			"Every Christmas I feel super lonely. Being fat makes me a loner forever.", 
			"I’m Sri Lankan. A guy covered his nose when he saw me! This has happened so many times in HK - an int’l city!!"
			};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactListView = inflater.inflate(R.layout.fragment_contact_list, container, false);
		return contactListView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		
		final String[] from = new String[]{"title","content"};
		final int[] to = new int[] {R.id.list_title, R.id.list_content};
		super.onActivityCreated(savedInstanceState);
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), getSimpleData(), R.layout.contact_list_item, from, to);
		this.setListAdapter(adapter);
		
	}

	private List<Map<String, Object>> getSimpleData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < testTitles.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", testTitles[i]);
			map.put("content", testContents[i]);
			list.add(map);
		}
		return list;
	}
	
	
}
