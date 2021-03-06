package ru.wintrade.mvp.presenter.entrance

import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import com.github.terrakok.cicerone.Router
import ru.wintrade.mvp.model.entity.Profile
import ru.wintrade.mvp.model.repo.ApiRepo
import ru.wintrade.mvp.model.repo.ProfileRepo
import ru.wintrade.mvp.model.repo.RoomRepo
import ru.wintrade.mvp.view.entrance.SignInView
import ru.wintrade.navigation.Screens
import javax.inject.Inject

class SignInPresenter : MvpPresenter<SignInView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiRepo: ApiRepo

    @Inject
    lateinit var roomRepo: RoomRepo

    @Inject
    lateinit var profile: Profile

    @Inject
    lateinit var profileRepo: ProfileRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun openRegistrationScreen() {
        router.navigateTo(Screens.SignUpScreen())
    }

    fun openResetPassScreen() {
        router.navigateTo(Screens.ResetPasswordScreen())
    }

    fun loginBtnClicked(nickname: String, password: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val deviceToken = task.result
                profile.deviceToken = deviceToken
                deviceTokenGranted(nickname, password)
            }
        }
    }

    private fun deviceTokenGranted(nickname: String, password: String) {
        apiRepo.auth(nickname, password).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ authToken ->
                val token = "Token $authToken"
                profile.token = token
                tokenGranted()
            }, {
                viewState.showToast("Неверные данные")
            })
    }

    private fun tokenGranted() {
        apiRepo.postDeviceToken(profile.token!!, profile.deviceToken!!).subscribe({},
            {
                it.printStackTrace()
            })
        profileRepo.getRemoteUserProfile(profile).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userProfile ->
                    profile.user = userProfile
                    userProfileGranted()
                },
                {
                    it.message
                }
            )
    }

    private fun userProfileGranted() {
        profileRepo.save(profile).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                if (profile.user!!.isTrader)
                    router.newRootScreen(Screens.TraderMeMainScreen())
                else
                    router.newRootScreen(Screens.SubscriberMainScreen())
            },
            {
                it.printStackTrace()
            }
        )

    }
}