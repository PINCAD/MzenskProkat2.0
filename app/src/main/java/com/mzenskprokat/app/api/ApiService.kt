package com.mzenskprokat.app.api

import com.mzenskprokat.app.models.OrderRequest
import com.mzenskprokat.app.models.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * API интерфейс для взаимодействия с сервером Мценскпрокат
 *
 * ВАЖНО: Этот файл является примером для будущей интеграции с Backend API.
 * В текущей версии приложение использует статические данные из ProductRepository.
 */
interface MzenskProkatApiService {

    /**
     * Получить список всех продуктов
     */
    @GET("api/products")
    suspend fun getProducts(): Response<List<Product>>

    /**
     * Получить продукт по ID
     */
    @GET("api/products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<Product>

    /**
     * Получить продукты по категории
     */
    @GET("api/products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Response<List<Product>>

    /**
     * Поиск продуктов
     */
    @GET("api/products/search")
    suspend fun searchProducts(@Query("q") query: String): Response<List<Product>>

    /**
     * Отправить заказ
     */
    @POST("api/orders")
    suspend fun submitOrder(@Body order: OrderRequest): Response<OrderResponse>

    /**
     * Получить контактную информацию
     */
    @GET("api/contacts")
    suspend fun getContactInfo(): Response<ContactInfoResponse>
}

/**
 * Ответ сервера при отправке заказа
 */
data class OrderResponse(
    val success: Boolean,
    val message: String,
    val orderId: String? = null
)

/**
 * Ответ сервера с контактной информацией
 */
data class ContactInfoResponse(
    val phone: String,
    val email: String,
    val address: String,
    val website: String,
    val workingHours: WorkingHours
)

data class WorkingHours(
    val weekdays: String,
    val weekend: String
)

/**
 * Singleton для создания API клиента
 */
object ApiClient {

    // TODO: Замените на реальный URL вашего API
    private const val BASE_URL = "https://api.mzenskprokat.ru/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MzenskProkatApiService by lazy {
        retrofit.create(MzenskProkatApiService::class.java)
    }
}

/**
 * Пример использования API в Repository:
 *
 * fun getAllProducts(): Flow<Result<List<Product>>> = flow {
 *     emit(Result.Loading)
 *     try {
 *         val response = ApiClient.apiService.getProducts()
 *         if (response.isSuccessful && response.body() != null) {
 *             emit(Result.Success(response.body()!!))
 *         } else {
 *             emit(Result.Error("Ошибка загрузки данных"))
 *         }
 *     } catch (e: Exception) {
 *         emit(Result.Error(e.message ?: "Ошибка сети"))
 *     }
 * }
 *
 * fun submitOrder(order: OrderRequest): Flow<Result<Boolean>> = flow {
 *     emit(Result.Loading)
 *     try {
 *         val response = ApiClient.apiService.submitOrder(order)
 *         if (response.isSuccessful && response.body()?.success == true) {
 *             emit(Result.Success(true))
 *         } else {
 *             emit(Result.Error(response.body()?.message ?: "Ошибка отправки"))
 *         }
 *     } catch (e: Exception) {
 *         emit(Result.Error(e.message ?: "Ошибка сети"))
 *     }
 * }
 */