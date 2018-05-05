package aop.bqt.com.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import aop.bqt.com.mylibrary.LibrartActivity;
import aop.bqt.com.mylibrary2.LibrartActivity2;

public class MainActivity extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView view = new TextView(this);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("bqt", "【");
			}
		});
		view.setText("\n拦截onClick事件\n");
		view.setTextSize(16);
		getListView().addFooterView(view);
		String[] array = {"这是MainActivity，点击调到LibrartActivity",
				"这是MainActivity，点击调到LibrartActivity2",
				"这是MainActivity，点击调到ButterKnifeActivity",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this, LibrartActivity.class));
				break;
			case 1:
				startActivity(new Intent(this, LibrartActivity2.class));
				break;
			case 2:
				startActivity(new Intent(this, ButterKnifeActivity.class));
				break;
		}
	}
}