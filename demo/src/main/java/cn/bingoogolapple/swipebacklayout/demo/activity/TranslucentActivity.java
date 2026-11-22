package cn.bingoogolapple.swipebacklayout.demo.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.swipebacklayout.demo.R;
import cn.bingoogolapple.swipebacklayout.demo.adapter.ContentAdapter;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/28 涓婂崍1:06
 * 鎻忚堪:Test Translucent StatusBar
 * <p>
 * 銆屽繀椤诲湪 Application 鐨?onCreate 鏂规硶涓墽琛?BGASwipeBackHelper.init 鏉ュ垵濮嬪寲婊戝姩杩斿洖銆? */
public class TranslucentActivity extends BaseActivity {
    private ContentAdapter mContentAdapter;
    private RecyclerView mContentRv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_translucent);
        mToolbar = getViewById(R.id.toolbar);
        mContentRv = getViewById(R.id.recyclerView);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();
        initRecyclerView();
        StatusBarUtil.setTranslucentForImageView(this, 0, findViewById(R.id.ll_translucent_content));
    }

    private void initToolbar() {
        mToolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test Translucent StatusBar");
    }

    private void initRecyclerView() {
        mContentRv.addItemDecoration(BGADivider.newBitmapDivider());

        mContentAdapter = new ContentAdapter(mContentRv);
        mContentAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Toast.makeText(parent.getContext(), "鐐瑰嚮浜嗘潯鐩?" + (position + 1), Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    mSwipeBackHelper.forward(RecyclerIndexActivity.class);
                }
            }
        });
        mContentAdapter.setOnRVItemLongClickListener(new BGAOnRVItemLongClickListener() {
            @Override
            public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
                Toast.makeText(parent.getContext(), "闀挎寜浜嗘潯鐩?" + (position + 1), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        List<String> data = new ArrayList<>();
        data.add("RecyclerIndex");
        for (int i = 1; i < 21; i++) {
            data.add("鏍囬" + i);
        }
        mContentAdapter.setData(data);
        mContentRv.setAdapter(mContentAdapter);
    }
}

