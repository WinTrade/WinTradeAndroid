package ru.wintrade.mvp.view.entrance

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface OnBoardView: MvpView {
    fun init()
    fun setVPPos(pos: Int)
}