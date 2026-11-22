package cn.bingoogolapple.swipebacklayout.demo.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.swipebacklayout.demo.R;
import cn.bingoogolapple.swipebacklayout.demo.adapter.ContentAdapter;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/27 涓嬪崍11:29
 * 鎻忚堪:
 */
public class ContentFragment extends BaseFragment {
    private static final String EXTRA_POSITION = "EXTRA_POSITION";
    private RecyclerView mContentRv;
    private ContentAdapter mContentAdapter;
    private ImageView mHeaderIv;

    private int mPosition;

    /**
     * @param position 浣嶇疆
     * @return
     */
    public static ContentFragment newInstance(int position) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_POSITION, position);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);
        mHeaderIv = getViewById(R.id.headerView);
        mContentRv = getViewById(R.id.recyclerView);
    }

    @Override
    protected void setListener() {
        mContentAdapter = new ContentAdapter(mContentRv);
        mContentAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Toast.makeText(parent.getContext(), "鐐瑰嚮浜嗘潯鐩?" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        mContentAdapter.setOnRVItemLongClickListener(new BGAOnRVItemLongClickListener() {
            @Override
            public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
                Toast.makeText(parent.getContext(), "闀挎寜浜嗘潯鐩?" + (position + 1), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mContentRv.addItemDecoration(BGADivider.newBitmapDivider());

        mPosition = getArguments().getInt(EXTRA_POSITION);
        if (mPosition == 0) {
            mHeaderIv.setImageResource(R.mipmap.one);
        } else if (mPosition == 1) {
            mHeaderIv.setImageResource(R.mipmap.two);
        } else {
            mHeaderIv.setImageResource(R.mipmap.three);
        }

        List<String> data = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            data.add("绗? + (mPosition + 1) + "椤?鏍囬" + i);
        }
        mContentAdapter.setData(data);
        mContentRv.setAdapter(mContentAdapter);
    }
}

