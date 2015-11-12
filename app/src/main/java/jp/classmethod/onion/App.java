package jp.classmethod.onion;

import android.app.Application;

import dagger.ObjectGraph;
import jp.classmethod.onion.module.AppModule;

/**
 * アプリケーションクラス
 */
public class App extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(new AppModule());
        mObjectGraph.inject(this);
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

}
