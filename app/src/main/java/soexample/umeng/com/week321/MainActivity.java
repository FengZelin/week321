package soexample.umeng.com.week321;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> list = new ArrayList<>();
    private ImageView mSearch;
    private LossView mTvLossviewHistory;
    private LossView mTvLossviewHot;
    private ImageView mDelete;
    private EditText mEditView;
    private DBDao dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbDao = new DBDao(this);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditView.getText().toString();
                list.add(str);
                dbDao.add(str);
                mTvLossviewHistory.setData(list);
                initData();
            }
        });
        initData();
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbDao.delete();
                mTvLossviewHot.removeAllViews();
            }
        });
    }
    private void initData() {
        List<String> dbList = dbDao.query();
        mTvLossviewHot.setData(dbList);
    }
    private void initView() {
        mEditView = findViewById(R.id.editview);
        mSearch = findViewById(R.id.search);
        mTvLossviewHistory = findViewById(R.id.tv_lossview_history);
        mTvLossviewHot = findViewById(R.id.tv_lossview_hot);
        mDelete = findViewById(R.id.delete);
    }
}