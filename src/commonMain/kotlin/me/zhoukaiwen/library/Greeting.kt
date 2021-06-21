package me.zhoukaiwen.library
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}




class NASA(val apiKey: String) {

    val NASAEntryPoint.fullPath: String
        get() {
            return nasaBaseURL + this.path
        }

    val client = HttpClient()

    val nasaBaseURL: String = "https://api.nasa.gov"

    enum class NASAEntryPoint(val path: String) {
        APOD( "/planetary/apod")

    }

    suspend fun getAPOD(): String {
        val httpResponse: HttpResponse = client.get(NASAEntryPoint.APOD.fullPath) {
            parameter("api_key", apiKey)
        }
        val stringBody: String = httpResponse.receive()
        return stringBody
    }

}