package ru.wintrade.mvp.presenter

import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.wintrade.mvp.view.TraderStatView
import ru.wintrade.navigation.Screens
import javax.inject.Inject

class TraderStatPresenter : MvpPresenter<TraderStatView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backClicked(): Boolean {
        router.backTo(Screens.AllTradersScreen())
        return true
    }
}