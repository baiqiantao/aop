package aop.bqt.com.mylibrary2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Desc：演示拦截最底层library中的点击事件
 *
 * @author <a href="http://www.cnblogs.com/baiqiantao">白乾涛</a><p>
 * @tag <p>
 * @date 2018/5/4 01:10 <p>
 */
public class LibrartActivity2 extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"这是LibrartActivity2",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				Toast.makeText(this, "LibrartActivity2", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				
				break;
		}
	}
}