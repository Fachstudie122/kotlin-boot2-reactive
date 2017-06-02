package com.sap.health.wtp

import com.mashape.unirest.http.Unirest
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

  @LocalServerPort
  var port: Int? = null

  @Test
  fun `Admit new patient`() {
    val response = Unirest.post("http://localhost:$port/\$process-message").header("Content-Type", "application/json").body(loadXml("person.json")).asString();

    assertThat(response.status, equalTo(201));
    assertNotNull(response.headers["Location"])
    assertThat(response.headers.getFirst("Location"), equalTo("Hans"))
  }

  fun loadXml(file: String): String {
    return ApplicationTests::class.java.classLoader.getResource(file).readText()
  }

}
