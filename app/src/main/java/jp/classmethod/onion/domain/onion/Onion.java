package jp.classmethod.onion.domain.onion;

/**
 * たまねぎオブジェクト
 */
public final class Onion {

    /**
     * 名前
     */
    public final String name;

    /**
     * 産地名
     */
    public final String farm;

    /**
     * 味
     */
    public final TasteType taste;

    /**
     * たまねぎのコンストラクタ
     *
     * @param name  名前
     * @param farm  産地名
     * @param taste 味の種別
     */
    public Onion(String name, String farm, TasteType taste) {
        this.name = name;
        this.farm = farm;
        this.taste = taste;
    }

    @Override
    public int hashCode() {
        return
                41 * (
                        41 * (
                                41 + name.hashCode()
                        ) + farm.hashCode()
                ) + taste.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Onion) {
            Onion that = (Onion) o;
            return name.equals(that.name) &&
                    farm.equals(that.farm) &&
                    taste.equals(that.taste);
        } else {
            return false;
        }
    }

}
