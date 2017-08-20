package

import com.mario.stackoverflowapi.R
import com.stfalcon.mvphelper.MvpActivity

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:30
 * @email mario_feles@live.com
 * @project Default (Template) Project
 */
class TesteActivity : MvpActivity<TesteActivityContract.Presenter, TesteActivityContract.View>(),
        TesteActivityContract.View {

    override fun getLayoutResId(): Int = R.layout.activity_teste

}