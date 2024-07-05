package com.sparta.redirect_outsourcing.domain.integrationtest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class IntegrationTest {

    @Autowired
    DataSource dataSource;

    @BeforeAll
    void setUp() {
        try (Connection conn = dataSource.getConnection()) {
            // 자신의 script path 넣어주면 됨
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/test/testData.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}