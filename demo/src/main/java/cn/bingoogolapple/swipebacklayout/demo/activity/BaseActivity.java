package cn.bingoogolapple.swipebacklayout.demo.activity;

import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import android.support.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.bingoogolapple.swipebacklayout.demo.R;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/27 涓嬪崍5:35
 * 鎻忚堪:寮€鍙戣€呭彲灏嗚绫讳腑鐨勬煇浜涙柟娉曟嫹璐濆埌鑷繁鐨?BaseActivity 涓皝瑁呮垚閫傚悎鑷繁椤圭洰鐨勬粦鍔ㄨ繑鍥炲熀绫? * <p>
 * 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆? */
public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate, View.OnClickListener {
    protected BGASwipeBackHelper mSwipeBackHelper;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆?        // 鍦?super.onCreate(savedInstanceState) 涔嬪墠璋冪敤璇ユ柟娉?        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate");


        initView(savedInstanceState);
        mToolbar = getViewById(R.id.toolbar);

        setListener();
        processLogic(savedInstanceState);
    }

    /**
     * 鍒濆鍖栨粦鍔ㄨ繑鍥炪€傚湪 super.onCreate(savedInstanceState) 涔嬪墠璋冪敤璇ユ柟娉?     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆?        // 涓嬮潰鍑犻」鍙互涓嶉厤缃紝杩欓噷鍙槸涓轰簡璁茶堪鎺ュ彛鐢ㄦ硶銆?
        // 璁剧疆婊戝姩杩斿洖鏄惁鍙敤銆傞粯璁ゅ€间负 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 璁剧疆鏄惁浠呬粎璺熻釜宸︿晶杈圭紭鐨勬粦鍔ㄨ繑鍥炪€傞粯璁ゅ€间负 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 璁剧疆鏄惁鏄井淇℃粦鍔ㄨ繑鍥炴牱寮忋€傞粯璁ゅ€间负 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 璁剧疆闃村奖璧勬簮 id銆傞粯璁ゅ€间负 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 璁剧疆鏄惁鏄剧ず婊戝姩杩斿洖鐨勯槾褰辨晥鏋溿€傞粯璁ゅ€间负 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 璁剧疆闃村奖鍖哄煙鐨勯€忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉銆傞粯璁ゅ€间负 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 璁剧疆瑙﹀彂閲婃斁鍚庤嚜鍔ㄦ粦鍔ㄨ繑鍥炵殑闃堝€硷紝榛樿鍊间负 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 璁剧疆搴曢儴瀵艰埅鏉℃槸鍚︽偓娴湪鍐呭涓婏紝榛樿鍊间负 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 鏄惁鏀寔婊戝姩杩斿洖銆傝繖閲屽湪鐖剁被涓粯璁よ繑鍥?true 鏉ユ敮鎸佹粦鍔ㄨ繑鍥烇紝濡傛灉鏌愪釜鐣岄潰涓嶆兂鏀寔婊戝姩杩斿洖鍒欓噸鍐欒鏂规硶杩斿洖 false 鍗冲彲
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 姝ｅ湪婊戝姩杩斿洖
     *
     * @param slideOffset 浠?0 鍒?1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 娌¤揪鍒版粦鍔ㄨ繑鍥炵殑闃堝€硷紝鍙栨秷婊戝姩杩斿洖鍔ㄤ綔锛屽洖鍒伴粯璁ょ姸鎬?     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 婊戝姩杩斿洖鎵ц瀹屾瘯锛岄攢姣佸綋鍓?Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 姝ｅ湪婊戝姩杩斿洖鐨勬椂鍊欏彇娑堣繑鍥炴寜閽簨浠?        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    /**
     * 璁剧疆鐘舵€佹爮棰滆壊
     *
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 璁剧疆鐘舵€佹爮棰滆壊
     *
     * @param color
     * @param statusBarAlpha 閫忔槑搴?     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 鍒濆鍖栧竷灞€浠ュ強View鎺т欢
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 缁橵iew鎺т欢娣诲姞浜嬩欢鐩戝惉鍣?     */
    protected abstract void setListener();

    /**
     * 澶勭悊涓氬姟閫昏緫锛岀姸鎬佹仮澶嶇瓑鎿嶄綔
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 闇€瑕佸鐞嗙偣鍑讳簨浠舵椂锛岄噸鍐欒鏂规硶
     *
     * @param v
     */
    public void onClick(View v) {
    }

    /**
     * 鏌ユ壘View
     *
     * @param id   鎺т欢鐨刬d
     * @param <VT> View绫诲瀷
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getSimpleName(), "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this.getClass().getSimpleName(), "onStop");
    }
}

