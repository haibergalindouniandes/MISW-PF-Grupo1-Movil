package com.sportapp_grupo1.test

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sportapp_grupo1.R
import com.sportapp_grupo1.ui.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserProfileTest {

    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    //Constante que define el tiempo de espera para que se carguen los datos retornados por el adapter
    val delayService = Integer.toUnsignedLong(5000)
    val delayService2 = Integer.toUnsignedLong(1000)

    fun clickIntoButtonById(idView: Int) {
        //Damos click en el boton idView
        Espresso.onView(ViewMatchers.withId(idView)).perform(ViewActions.click())
    }
    fun clickIntoButtonByIdwithScroll(idView: Int) {
        //Damos click en el boton idView
        Espresso.onView(ViewMatchers.withId(idView)).perform(ViewActions.scrollTo(), ViewActions.click())
    }

    fun clickIntoButtonByText(idView: Int, valueToSearch: String) {
        //Damos click en el boton idView
        getTextViewByValue(idView, valueToSearch)?.perform(ViewActions.click())
    }

    fun getTextViewByValue(idView: Int, valueToSearch: String): ViewInteraction? {
        //Validamos si existe un TextView de tipo idView con el texto valueToSearch
        return Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(idView),
                ViewMatchers.withText(valueToSearch)
            )
        )
    }

    fun setTextLayoutViewByValue(idView: Int, valueToType:String) {
        //Validamos si existe un TextView de tipo idView con el texto valueToSearch
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(idView)),
                ViewMatchers.withClassName(CoreMatchers.endsWith("EditText"))
            )
        ).perform(
            ViewActions.typeText(valueToType)
        )
    }

    fun setTextViewByValue(idView: Int, valueToType:String) {
        //Validamos si existe un TextView de tipo idView con el texto valueToSearch
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(idView)
            )
        ).perform(
            ViewActions.click(),
            ViewActions.clearText(),
            ViewActions.typeText(valueToType),
            ViewActions.closeSoftKeyboard()
        )
    }


    fun navigateToTestScreen(){
        setTextViewByValue(R.id.input_username,"s.salazarc@uniandes.edu.co")
        setTextViewByValue(R.id.input_password,"123456789156Aa-")
        clickIntoButtonById(R.id.login_button)
        SystemClock.sleep(delayService)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.fab_profile),
                ViewMatchers.isDisplayed()
            )
        )
        clickIntoButtonById(R.id.fab_profile)
        SystemClock.sleep(delayService)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.cerrar_sesion),
                ViewMatchers.isDisplayed()
            )
        )
    }

    /**
     * Esta prueba tiene la intencion de comprobar la informacion de Perfil
     */
    @Test
    fun positiveTestSuccesfull(){
        /* Primero navegamos a la pantalla correcta */
        navigateToTestScreen()
        Espresso.onView(ViewMatchers.withId(R.id.nombre_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Shiomar Salazar Castillo")
            )
        )
        //TODO: Descomentar despues de terminar implementacion
        /*Espresso.onView(ViewMatchers.withId(R.id.edad_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.peso_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.altura_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.Telefono_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.correo_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.sexo_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.ciudad_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.vo2max_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.ftp_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )*/
    }

    /**
     * Esta prueba tiene la intencion de comprobar los botones no implementados
     */
    @Test
    fun notImplementedButtons(){
        /* Primero navegamos a la pantalla correcta */
        navigateToTestScreen()
        clickIntoButtonById(R.id.editar_perfil)
        SystemClock.sleep(delayService2)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.cerrar_sesion),
                ViewMatchers.isDisplayed()
            )
        )
        clickIntoButtonById(R.id.cambiar_contraseña)
        SystemClock.sleep(delayService2)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.cerrar_sesion),
                ViewMatchers.isDisplayed()
            )
        )
        clickIntoButtonById(R.id.mejorar_plan)
        SystemClock.sleep(delayService2)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.cerrar_sesion),
                ViewMatchers.isDisplayed()
            )
        )
    }
    /**
     * Esta prueba tiene la intencion de comprobar el cerrado de sesion
     */
    @Test
    fun cerrarSesion(){
        /* Primero navegamos a la pantalla correcta */
        navigateToTestScreen()
        clickIntoButtonById(R.id.cerrar_sesion)
        SystemClock.sleep(delayService2)
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.login_button),
                ViewMatchers.isDisplayed()
            )
        )

    }

}