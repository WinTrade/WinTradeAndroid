package ru.wintrade.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.wintrade.mvp.view.MainView
import ru.wintrade.navigation.Screens
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.newRootScreen(Screens.OnBoardScreen())
    }

    fun openTradersScreen(){
        router.replaceScreen(Screens.TradersMainScreen())
    }

    fun openSubscriberObservationScreen() {
        router.replaceScreen(Screens.SubscriberMainScreen())
    }

    fun backClicked() {
        router.exit()
    }
}