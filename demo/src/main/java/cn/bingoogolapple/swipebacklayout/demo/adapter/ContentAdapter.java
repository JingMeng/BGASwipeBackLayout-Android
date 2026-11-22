package cn.bingoogolapple.swipebacklayout.demo.adapter;

import androidx.recyclerview.widget.RecyclerView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.swipebacklayout.demo.R;

/**
 * 浣滆€?鐜嬫旦 閭欢:bingoogolapple@gmail.com
 * 鍒涘缓鏃堕棿:16/12/28 涓婂崍1:21
 * 鎻忚堪:
 */
public class ContentAdapter extends BGARecyclerViewAdapter<String> {

    public ContentAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_test);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
        helper.setText(R.id.tv_item_test_title, model);
        helper.setText(R.id.tv_item_test_content, "鍐呭" + (position + 1));
    }
}
