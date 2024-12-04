package com.example.starwarsblasterstournament.Services

import com.example.starwarsblasterstournament.Model.Match
import com.example.starwarsblasterstournament.Model.Player
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


interface ApiService {
    @GET("b/IKQQ")
    suspend fun getPlayers(): List<Player>

    @GET("b/JNYL")
    suspend fun getMatches(): List<Match>
}

object RetrofitInstance {
    val client = createInsecureOkHttpClient()

    //https://jsonkeeper.com/b/JNYL
    private const val BASE_URL = "https://jsonkeeper.com/"


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun createInsecureOkHttpClient(): OkHttpClient {
        // Create an all-accepting TrustManager
        val trustAllCertificates = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
        }

        // Set up SSL context to allow all certificates
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf<TrustManager>(trustAllCertificates), java.security.SecureRandom())

        // Create the OkHttpClient with the custom SSL context and hostname verifier
        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates)
            .hostnameVerifier { _, _ -> true } // Accept any hostname
            .build()
    }
}