package cn.bingoogolapple.swipebacklayout.demo.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.swipebacklayout.demo.R;
import cn.bingoogolapple.swipebacklayout.demo.adapter.SwipeDeleteAdapter;
import cn.bingoogolapple.swipebacklayout.demo.util.DataUtil;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:17/1/11 涓婂崍2:46
 * 鎻忚堪:娴嬭瘯涓庢粦鍔ㄥ垹闄や竴璧蜂娇鐢? * <p>
 * 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆? */
public class SwipeDeleteActivity extends BaseActivity implements BGAOnRVItemClickListener, BGAOnRVItemLongClickListener, BGAOnItemChildClickListener, BGAOnItemChildLongClickListener {
    private SwipeDeleteAdapter mAdapter;
    private RecyclerView mDataRv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_swipe_delete);
        mDataRv = getViewById(R.id.rv_swipe_delete_data);
    }

    @Override
    protected void setListener() {
        mAdapter = new SwipeDeleteAdapter(mDataRv);
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);

        mDataRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    mAdapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();

        mAdapter.setData(DataUtil.loadNormalModelDatas());
        mDataRv.addItemDecoration(BGADivider.newBitmapDivider());
        mDataRv.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("娴嬭瘯婊戝姩鍒犻櫎");
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_bgaswipe_delete) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            mAdapter.removeItem(position);
        }
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_bgaswipe_delete) {
            Toast.makeText(this, "闀挎寜浜嗗垹闄?" + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Toast.makeText(this, "鐐瑰嚮浜嗘潯鐩?" + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
        Toast.makeText(this, "闀挎寜浜嗘潯鐩?" + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
        return true;
    }
}

