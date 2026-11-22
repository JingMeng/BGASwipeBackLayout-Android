package cn.bingoogolapple.swipebacklayout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.annotation.DrawableRes;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * 浣滆€?鐜嬫旦 閭欢:wanghao76@meituan.com
 * 鍒涘缓鏃堕棿:2017/10/13
 * 鎻忚堪:宸︿晶闃村奖鎺т欢
 */
class BGASwipeBackShadowView extends FrameLayout {
    private static final String TAG = BGASwipeBackShadowView.class.getSimpleName();
    private Activity mActivity;
    private WeakReference<Activity> mPreActivity;
    private ViewGroup mPreDecorView;
    private View mPreContentView;
    private ImageView mPreImageView;
    private View mShadowView;

    /**
     * 鏄惁鏄剧ず婊戝姩杩斿洖鐨勯槾褰辨晥鏋?     */
    private boolean mIsNeedShowShadow = true;
    /**
     * 闃村奖璧勬簮 id
     */
    private int mShadowResId = R.drawable.bga_sbl_shadow;
    /**
     * 闃村奖鍖哄煙鐨勯€忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉
     */
    private boolean mIsShadowAlphaGradient = true;
    /**
     * 鏄惁鏄井淇℃粦鍔ㄨ繑鍥炴牱寮?     */
    private boolean mIsWeChatStyle = true;

    BGASwipeBackShadowView(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    /**
     * 璁剧疆鏄惁鏄剧ず婊戝姩杩斿洖鐨勯槾褰辨晥鏋溿€傞粯璁ゅ€间负 true
     *
     * @param isNeedShowShadow
     */
    void setIsNeedShowShadow(boolean isNeedShowShadow) {
        mIsNeedShowShadow = isNeedShowShadow;
        updateShadow();
    }

    /**
     * 璁剧疆闃村奖璧勬簮 id銆傞粯璁ゅ€间负 R.drawable.bga_sbl_shadow
     *
     * @param shadowResId
     */
    void setShadowResId(@DrawableRes int shadowResId) {
        mShadowResId = shadowResId;
        updateShadow();
    }

    /**
     * 璁剧疆闃村奖鍖哄煙鐨勯€忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉銆傞粯璁ゅ€间负 true
     *
     * @param isShadowAlphaGradient
     */
    void setIsShadowAlphaGradient(boolean isShadowAlphaGradient) {
        mIsShadowAlphaGradient = isShadowAlphaGradient;
    }

    /**
     * 璁剧疆鏄惁鏄井淇℃粦鍔ㄨ繑鍥炴牱寮忋€傞粯璁ゅ€间负 true
     */
    void setIsWeChatStyle(boolean isWeChatStyle) {
        mIsWeChatStyle = isWeChatStyle;
    }

    private void updateShadow() {
        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            if (mIsNeedShowShadow) {
                setBackgroundResource(mShadowResId);
            } else {
                setBackgroundResource(android.R.color.transparent);
            }
        } else {
            if (mIsNeedShowShadow) {
                if (mShadowView == null) {
                    mShadowView = new View(getContext());
                    addView(mShadowView, getChildCount(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                }
                mShadowView.setBackgroundResource(mShadowResId);
            } else {
                if (mShadowView != null) {
                    removeView(mShadowView);
                }
            }
        }
    }

    void bindPreActivity() {
        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            return;
        }

        if (mPreActivity == null) {
            Activity preActivity = BGASwipeBackManager.getInstance().getPenultimateActivity(mActivity);
            if (preActivity != null) {
                mPreActivity = new WeakReference<>(preActivity);
                mPreDecorView = (ViewGroup) preActivity.getWindow().getDecorView();
                mPreContentView = mPreDecorView.getChildAt(0);
                mPreDecorView.removeView(mPreContentView);
                addView(mPreContentView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
        }
    }

    void unBindPreActivity(boolean isNeedAddImageView) {
        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            return;
        }

        if (mPreActivity == null) {
            return;
        }

        Activity activity = mPreActivity.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (mPreDecorView != null && mPreContentView != null) {
            if (isNeedAddImageView && mPreImageView == null && containsProblemView((ViewGroup) mPreContentView)) {
                mPreImageView = new ImageView(mActivity);
                mPreImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                mPreImageView.setImageBitmap(getBitmap(mPreContentView));
                addView(mPreImageView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }

            removeView(mPreContentView);
            mPreDecorView.addView(mPreContentView, 0);

            mPreContentView = null;
            mPreActivity.clear();
            mPreActivity = null;
        }
    }

    @Override
    protected void dispatchDraw(final Canvas canvas) {
        super.dispatchDraw(canvas);
        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            return;
        }

        if (mPreImageView != null) {
            return;
        }

        // 婊戝姩杩斿洖缁撴潫鍚庣殑鏈€鍚庝竴甯ч€氳繃 draw 鏉ュ疄鐜帮紝閬垮厤鎶栧姩
        if (mPreContentView == null && mPreDecorView != null) {
            mPreDecorView.draw(canvas);
        }
    }

    private Bitmap getBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 0,
                UIUtil.getRealScreenWidth(mActivity),
                UIUtil.getRealScreenHeight(mActivity) - UIUtil.getNavigationBarHeight(mActivity));
        view.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 璇?ViewGroup 涓槸鍚﹀寘鍚鑷村娆?draw 鍚庡簲鐢ㄥ穿婧冪殑 View
     *
     * @param viewGroup
     * @return
     */
    private boolean containsProblemView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        View childView;
        for (int i = 0; i < childCount; i++) {
            childView = viewGroup.getChildAt(i);
            if (BGASwipeBackManager.getInstance().isProblemView(childView)) {
                return true;
            } else if (childView instanceof ViewGroup) {
                if (containsProblemView((ViewGroup) childView)) {
                    return true;
                }
            }
        }
        return false;
    }

    void setShadowAlpha(float alpha) {
        if (mIsNeedShowShadow && mIsShadowAlphaGradient) {
            if (BGASwipeBackManager.getInstance().isTranslucent()) {
                ViewCompat.setAlpha(this, alpha);
            } else if (mShadowView != null) {
                ViewCompat.setAlpha(mShadowView, alpha);
            }
        }
    }

    void onPanelSlide(float slideOffset) {
        if (!mIsWeChatStyle) {
            return;
        }

        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            onPanelSlide(mActivity, slideOffset);
        } else if (mPreContentView != null) {
            ViewCompat.setTranslationX(mPreContentView, (mPreContentView.getMeasuredWidth() / 3.0f) * (1 - slideOffset));
        }
    }

    private void onPanelSlide(Activity currentActivity, float slideOffset) {
        try {
            Activity preActivity = BGASwipeBackManager.getInstance().getPenultimateActivity(currentActivity);
            if (preActivity != null) {
                View decorView = preActivity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, -(decorView.getMeasuredWidth() / 3.0f) * (1 - slideOffset));
            }
        } catch (Exception e) {
        }
    }

    void onPanelClosed() {
        if (!mIsWeChatStyle) {
            return;
        }

        if (BGASwipeBackManager.getInstance().isTranslucent()) {
            onPanelClosed(mActivity);
        } else if (mPreContentView != null) {
            ViewCompat.setTranslationX(mPreContentView, 0);
        }
        unBindPreActivity(false);
    }

    private void onPanelClosed(Activity currentActivity) {
        try {
            Activity preActivity = BGASwipeBackManager.getInstance().getPenultimateActivity(currentActivity);
            if (preActivity != null) {
                View decorView = preActivity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, 0);
            }
        } catch (Exception e) {
        }
    }
}

