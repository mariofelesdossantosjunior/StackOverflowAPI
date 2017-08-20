package

import com.stfalcon.mvphelper.Presenter
import javax.inject.Inject

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:30
 * @email mario_feles@live.com
 * @project Default (Template) Project
 */
class TesteActivityPresenter @Inject constructor()
    : Presenter<TesteActivityContract.View>(), TesteActivityContract.Presenter {

    override fun onViewAttached(view: TesteActivityContract.View, created: Boolean) {
        super.onViewAttached(view, created)
    }

    override fun onViewDetached() {
        super.onViewDetached()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}