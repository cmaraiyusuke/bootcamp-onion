package jp.classmethod.onion.infrastructure.onion;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.classmethod.onion.core.error.HTTPClientError;
import jp.classmethod.onion.domain.onion.Onion;
import jp.classmethod.onion.domain.onion.TasteType;
import jp.classmethod.onion.service.onion.OnionListStore;

/**
 * たまねぎ一覧を取得するHTTPクライアント
 */
public class GetOnionListHTTPClient implements OnionListStore {

    /**
     * OkHttpのクライアント
     */
    OkHttpClient mClient = new OkHttpClient();

    /**
     * JSONエンドポイント
     */
    static final String END_POINT
            = "https://s3-ap-northeast-1.amazonaws.com/arai-test/bootcamp/onion/onions.json";

    /**
     * たまねぎ一覧をHTTPで取得する処理
     *
     * @return たまねぎ一覧取得
     */
    @Override public List<Onion> get() {
        Request req = new Request.Builder().url(END_POINT).build();

        try {
            Response res = mClient.newCall(req).execute();
            String jsonString = res.body().string();
            JSONObject json = new JSONObject(jsonString);

            return convertToOnionList(json);
        } catch (Exception e) {
            // raise
            throw new HTTPClientError(e);
        }
    }

    /**
     * JSONオブジェクトからたまねぎリストを生成
     *
     * @param json JSONオブジェクト
     * @return たまねぎリスト
     * @throws JSONException JSON例外
     */
    private List<Onion> convertToOnionList(JSONObject json) throws JSONException {
        List<Onion> onions = new ArrayList<>();
        JSONArray array = json.getJSONArray("onions");

        for (int i = 0; i < array.length(); i++) {
            JSONObject onionJson = array.getJSONObject(i);
            String name = onionJson.getString("name");
            String farm = onionJson.getString("farm");
            String taste = onionJson.getString("taste");
            TasteType tasteType = mappingToTasteType(taste);

            Onion onion = new Onion(name, farm, tasteType);
            onions.add(onion);
        }

        return onions;
    }

    /**
     * 文字列からTasteTypeへマッピング
     *
     * @param taste 文字列
     * @return TasteType
     */
    private TasteType mappingToTasteType(String taste) {
        switch (taste) {
            case "bitter":
                return TasteType.BITTER;
            case "normal":
                return TasteType.NORMAL;
            case "sweet":
                return TasteType.SWEET;
            default:
                throw new IllegalArgumentException("Unexpected TasteType: " + taste);
        }
    }

}
