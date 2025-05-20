package com.example.test

// File: src/test/java/com/example/test/model/DataClassesTest.kt

import com.example.test.ui.home.ProductItem
import com.example.test.ui.login.LoginResponse
import com.example.test.RegisterRequest
import com.example.test.LoginRequest
import com.example.test.ui.sbornik.Product
import com.example.test.ui.profile.ItemProfile
import com.example.test.ui.order.EdinicaTovara
import com.example.test.ui.order.ItemZakaz
import org.junit.Assert.*
import org.junit.Test

class DataClassesTest {

    @Test
    fun testProductItemEquality() {
        val item1 = ProductItem("Test", "Sub", 123.0, "link")
        val item2 = ProductItem("Test", "Sub", 123.0, "link")
        assertEquals(item1, item2)
    }

    @Test
    fun testProductFields() {
        val product = Product("icon", "Nokia", 5.0, 1000.0, "phones", "desc")
        assertEquals("icon", product.imageResId)
        assertEquals("Nokia", product.name)
        assertEquals(5.0, product.rating, 0.001)
        assertEquals(1000.0, product.price, 0.001)
        assertEquals("phones", product.category)
        assertEquals("desc", product.description)
    }

    @Test
    fun testLoginResponse() {
        val resp = LoginResponse("tokenValue")
        assertEquals("tokenValue", resp.token)
    }

    @Test
    fun testRegisterRequest() {
        val req = RegisterRequest("user", "pass")
        assertEquals("user", req.login)
        assertEquals("pass", req.password)
    }

    @Test
    fun testLoginRequest() {
        val req = LoginRequest("user", "pass")
        assertEquals("user", req.login)
        assertEquals("pass", req.password)
    }

    @Test
    fun testItemProfile() {
        val item = ItemProfile(123, "TestUser")
        assertEquals(123, item.imageprofile)
        assertEquals("TestUser", item.nameprofile)
    }

    @Test
    fun testEdinicaTovara() {
        val et = EdinicaTovara("n", "k", "2", "3.0", 456)
        assertEquals("n", et.nameTovar)
        assertEquals("k", et.kodTovar)
        assertEquals("2", et.kolvoTovar)
        assertEquals("3.0", et.priceTovar)
        assertEquals(456, et.imageTovar)
    }

    @Test
    fun testItemZakaz() {
        val et = EdinicaTovara("n", "k", "2", "3.0", 456)
        val zakaz = ItemZakaz("1", "today", "here", "8900", listOf(et))
        assertEquals("1", zakaz.numberZakaz)
        assertEquals("today", zakaz.dateZakaz)
        assertEquals("here", zakaz.adress)
        assertEquals("8900", zakaz.numberPhone)
        assertEquals(1, zakaz.EdinicaTovara.size)
    }
}
