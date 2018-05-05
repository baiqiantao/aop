package aop.bqt.com.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc：演示拦截通过ButterKnife的注解添加的点击事件
 *
 * @author <a href="http://www.cnblogs.com/baiqiantao">白乾涛</a><p>
 * @tag <p>
 * @date 2018/5/4 14:35 <p>
 */
public class ButterKnifeActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
	
	@OnClick(R.id.tv)
	public void ClickTv() {
		Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
	}
}
