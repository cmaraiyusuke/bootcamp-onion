package jp.classmethod.onion.infrastructure.onion;

import java.util.ArrayList;
import java.util.List;

import jp.classmethod.onion.domain.onion.Onion;
import jp.classmethod.onion.domain.onion.TasteType;

/**
 * たまねぎ一覧を取得するインメモリストアクライアント
 */
public class GetOnionListMemoryStoreClient {

    /**
     * インメモリストア
     */
    volatile private static List<Onion> sStore = null;

    /**
     * @return インメモリストア
     */
    synchronized private static List<Onion> getStore() {
        if (sStore == null) {
            sStore = new ArrayList<Onion>() {
                {
                    add(new Onion("ダミーたまねぎ1", "ダミー農場A", TasteType.NORMAL));
                    add(new Onion("ダミーたまねぎ2", "ダミー農場B", TasteType.NORMAL));
                    add(new Onion("ダミーたまねぎ3", "ダミー農場C", TasteType.NORMAL));
                    add(new Onion("ダミーたまねぎ4", "ダミー農場D", TasteType.BITTER));
                    add(new Onion("ダミーたまねぎ5", "ダミー農場E", TasteType.BITTER));
                    add(new Onion("ダミーたまねぎ6", "ダミー農場F", TasteType.BITTER));
                    add(new Onion("ダミーたまねぎ7", "ダミー農場G", TasteType.SWEET));
                    add(new Onion("ダミーたまねぎ8", "ダミー農場H", TasteType.SWEET));
                    add(new Onion("ダミーたまねぎ9", "ダミー農場I", TasteType.SWEET));
                }
            };
        }
        return sStore;
    }

    /**
     * たまねぎ一覧をインメモリストアから取得
     *
     * @return たまねぎ一覧
     */
    public List<Onion> get() {
        return new ArrayList<>(getStore());
    }

}
