package com.example.demo.Service;

import com.example.demo.dtos.stepValuesDTO;
import com.example.demo.entity.Champ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public void createTable(stepValuesDTO sVD) {
        String tableName = sVD.getFormLabel();
        List<Champ> champs = sVD.getFormValues();

        StringBuilder sqlCreate = new StringBuilder("CREATE TABLE IF NOT EXISTS `" + tableName + "` (id BIGINT AUTO_INCREMENT PRIMARY KEY");
        for (Champ champ : champs) {
            String columnName = champ.getChampLabel().replaceAll("\\s+", "_");
            String columnType = champ.getChampType().toLowerCase();
            sqlCreate.append(", `" + columnName + "` " + columnType);
        }
        sqlCreate.append(")");

        jdbcTemplate.execute(sqlCreate.toString());

        if (tableExists(tableName)) {


            StringBuilder sqlInsert = new StringBuilder("INSERT INTO `" + tableName + "` (");

            for (int i = 0; i < champs.size(); i++) {
                sqlInsert.append("`").append(champs.get(i).getChampLabel().replaceAll("\\s+", "_")).append("`");
                if (i < champs.size() - 1) sqlInsert.append(", ");
            }

            sqlInsert.append(") VALUES (");

            for (int i = 0; i < champs.size(); i++) {
                sqlInsert.append("'").append(champs.get(i).getChampValue()).append("'");
                if (i < champs.size() - 1) sqlInsert.append(", ");
            }

            sqlInsert.append(")");

            jdbcTemplate.execute(sqlInsert.toString());
        }
    }
    private boolean tableExists(String tableName) {
        try {
            jdbcTemplate.queryForObject("SELECT 1 FROM `" + tableName + "` LIMIT 1", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
