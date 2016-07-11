package com.shcx.sreader.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.shcx.sreader.R;
import com.shcx.sreader.fragment.MainFragment;
import com.shcx.sreader.fragment.MenuFragment;
import com.shcx.sreader.fragment.dummy.DummyContent;
import com.shcx.sreader.interf.OnListFragmentInteractionListener;

public class MainActivity extends SlidingFragmentActivity implements OnListFragmentInteractionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        Fragment mContent = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();

        // 设置滑动菜单视图界面
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, new MenuFragment()).commit();

        // 设置滑动菜单的属性值
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
        getSlidingMenu().setShadowDrawable(R.drawable.shadow);
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        getSlidingMenu().setFadeDegree(0.35f);

        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);//设置左右均可滑动出来
        getSlidingMenu().setSecondaryMenu(R.layout.second_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.second_frame, new MenuFragment()).commit();

        getSlidingMenu().setOnScrollLitener(new SlidingMenu.OnScrollLitener() {
            private int last = 0;
            @Override
            public void OnScroll(int dx) {
                if (dx>0 && last<=0) {
                    last = 1;
                    getSlidingMenu().setShadowWidth(0);
                    getSlidingMenu().setShadowDrawable(R.drawable.shadow);
                    getSlidingMenu().setBehindOffset(0);
//                    getSlidingMenu().setFadeDegree(0.35f);
                } else if(dx<0 && last>=0) {
                    last = -1;
                    getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
                    getSlidingMenu().setShadowDrawable(R.drawable.shadow);
                    getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
//                    getSlidingMenu().setFadeDegree(0.35f);
                } else {
//                    last = 0;
                }
//                Log.e("main","scroller");
//                ((HomeFragment)mContent).setTouchEnable(false);
            }
        });
    }


    @Override
    public void onListFragmentInteraction(com.shcx.sreader.bean.DummyContent.DummyItem item) {
        Toast.makeText(this,"dfsfdf",Toast.LENGTH_LONG).show();
    }
}
