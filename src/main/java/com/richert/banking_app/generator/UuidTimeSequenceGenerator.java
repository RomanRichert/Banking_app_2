package com.richert.banking_app.generator;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.UUID;

import static java.lang.System.arraycopy;
import static java.lang.System.currentTimeMillis;

@RequiredArgsConstructor
public class UuidTimeSequenceGenerator implements IdentifierGenerator {

    private static final String NEXT_VAL_QUERY = "SELECT nextval('seq_for_uuid_generator');";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long currTimeMillis = currentTimeMillis();
        long sequenceValue = getSequenceValue();

        char[] uuidRaw = concatInHexFormat(currTimeMillis, sequenceValue);

        return UUID.fromString(formatUuidToString(uuidRaw));
    }

    private Long getSequenceValue() {
        return jdbcTemplate.queryForObject(NEXT_VAL_QUERY, (rs, rowNum) -> rs.getLong(1));
    }

    private char[] concatInHexFormat(long currTimeMillis, long sequenceValue) {
        char[] millisHex = Long.toHexString(currTimeMillis).toCharArray();
        char[] seqHex = Long.toHexString(sequenceValue).toCharArray();
        char[] concatenated = new char[36];

        arraycopy(millisHex, 0, concatenated, 0, millisHex.length);
        arraycopy(seqHex, 0, concatenated, 16, seqHex.length);

        return concatenated;
    }

    private String formatUuidToString(char[] uuid) {
        for (int i = 0; i < uuid.length; i++) {
            if (isDashPosition(i)) {
                arraycopy(uuid, i, uuid, i + 1, uuid.length - 1 - i);
                uuid[i] = '-';
            } else if (uuid[i] == 0) {
                uuid[i] = '0';
            }
        }
        return new String(uuid);
    }

    private boolean isDashPosition(int pos) {
        return pos == 8 || pos == 13 || pos == 18 || pos == 23;
    }
}