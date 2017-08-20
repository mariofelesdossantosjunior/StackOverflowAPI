package

import android.content.Context
import com.stfalcon.mvphelper.PresenterLoader
import dagger.Module
import dagger.Provides

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:30
 * @email mario_feles@live.com
 * @project Default (Template) Project
 */
@Module
class TesteActivityModule {

    @Provides
    fun providePresenterLoader(context: Context, presenter: TesteActivityPresenter)
            : PresenterLoader<TesteActivityContract.Presenter> = PresenterLoader(context, presenter)
}