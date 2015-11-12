package jp.classmethod.onion.module;

import dagger.Module;
import dagger.Provides;
import jp.classmethod.onion.infrastructure.onion.GetOnionListHTTPClient;
import jp.classmethod.onion.presentation.MainActivity;
import jp.classmethod.onion.service.onion.OnionListStore;
import jp.classmethod.onion.service.onion.ReadOnionListService;

@Module(
        injects = {
                MainActivity.class
        },
        addsTo = AppModule.class
)
public class ActivityModule {

    @Provides
    public ReadOnionListService provideService(OnionListStore store) {
        return new ReadOnionListService(store);
    }

    @Provides
    public OnionListStore provideStore() {
        return new GetOnionListHTTPClient();
    }

}
