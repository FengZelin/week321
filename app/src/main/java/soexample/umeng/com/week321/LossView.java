package soexample.umeng.com.week321;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LossView extends LinearLayout {

    final int mMaxSize = 20;
    List<String> list = new ArrayList<>();
    Context mContext;
    private DBDao dbDao;

    public LossView(Context context) {
        this(context, null);
    }

    public LossView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LossView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        dbDao = new DBDao(mContext);
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void setData(List<String> list) {
        this.list = list;
        showData();
    }

    private void showData() {
        removeAllViews();
        LinearLayout linearLayout = (LinearLayout) View.inflate(mContext, R.layout.layout_lossview_ver, null);
        addView(linearLayout);

        int len = 0;
        for (int i = 0; i<list.size(); i++) {
            final String str = list.get(i);
            len += str.length();
            if (len == 0) {
                Toast.makeText(mContext,"输入的内容不能为空",Toast.LENGTH_SHORT).show();
            } else {
                if (len > mMaxSize) {
                    linearLayout = (LinearLayout) View.inflate(mContext, R.layout.layout_lossview_ver, null);
                    addView(linearLayout);
                    len = str.length();
                }

                View view = View.inflate(mContext, R.layout.layout_lossview, null);
                TextView mLossView = view.findViewById(R.id.tv_lossview);
                mLossView.setText(str);
                linearLayout.addView(view);

                //设置权重
                LayoutParams params = (LayoutParams) view.getLayoutParams();
                params.weight = 1;
                view.setLayoutParams(params);
                final int index = i;

                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
                    }
                });
                view.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
//                        dbDao.delete(index);
                        list.remove(index);
                        showData();
                        return false;
                    }
                });
            }
        }
    }

}
