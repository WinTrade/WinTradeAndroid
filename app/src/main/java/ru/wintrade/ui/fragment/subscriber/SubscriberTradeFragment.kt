package ru.wintrade.ui.fragment.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_subscriber_deal.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.wintrade.R
import ru.wintrade.mvp.presenter.subscriber.SubscriberTradePresenter
import ru.wintrade.mvp.view.subscriber.SubscriberDealView
import ru.wintrade.ui.App
import ru.wintrade.ui.adapter.SubscriberTradesRVAdapter

class SubscriberTradeFragment : MvpAppCompatFragment(), SubscriberDealView {
    companion object {
        fun newInstance() = SubscriberTradeFragment()
    }

    @InjectPresenter
    lateinit var presenter: SubscriberTradePresenter

    var adapter: SubscriberTradesRVAdapter? = null

    @ProvidePresenter
    fun providePresenter() = SubscriberTradePresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subscriber_deal, container, false)

    override fun init() {
        adapter = SubscriberTradesRVAdapter(presenter.listPresenter)
        rv_sub_deal.adapter = adapter
        rv_sub_deal.layoutManager = LinearLayoutManager(context)
        layout_sub_deal_refresh.setOnRefreshListener {
            presenter.refreshed()
        }
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        layout_sub_deal_refresh.isRefreshing = isRefreshing
    }

    override fun updateAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun selectBtn(pos: Int) {

    }
}