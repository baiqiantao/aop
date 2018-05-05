package aop.bqt.com.mylibrary;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import aop.bqt.com.mylibrary2.LibrartActivity2;

/**
 * Desc：演示拦截library中的点击事件
 *
 * @author <a href="http://www.cnblogs.com/baiqiantao">白乾涛</a><p>
 * @tag <p>
 * @date 2018/5/4 01:10 <p>
 */
public class LibrartActivity extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"这是LibrartActivity，点击调到LibrartActivity2",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this, LibrartActivity2.class));
				break;
			case 1:
				break;
		}
	}
}