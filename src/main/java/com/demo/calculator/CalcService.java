package com.demo.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CalcService {

    private final JdbcTemplate jdbcTemplate;

    public Optional<String> evaluate(String line) {
        try {
            String sql = MessageFormat.format("select ({0}) amount", line);
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, String.class));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
