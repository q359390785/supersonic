package com.tencent.supersonic.headless.core.adaptor.db;


import com.google.common.collect.Lists;
import com.tencent.supersonic.headless.core.pojo.ConnectInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class DatafuseAdaptor extends BaseDbAdaptor {

    @Override
    public List<String> getDBs(ConnectInfo connectionInfo) throws SQLException {
        List<String> dbs = Lists.newArrayList();
        DatabaseMetaData metaData = getDatabaseMetaData(connectionInfo);
        try {
            ResultSet schemaSet = metaData.getSchemas();
            while (schemaSet.next()) {
                String db = schemaSet.getString("TABLE_SCHEM");
                dbs.add(db);
            }
        } catch (Exception e) {
            log.info("get meta schemas failed, try to get catalogs");
        }
        try {
            ResultSet catalogSet = metaData.getCatalogs();
            while (catalogSet.next()) {
                String db = catalogSet.getString("TABLE_CAT");
                dbs.add(db);
            }
        } catch (Exception e) {
            log.info("get meta catalogs failed, try to get schemas");
        }
        return dbs;
    }

    @Override
    public String getDateFormat(String dateType, String dateFormat, String column) {
        return column;
    }

    @Override
    public String functionNameCorrector(String sql) {
        return sql;
    }

}
