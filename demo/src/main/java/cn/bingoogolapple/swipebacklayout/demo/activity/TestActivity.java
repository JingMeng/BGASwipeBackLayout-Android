package cn.bingoogolapple.swipebacklayout.demo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import cn.bingoogolapple.swipebacklayout.demo.R;
import cn.bingoogolapple.swipebacklayout.demo.fragment.ContentFragment;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/27 涓嬪崍5:35
 * 鎻忚堪:娴嬭瘯婊戝姩杩斿洖甯冨眬鐨勬帴鍙? * <p>
 * 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆? */
public class TestActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SwitchCompat mSwipeBackEnableSwitch;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        mToolbar = getViewById(R.id.toolbar);
        mViewPager = getViewById(R.id.viewPager);
        mSwipeBackEnableSwitch = getViewById(R.id.swipeBackEnableSwitch);
    }

    @Override
    protected void setListener() {
        testSwipeBackLayout();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();
        setUpTabLayoutAndViewPager();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("娴嬭瘯婊戝姩杩斿洖甯冨眬鐨勬帴鍙?);
    }

    private void setUpTabLayoutAndViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeTopBgColor(position);
            }
        });
        mViewPager.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 娴嬭瘯鍙湁ViewPager鍦ㄧ0椤垫椂鎵嶅紑鍚粦鍔ㄨ繑鍥?                mSwipeBackHelper.setSwipeBackEnable(position == 0);
                mSwipeBackEnableSwitch.setChecked(position == 0);
            }
        });

        mTabLayout = getViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        changeTopBgColor(mViewPager.getCurrentItem());
    }

    /**
     * 娴嬭瘯婊戝姩杩斿洖鐩稿叧鎺ュ彛
     */
    private void testSwipeBackLayout() {
        // 娴嬭瘯鍔ㄦ€佽缃粦鍔ㄨ繑鍥炴槸鍚﹀彲鐢?        mSwipeBackEnableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean swipeBackEnable) {
                /**
                 * 璁剧疆婊戝姩杩斿洖鏄惁鍙敤
                 */
                mSwipeBackHelper.setSwipeBackEnable(swipeBackEnable);
            }
        });

        // 娴嬭瘯鍔ㄦ€佽缃槸鍚︿粎浠呰窡韪乏渚ц竟缂樼殑婊戝姩杩斿洖
        ((SwitchCompat) getViewById(R.id.onlyTrackingLeftEdgeSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isOnlyTrackingLeftEdge) {
                /**
                 * 璁剧疆鏄惁浠呬粎璺熻釜宸︿晶杈圭紭鐨勬粦鍔ㄨ繑鍥?                 */
                mSwipeBackHelper.setIsOnlyTrackingLeftEdge(isOnlyTrackingLeftEdge);
            }
        });

        // 娴嬭瘯鍔ㄦ€佽缃槸鍚︽槸寰俊婊戝姩杩斿洖鏍峰紡
        ((SwitchCompat) getViewById(R.id.weChatStyleSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isWeChatStyle) {
                /**
                 * 璁剧疆鏄惁鏄井淇℃粦鍔ㄨ繑鍥炴牱寮?                 */
                mSwipeBackHelper.setIsWeChatStyle(isWeChatStyle);
            }
        });

        // 娴嬭瘯鍔ㄦ€佽缃槸鍚︽樉绀烘粦鍔ㄨ繑鍥炵殑闃村奖鏁堟灉
        ((SwitchCompat) getViewById(R.id.needShowShadowSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isNeedShowShadow) {
                /**
                 * 璁剧疆鏄惁鏄剧ず婊戝姩杩斿洖鐨勯槾褰辨晥鏋?                 */
                mSwipeBackHelper.setIsNeedShowShadow(isNeedShowShadow);
            }
        });

        // 娴嬭瘯鍔ㄦ€佽缃槾褰卞尯鍩熺殑閫忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉
        ((SwitchCompat) getViewById(R.id.shadowAlphaGradientSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isShadowAlphaGradient) {
                /**
                 * 璁剧疆闃村奖鍖哄煙鐨勯€忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉
                 */
                mSwipeBackHelper.setIsShadowAlphaGradient(isShadowAlphaGradient);
            }
        });
    }

    public void testTranslucent(View v) {
        mSwipeBackHelper.forward(TranslucentActivity.class);

//        mSwipeBackHelper.forwardAndFinish(TranslucentActivity.class);
    }

    public void testPullRefreshAndWebView(View v) {
        mSwipeBackHelper.forward(WebViewActivity.class);
    }

    public void testSwipeDelete(View v) {
        mSwipeBackHelper.forward(SwipeDeleteActivity.class);
    }

    public void testRecyclerView(View v) {
        mSwipeBackHelper.forward(RecyclerIndexActivity.class);
    }

    /**
     * 娴嬭瘯 ViewPager 鐨勯€傞厤鍣?     */
    private class ContentPagerAdapter extends FragmentPagerAdapter {
        private ContentFragment mOneFragment;
        private ContentFragment mTwoFragment;
        private ContentFragment mThreeFragment;

        public ContentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mOneFragment == null) {
                    mOneFragment = ContentFragment.newInstance(position);
                }
                return mOneFragment;
            } else if (position == 1) {
                if (mTwoFragment == null) {
                    mTwoFragment = ContentFragment.newInstance(position);
                }
                return mTwoFragment;
            } else {
                if (mThreeFragment == null) {
                    mThreeFragment = ContentFragment.newInstance(position);
                }
                return mThreeFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "绗? + (position + 1) + "椤?;
        }
    }

    /**
     * 鏍规嵁Palette鎻愬彇鐨勯鑹诧紝淇敼tab鍜宼oolbar浠ュ強鐘舵€佹爮鐨勯鑹?     */
    private void changeTopBgColor(int position) {
        int imageResource = R.mipmap.one;
        if (position == 1) {
            imageResource = R.mipmap.two;
        } else if (position == 2) {
            imageResource = R.mipmap.three;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                mTabLayout.setBackgroundColor(vibrant.getRgb());
                mTabLayout.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                mTabLayout.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                mToolbar.setBackgroundColor(vibrant.getRgb());

                setStatusBarColor(colorBurn(vibrant.getRgb()));
            }
        });
    }

    /**
     * 棰滆壊鍔犳繁澶勭悊
     */
    private int colorBurn(int RGBValues) {
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}

