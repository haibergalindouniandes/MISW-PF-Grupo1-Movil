package com.sportapp_grupo1.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sportapp_grupo1.models.Alimentacion
import com.sportapp_grupo1.models.Entrenamiento
import com.sportapp_grupo1.models.PlanAlimentacion
import com.sportapp_grupo1.models.PlanEntrenamiento
import com.sportapp_grupo1.models.User
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL_USER = "https://misw-pf-grupo1-backend-gestor-usuarios-klme3r4qta-uc.a.run.app/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun login(username:String, password:String) = suspendCoroutine { cont ->
        requestQueue.add(
            postRequest(
                "usuarios/login",
                JSONObject(
                    """{"email":"$username",
                    |"password":"$password"}""".trimMargin(),
                ),
                { response ->
                     val user = User (
                        userId = response.optInt("id"),
                        nombres = response.optString("nombres"),
                         rol = response.optString("rol"),
                         plan = response.optString("plan"),
                         token = response.optString("token")
                    )
                    cont.resume(user)
                },
                {
                    cont.resumeWithException(it)
                }, BASE_URL_USER)
        )

    }

    suspend fun addPlanEntrenamiento(new: PlanEntrenamiento) = suspendCoroutine { cont ->
        /*requestQueue.add(
            postRequest(
                "albums",
                JSONObject(
                    """{"name":"${album.name}",
                    |"cover":"${album.cover}",
                    |"releaseDate":"${album.releaseDate}",
                    |"description":"${album.description}",
                    |"genre":"${album.genre}",
                    |"recordLabel":"${album.recordLabel}"}""".trimMargin()
                ),
                { response ->
                     val planCreated = PlanEntrenamiento (
                        planEntrenamientoID = response.optInt("planId"),
                        actividad = response.optInt("actividad"),
                        lunes = response.optInt("lunes"),
                        martes = response.optInt("martes"),
                        miercoles = response.optInt("miercoles"),
                        jueves = response.optInt("jueves"),
                        viernes = response.optInt("viernes"),
                        sabado = response.optInt("sabado"),
                        domingo = response.optInt("domingo"),
                        numero_semanas = response.optInt("numero_semanas")
                    )
                    cont.resume(planCreated)
                },
                {
                    cont.resumeWithException(it)
                })
        )*/

        val planCreated = PlanEntrenamiento (
            planEntrenamientoID = 1,
            actividad = new.actividad,
            lunes = new.lunes,
            martes = new.martes,
            miercoles = new.miercoles,
            jueves = new.jueves,
            viernes = new.viernes,
            sabado = new.sabado,
            domingo = new.domingo,
            numero_semanas = new.numero_semanas
        )
        cont.resume(planCreated)

    }

    suspend fun addPlanAlimentacion(new: PlanAlimentacion) = suspendCoroutine { cont ->
        /*requestQueue.add(
            postRequest(
                "albums",
                JSONObject(
                    """{"name":"${album.name}",
                    |"cover":"${album.cover}",
                    |"releaseDate":"${album.releaseDate}",
                    |"description":"${album.description}",
                    |"genre":"${album.genre}",
                    |"recordLabel":"${album.recordLabel}"}""".trimMargin()
                ),
                { response ->
                     val planCreated = PlanAlimentacion (
                        planAlimentacionID = response.optInt("planId"),
                        lunes = response.optInt("lunes"),
                        martes = response.optInt("martes"),
                        miercoles = response.optInt("miercoles"),
                        jueves = response.optInt("jueves"),
                        viernes = response.optInt("viernes"),
                        sabado = response.optInt("sabado"),
                        domingo = response.optInt("domingo"),
                        numero_semanas = response.optInt("numero_semanas")
                    )
                    cont.resume(planCreated)
                },
                {
                    cont.resumeWithException(it)
                })
        )*/

        val planCreated = PlanAlimentacion (
            planAlimentacionID = 1,
            lunes = new.lunes,
            martes = new.martes,
            miercoles = new.miercoles,
            jueves = new.jueves,
            viernes = new.viernes,
            sabado = new.sabado,
            domingo = new.domingo,
            numero_semanas = new.numero_semanas
        )
        cont.resume(planCreated)

    }

    suspend fun addAlimentacion(new: Alimentacion) = suspendCoroutine { cont ->
        /*requestQueue.add(
            postRequest(
                "albums",
                JSONObject(
                    """{"name":"${album.name}",
                    |"cover":"${album.cover}",
                    |"releaseDate":"${album.releaseDate}",
                    |"description":"${album.description}",
                    |"genre":"${album.genre}",
                    |"recordLabel":"${album.recordLabel}"}""".trimMargin()
                ),
                { response ->
                     val resultCreated = Alimentacion (
                        alimentacionID = 1,
                        calorias1 = new.calorias1,
                        calorias2 = new.calorias2,
                        calorias3 = new.calorias3,
                        ml_agua = new.ml_agua
                    )
                    cont.resume(resultCreated)
                },
                {
                    cont.resumeWithException(it)
                })
        )*/

        val resultCreated = Alimentacion (
            alimentacionID = 1,
            calorias1 = new.calorias1,
            calorias2 = new.calorias2,
            calorias3 = new.calorias3,
            ml_agua = new.ml_agua
        )
        cont.resume(resultCreated)

    }

    suspend fun addEntrenamiento(new: Entrenamiento) = suspendCoroutine { cont ->
        /*requestQueue.add(
            postRequest(
                "albums",
                JSONObject(
                    """{"name":"${album.name}",
                    |"cover":"${album.cover}",
                    |"releaseDate":"${album.releaseDate}",
                    |"description":"${album.description}",
                    |"genre":"${album.genre}",
                    |"recordLabel":"${album.recordLabel}"}""".trimMargin()
                ),
                { response ->
                     val resultCreated = Entrenamiento (
                                        entrenamientoId = 1,
                                        userId = new.userId,
                                        actividad = new.actividad,
                                        distancia = new.distancia,
                                        tiempo = new.tiempo,
                                        resultado = new.resultado,
                                        feedback = new.feedback
                                    )
                    cont.resume(resultCreated)
                },
                {
                    cont.resumeWithException(it)
                })
        )*/

        val resultCreated = Entrenamiento (
            entrenamientoId = 1,
            userId = new.userId,
            actividad = new.actividad,
            distancia = new.distancia,
            tiempo = new.tiempo,
            resultado = new.resultado,
            feedback = new.feedback
        )
        cont.resume(resultCreated)

    }

    private fun postRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener,
        URL:String
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.POST,
            URL + path,
            body,
            responseListener,
            errorListener
        )
    }

}