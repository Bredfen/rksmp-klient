    package com.example.test

    import com.example.test.ui.home.ProductItem
    import com.example.test.ui.login.LoginResponse
    import retrofit2.Call
    import retrofit2.http.Body
    import retrofit2.http.GET
    import retrofit2.http.Header
    import retrofit2.http.POST
    import retrofit2.http.Query

    data class RegisterRequest(
        val login: String,
        val password: String
    )

    data class LoginRequest(
        val login: String,
        val password: String
    )


    interface ApiService {
        @POST("/auth/register")
        fun register(@Body request: RegisterRequest): Call<Unit>

        @POST("/login")
        fun login(@Body request: LoginRequest): Call<LoginResponse>

        @GET("/products")
        fun searchProducts(
            @Header("Authorization") token: String,
            @Query("search") search: String = ""
        ): Call<List<ProductItem>>

    }



