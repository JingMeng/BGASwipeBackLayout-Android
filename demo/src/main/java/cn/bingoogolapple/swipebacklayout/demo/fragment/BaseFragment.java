package cn.bingoogolapple.swipebacklayout.demo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/27 涓嬪崍11:29
 * 鎻忚堪:
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected View mContentView;
    protected Activity mActivity;

    protected boolean mIsLoadedData = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = this.getClass().getSimpleName();
        mActivity = getActivity();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            handleOnVisibilityChangedToUser(isVisibleToUser);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(false);
        }
    }

    /**
     * 澶勭悊瀵圭敤鎴锋槸鍚﹀彲瑙?     *
     * @param isVisibleToUser
     */
    private void handleOnVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // 瀵圭敤鎴峰彲瑙?            if (!mIsLoadedData) {
                mIsLoadedData = true;
                onLazyLoadOnce();
            }
            onVisibleToUser();
        } else {
            // 瀵圭敤鎴蜂笉鍙
            onInvisibleToUser();
        }
    }

    /**
     * 鎳掑姞杞戒竴娆°€傚鏋滃彧鎯冲湪瀵圭敤鎴峰彲瑙佹椂鎵嶅姞杞芥暟鎹紝骞朵笖鍙姞杞戒竴娆℃暟鎹紝鍦ㄥ瓙绫讳腑閲嶅啓璇ユ柟娉?     */
    protected void onLazyLoadOnce() {
    }

    /**
     * 瀵圭敤鎴峰彲瑙佹椂瑙﹀彂璇ユ柟娉曘€傚鏋滃彧鎯冲湪瀵圭敤鎴峰彲瑙佹椂鎵嶅姞杞芥暟鎹紝鍦ㄥ瓙绫讳腑閲嶅啓璇ユ柟娉?     */
    protected void onVisibleToUser() {
    }

    /**
     * 瀵圭敤鎴蜂笉鍙鏃惰Е鍙戣鏂规硶
     */
    protected void onInvisibleToUser() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 閬垮厤澶氭浠巟ml涓姞杞藉竷灞€鏂囦欢
        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(mActivity).inflate(layoutResID, null);
    }

    /**
     * 鍒濆鍖朧iew鎺т欢
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
     * 鏌ユ壘View
     *
     * @param id   鎺т欢鐨刬d
     * @param <VT> View绫诲瀷
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }
}
