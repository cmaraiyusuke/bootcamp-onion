package jp.classmethod.onion.presentation;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.classmethod.onion.R;
import jp.classmethod.onion.domain.onion.Onion;
import jp.classmethod.onion.service.onion.ReadOnionListService;

/**
 * メインアクティビティ
 */
public class MainActivity extends AppCompatActivity {

    /**
     * たまねぎ一覧取得サービス
     *
     * ここはUIレイヤ（プレゼンテーションレイヤ）
     * 参照先はサービスレイヤ（アプリケーションレイヤ）
     *
     * presentation ----> service
     */
    ReadOnionListService mReadOnionListService = new ReadOnionListService();

    @Bind(R.id.toolbar) Toolbar mToolbar = null;

    @Bind(R.id.list) ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        ListAdapter adapter = new OnionListAdapter(this.getLayoutInflater());
        mListView.setAdapter(adapter);
        refreshListView();
    }

    /**
     * たまねぎ一覧のListViewを更新する
     */
    private void refreshListView() {
        mReadOnionListService.read().onSuccess(
            new Continuation<List<Onion>, Void>() {
                @Override
                public Void then(Task<List<Onion>> task) throws Exception {
                    List<Onion> onions = task.getResult();
                    ((OnionListAdapter) mListView.getAdapter()).setItems(onions);
                    return null;
                }
            }, Task.UI_THREAD_EXECUTOR
        );
    }

    @OnClick(R.id.fab)
    public void onFloatingButtonClicked(View view) {
        Snackbar.make(view, "オッスオッス", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

}
