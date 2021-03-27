package ru.wintrade.mvp.presenter.subscriber

import androidx.fragment.app.Fragment
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.wintrade.mvp.presenter.adapter.ISubscriberMainVPListPresenter
import ru.wintrade.mvp.view.subscriber.SubscriberMainView
import ru.wintrade.ui.fragment.subscriber.SubscriberDealFragment
import ru.wintrade.ui.fragment.subscriber.SubscriberNewsFragment
import ru.wintrade.ui.fragment.subscriber.SubscriberObservationFragment
import javax.inject.Inject

class SubscriberMainPresenter : MvpPresenter<SubscriberMainView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    val listPresenter = SubscriberMainVPListPresenter()

    inner class SubscriberMainVPListPresenter : ISubscriberMainVPListPresenter {
        private val viewPagerListOfFragment = listOf<Fragment>(
            SubscriberObservationFragment.newInstance(),
            SubscriberDealFragment.newInstance(),
            SubscriberNewsFragment.newInstance()
        )

        override fun getCount(): Int = viewPagerListOfFragment.size

        override fun getFragmentList(): List<Fragment> {
            return viewPagerListOfFragment
        }
    }
}