package

import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:30
 * @email mario_feles@live.com
 * @project Default (Template) Project
 */
@Subcomponent(modules = arrayOf(TesteActivityModule::class))
interface TesteActivitySubComponent : AndroidInjector<TesteActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TesteActivity>()

    //TODO REMINDER: move the line below to AppModule's @Module(...) annotation
    //TesteActivitySubComponent::class

    //TODO REMINDER: move this to ActivitiesInjectorFactories module
    @Binds
    @IntoMap
    @ActivityKey(TesteActivity::class)
    internal abstract fun bindTesteActivityInjectorFactory(
            builder: TesteActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}