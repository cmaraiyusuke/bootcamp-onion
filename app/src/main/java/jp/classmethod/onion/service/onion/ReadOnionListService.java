package jp.classmethod.onion.service.onion;

import java.util.List;
import java.util.concurrent.Callable;

import bolts.Task;
import javax.inject.Inject;

import jp.classmethod.onion.domain.onion.Onion;

/**
 * たまねぎ一覧読み込みサービス
 */
public class ReadOnionListService {

    /**
     * たまねぎ一覧取得HTTPクライアント
     *
     * ここはサービスレイヤー(アプリケーションレイヤ)
     * 参照先もサービスレイヤ
     *
     * service ----> service
     */
    private final OnionListStore mStore;

    @Inject
    public ReadOnionListService(OnionListStore store) {
        this.mStore = store;
    }

    /**
     * たまねぎの一覧を読み込む
     *
     * @return たまねぎ一覧のタスク
     */
    public Task<List<Onion>> read() {
        return Task.callInBackground(new Callable<List<Onion>>() {
            @Override
            public List<Onion> call() throws Exception {
                return mClient.get();
            }
        });
    }

}
