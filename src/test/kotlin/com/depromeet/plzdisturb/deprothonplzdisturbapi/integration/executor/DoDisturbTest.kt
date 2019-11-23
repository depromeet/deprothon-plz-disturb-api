package com.depromeet.plzdisturb.deprothonplzdisturbapi.integration.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor.DoDisturb
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Suppress("NonAsciiCharacters")
@Transactional
@SpringBootTest
class DoDisturbTest {

    @Autowired
    private lateinit var doDisturb: DoDisturb


    @Test
    fun test_execute() {
        doDisturb.execute(
            DoDisturb.Param(listOf("ssss"))
        )
    }
}