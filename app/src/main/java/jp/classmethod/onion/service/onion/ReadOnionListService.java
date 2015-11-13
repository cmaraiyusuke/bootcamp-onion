package jp.classmethod.onion.service.onion;

import java.util.List;
import java.util.concurrent.Callable;

import bolts.Task;
import jp.classmethod.onion.domain.onion.Onion;
import jp.classmethod.onion.infrastructure.onion.GetOnionListHTTPClient;

/**
 * たまねぎ一覧読み込みサービス
 */
public class ReadOnionListService {

    /**
     * たまねぎ一覧取得HTTPクライアント
     *
     * ここはサービスレイヤー(アプリケーションレイヤ)
     * 参照先はインフラストラクチャレイヤのHTTPクライアント
     *
     * service ----> infrastructure
     * オォン！
     *
     * 使うのはHTTPのクライアントってこと、知ってる必要ない……なくない？
     */
    GetOnionListHTTPClient mClient = new GetOnionListHTTPClient();

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
