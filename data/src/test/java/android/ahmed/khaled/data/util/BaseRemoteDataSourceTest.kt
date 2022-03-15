package android.ahmed.khaled.data.util

import android.ahmed.khaled.data.restful.MoviesEndPoints
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
abstract class BaseRemoteDataSourceTest {

    protected val mockWebServer = MockWebServer().also {
        it.start()
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    protected val moviesEndPoints = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesEndPoints::class.java)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}

fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    val source = inputStream?.let { inputStream.source().buffer() } ?: return
    val body = source.readString(StandardCharsets.UTF_8)
    enqueue(MockResponse().setResponseCode(code).setBody(body))
}



