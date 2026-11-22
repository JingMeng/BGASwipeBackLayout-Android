/*
 * Copyright 2016 bingoogolapple
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.bingoogolapple.swipebacklayout;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import android.view.View;

import java.util.List;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:17/1/4 涓嬪崍3:44
 * 鎻忚堪:婊戝姩杩斿洖甯姪绫? */
public class BGASwipeBackHelper {
    private Activity mActivity;
    private Delegate mDelegate;
    private BGASwipeBackLayout mSwipeBackLayout;

    /**
     * 蹇呴』鍦?Application 鐨?onCreate 鏂规硶涓皟鐢?     *
     * @param application          搴旂敤绋嬪簭涓婁笅鏂?     * @param isTranslucent        鏄惁浣跨敤閫忔槑涓婚妯″紡
     * @param problemViewClassList 浣跨敤闈為€忔槑涓婚鏃讹紝濡傛灉鍙戠幇婊戝姩杩斿洖鍚庣珛鍗宠Е鎽哥晫闈㈡椂搴旂敤宕╂簝锛?     *                             璇锋妸璇ョ晫闈㈤噷姣旇緝鐗规畩鐨?View 鐨?class 娣诲姞鍒拌闆嗗悎涓紝
     *                             鐩墠鍦ㄥ簱涓凡缁忔坊鍔犱簡 WebView 鍜?SurfaceView
     */
    public static void init(Application application, boolean isTranslucent, List<Class<? extends View>> problemViewClassList) {
        BGASwipeBackManager.getInstance().init(application, isTranslucent, problemViewClassList);
    }

    /**
     * @param activity
     * @param delegate
     */
    public BGASwipeBackHelper(Activity activity, Delegate delegate) {
        mActivity = activity;
        mDelegate = delegate;

        initSwipeBackFinish();
    }

    /**
     * 鍒濆鍖栨粦鍔ㄨ繑鍥?     */
    private void initSwipeBackFinish() {
        if (mDelegate.isSupportSwipeBack()) {
            mSwipeBackLayout = new BGASwipeBackLayout(mActivity);
            mSwipeBackLayout.attachToActivity(mActivity);
            mSwipeBackLayout.setPanelSlideListener(new BGASwipeBackLayout.PanelSlideListener() {
                @Override
                public void onPanelSlide(View panel, float slideOffset) {
                    if (slideOffset < 0.03) {
                        BGAKeyboardUtil.closeKeyboard(mActivity);
                    }

                    mDelegate.onSwipeBackLayoutSlide(slideOffset);
                }

                @Override
                public void onPanelOpened(View panel) {
                    mDelegate.onSwipeBackLayoutExecuted();
                }

                @Override
                public void onPanelClosed(View panel) {
                    mDelegate.onSwipeBackLayoutCancel();
                }
            });
        }
    }

    /**
     * 璁剧疆婊戝姩杩斿洖鏄惁鍙敤銆傞粯璁ゅ€间负 true
     *
     * @param swipeBackEnable
     * @return
     */
    public BGASwipeBackHelper setSwipeBackEnable(boolean swipeBackEnable) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setSwipeBackEnable(swipeBackEnable);
        }
        return this;
    }

    /**
     * 璁剧疆鏄惁浠呬粎璺熻釜宸︿晶杈圭紭鐨勬粦鍔ㄨ繑鍥炪€傞粯璁ゅ€间负 true
     *
     * @param isOnlyTrackingLeftEdge
     * @return
     */
    public BGASwipeBackHelper setIsOnlyTrackingLeftEdge(boolean isOnlyTrackingLeftEdge) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setIsOnlyTrackingLeftEdge(isOnlyTrackingLeftEdge);
        }
        return this;
    }

    /**
     * 璁剧疆鏄惁鏄井淇℃粦鍔ㄨ繑鍥炴牱寮忋€傞粯璁ゅ€间负 true
     *
     * @param isWeChatStyle
     * @return
     */
    public BGASwipeBackHelper setIsWeChatStyle(boolean isWeChatStyle) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setIsWeChatStyle(isWeChatStyle);
        }
        return this;
    }

    /**
     * 璁剧疆闃村奖璧勬簮 id銆傞粯璁ゅ€间负 R.drawable.bga_sbl_shadow
     *
     * @param shadowResId
     * @return
     */
    public BGASwipeBackHelper setShadowResId(@DrawableRes int shadowResId) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setShadowResId(shadowResId);
        }
        return this;
    }

    /**
     * 璁剧疆鏄惁鏄剧ず婊戝姩杩斿洖鐨勯槾褰辨晥鏋溿€傞粯璁ゅ€间负 true
     *
     * @param isNeedShowShadow
     * @return
     */
    public BGASwipeBackHelper setIsNeedShowShadow(boolean isNeedShowShadow) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setIsNeedShowShadow(isNeedShowShadow);
        }
        return this;
    }

    /**
     * 璁剧疆闃村奖鍖哄煙鐨勯€忔槑搴︽槸鍚︽牴鎹粦鍔ㄧ殑璺濈娓愬彉銆傞粯璁ゅ€间负 true
     *
     * @param isShadowAlphaGradient
     * @return
     */
    public BGASwipeBackHelper setIsShadowAlphaGradient(boolean isShadowAlphaGradient) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setIsShadowAlphaGradient(isShadowAlphaGradient);
        }
        return this;
    }

    /**
     * 璁剧疆瑙﹀彂閲婃斁鍚庤嚜鍔ㄦ粦鍔ㄨ繑鍥炵殑闃堝€硷紝榛樿鍊间负 0.3f
     *
     * @param threshold
     */
    public BGASwipeBackHelper setSwipeBackThreshold(@FloatRange(from = 0.0f, to = 1.0f) float threshold) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setSwipeBackThreshold(threshold);
        }
        return this;
    }

    /**
     * 璁剧疆搴曢儴瀵艰埅鏉℃槸鍚︽偓娴湪鍐呭涓?     *
     * @param overlap
     */
    public BGASwipeBackHelper setIsNavigationBarOverlap(boolean overlap) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setIsNavigationBarOverlap(overlap);
        }
        return this;
    }

    /**
     * 鏄惁姝ｅ湪婊戝姩
     *
     * @return
     */
    public boolean isSliding() {
        if (mSwipeBackLayout != null) {
            return mSwipeBackLayout.isSliding();
        }
        return false;
    }

    /**
     * 鎵ц璺宠浆鍒颁笅涓€涓?Activity 鐨勫姩鐢?     */
    public void executeForwardAnim() {
        executeForwardAnim(mActivity);
    }

    /**
     * 鎵ц鍥炲埌鍒颁笂涓€涓?Activity 鐨勫姩鐢?     */
    public void executeBackwardAnim() {
        executeBackwardAnim(mActivity);
    }

    /**
     * 鎵ц婊戝姩杩斿洖鍒板埌涓婁竴涓?Activity 鐨勫姩鐢?     */
    public void executeSwipeBackAnim() {
        executeSwipeBackAnim(mActivity);
    }

    /**
     * 鎵ц璺宠浆鍒颁笅涓€涓?Activity 鐨勫姩鐢汇€傝繖閲屽紕鎴愰潤鎬佹柟娉曪紝鏂逛究鍦?Fragment 涓皟鐢?     */
    public static void executeForwardAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.bga_sbl_activity_forward_enter, R.anim.bga_sbl_activity_forward_exit);
    }

    /**
     * 鎵ц鍥炲埌鍒颁笂涓€涓?Activity 鐨勫姩鐢汇€傝繖閲屽紕鎴愰潤鎬佹柟娉曪紝鏂逛究鍦?Fragment 涓皟鐢?     */
    public static void executeBackwardAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.bga_sbl_activity_backward_enter, R.anim.bga_sbl_activity_backward_exit);
    }

    /**
     * 鎵ц婊戝姩杩斿洖鍒板埌涓婁竴涓?Activity 鐨勫姩鐢汇€傝繖閲屽紕鎴愰潤鎬佹柟娉曪紝鏂逛究鍦?Fragment 涓皟鐢?     */
    public static void executeSwipeBackAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.bga_sbl_activity_swipeback_enter, R.anim.bga_sbl_activity_swipeback_exit);
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity锛屽苟涓旈攢姣佸綋鍓?Activity
     *
     * @param cls 涓嬩竴涓?Activity 鐨?Class
     */
    public void forwardAndFinish(Class<?> cls) {
        forward(cls);
        mActivity.finish();
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity锛屼笉閿€姣佸綋鍓?Activity
     *
     * @param cls 涓嬩竴涓?Activity 鐨?Class
     */
    public void forward(Class<?> cls) {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.startActivity(new Intent(mActivity, cls));
        executeForwardAnim();
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity锛屼笉閿€姣佸綋鍓?Activity
     *
     * @param cls         涓嬩竴涓?Activity 鐨?Class
     * @param requestCode 璇锋眰鐮?     */
    public void forward(Class<?> cls, int requestCode) {
        forward(new Intent(mActivity, cls), requestCode);
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity锛岄攢姣佸綋鍓?Activity
     *
     * @param intent 涓嬩竴涓?Activity 鐨勬剰鍥惧璞?     */
    public void forwardAndFinish(Intent intent) {
        forward(intent);
        mActivity.finish();
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity,涓嶉攢姣佸綋鍓?Activity
     *
     * @param intent 涓嬩竴涓?Activity 鐨勬剰鍥惧璞?     */
    public void forward(Intent intent) {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.startActivity(intent);
        executeForwardAnim();
    }

    /**
     * 璺宠浆鍒颁笅涓€涓?Activity,涓嶉攢姣佸綋鍓?Activity
     *
     * @param intent      涓嬩竴涓?Activity 鐨勬剰鍥惧璞?     * @param requestCode 璇锋眰鐮?     */
    public void forward(Intent intent, int requestCode) {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.startActivityForResult(intent, requestCode);
        executeForwardAnim();
    }

    /**
     * 鍥炲埌涓婁竴涓?Activity锛屽苟閿€姣佸綋鍓?Activity
     */
    public void backward() {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.finish();
        executeBackwardAnim();
    }

    /**
     * 婊戝姩杩斿洖涓婁竴涓?Activity锛屽苟閿€姣佸綋鍓?Activity
     */
    public void swipeBackward() {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.finish();
        executeSwipeBackAnim();
    }

    /**
     * 鍥炲埌涓婁竴涓?Activity锛屽苟閿€姣佸綋鍓?Activity锛堝簲鐢ㄥ満鏅細娆㈣繋銆佺櫥褰曘€佹敞鍐岃繖涓変釜鐣岄潰锛?     *
     * @param cls 涓婁竴涓?Activity 鐨?Class
     */
    public void backwardAndFinish(Class<?> cls) {
        BGAKeyboardUtil.closeKeyboard(mActivity);
        mActivity.startActivity(new Intent(mActivity, cls));
        mActivity.finish();
        executeBackwardAnim();
    }

    public interface Delegate {
        /**
         * 鏄惁鏀寔婊戝姩杩斿洖
         *
         * @return
         */
        boolean isSupportSwipeBack();

        /**
         * 姝ｅ湪婊戝姩杩斿洖
         *
         * @param slideOffset 浠?0 鍒?1
         */
        void onSwipeBackLayoutSlide(float slideOffset);

        /**
         * 娌¤揪鍒版粦鍔ㄨ繑鍥炵殑闃堝€硷紝鍙栨秷婊戝姩杩斿洖鍔ㄤ綔锛屽洖鍒伴粯璁ょ姸鎬?         */
        void onSwipeBackLayoutCancel();

        /**
         * 婊戝姩杩斿洖鎵ц瀹屾瘯锛岄攢姣佸綋鍓?Activity
         */
        void onSwipeBackLayoutExecuted();
    }
}

