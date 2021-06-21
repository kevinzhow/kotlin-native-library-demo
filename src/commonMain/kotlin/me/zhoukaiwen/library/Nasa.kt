package me.zhoukaiwen.library
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*

/**
 * Astronomy Picture of the Day
 *
 *  This endpoint structures the APOD imagery and associated metadata so that it can be repurposed for other applications.
 *
 * @property date The date of the APOD image to retrieve
 * @property title The title of the image.
 * @property hdurl The URL for any high-resolution image for that day. Returned regardless of 'hd' param setting but will be omitted in the response IF it does not exist originally at APOD.
 * @property explanation The supplied text explanation of the image.
 * @property url The URL of the APOD image or video of the day.
 * @property media_type The type of media (data) returned. May either be 'image' or 'video' depending on content.
 */
@Serializable
@SerialName("APOD")
data class APOD(val date: String,
                val explanation: String,
                val hdurl: String,
                val media_type: String,
                val service_version: String,
                val title: String,
                val url: String)

class NASA(private val apiKey: String) {

    val NASAEntryPoint.fullPath: String
        get() {
            return nasaBaseURL + this.path
        }

    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    private val nasaBaseURL: String = "https://api.nasa.gov"

    enum class NASAEntryPoint(val path: String) {
        APOD( "/planetary/apod")

    }

    suspend fun getAPOD(): APOD? {
        val response: HttpResponse =  client.get(NASAEntryPoint.APOD.fullPath) {
            parameter("api_key", apiKey)
        }

        return try {
            val apod: APOD = response.receive()
            apod
        } catch (e: NoTransformationFoundException) {
            null
        }
    }
}