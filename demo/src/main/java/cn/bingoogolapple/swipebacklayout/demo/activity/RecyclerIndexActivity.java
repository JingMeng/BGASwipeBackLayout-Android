package cn.bingoogolapple.swipebacklayout.demo.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewScrollHelper;
import cn.bingoogolapple.swipebacklayout.demo.R;
import cn.bingoogolapple.swipebacklayout.demo.adapter.RecyclerIndexAdapter;
import cn.bingoogolapple.swipebacklayout.demo.util.DataUtil;
import cn.bingoogolapple.swipebacklayout.demo.widget.IndexView;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:17/1/11 涓嬪崍5:08
 * 鎻忚堪:娴嬭瘯涓?RecyclerView 涓€璧蜂娇鐢? * <p>
 * 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆? */
public class RecyclerIndexActivity extends BaseActivity implements BGAOnRVItemClickListener {
    private RecyclerIndexAdapter mAdapter;
    private RecyclerView mDataRv;
    private LinearLayoutManager mLayoutManager;
    private IndexView mIndexView;
    private TextView mTipTv;
    private BGARecyclerViewScrollHelper mRecyclerViewScrollHelper;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler_index);
        mDataRv = getViewById(R.id.rv_recyclerindexview_data);

        mIndexView = getViewById(R.id.indexview);
        mTipTv = getViewById(R.id.tv_recyclerindexview_tip);
    }

    @Override
    protected void setListener() {
        mAdapter = new RecyclerIndexAdapter(mDataRv);
        mAdapter.setOnRVItemClickListener(this);

        mIndexView.setDelegate(new IndexView.Delegate() {
            @Override
            public void onIndexViewSelectedChanged(IndexView indexView, String text) {
                int position = mAdapter.getPositionForCategory(text.charAt(0));
                if (position != -1) {
                    mRecyclerViewScrollHelper.smoothScrollToPosition(position);
                }
            }
        });
        mRecyclerViewScrollHelper = BGARecyclerViewScrollHelper.newInstance(mDataRv);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();

        mIndexView.setTipTv(mTipTv);

        mDataRv.addItemDecoration(BGADivider.newBitmapDivider()
                .setStartSkipCount(0)
                .setMarginLeftResource(R.dimen.size_level3)
                .setMarginRightResource(R.dimen.size_level9)
                .setDelegate(new BGADivider.SuspensionCategoryDelegate() {
                    @Override
                    protected boolean isCategory(int position) {
                        return mAdapter.isCategory(position);
                    }

                    @Override
                    protected String getCategoryName(int position) {
                        return mAdapter.getItem(position).topc;
                    }

                    @Override
                    protected int getFirstVisibleItemPosition() {
                        return mLayoutManager.findFirstVisibleItemPosition();
                    }
                }));

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDataRv.setLayoutManager(mLayoutManager);

        mAdapter.setData(DataUtil.loadIndexModelData());
        mDataRv.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("娴嬭瘯 RecyclerView");
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Toast.makeText(this, "閫夋嫨浜嗗煄甯?" + mAdapter.getItem(position).name, Toast.LENGTH_SHORT).show();
    }
}

