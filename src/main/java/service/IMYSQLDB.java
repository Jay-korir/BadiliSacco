package service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface IMYSQLDB<T extends Entity> {

    String createInsertQuery();

    String createUpdateQuery(Map<String, Object> entryMap);

    public ResultSet fetchAll() throws SQLException;

    public ResultSet fetchOne() throws SQLException;

    public void update();
    //String createSelectQuery();
    // public String createSelectWithWhereClauseQuery();

    //public String createSelectOneQuery();
    boolean executeQuery(String query);

    public void save();

    ResultSet executeReadQuery(String query) throws SQLException;
}
