package app.template.orm;

import app.template.orm.classparser.MethodCaller;
import app.template.orm.classparser.PropertyChecker;
import app.template.orm.classparser.PropertyParser;
import app.template.orm.exceptions.DuplicateDataException;
import app.template.orm.exceptions.OrmException;
import app.template.orm.exceptions.PrimaryKeyException;
import app.template.orm.sql.DDLWriter;
import app.template.orm.sql.DMLWriter;
import app.template.orm.sql.SelectExecutor;
import app.template.orm.sql.utils.JavaSQLMapper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ORM {
    ConnectionManager connectionManager;

    public ORM(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
    }

    private static int sortClasses(Class<?> c1, Class<?> c2){
        PropertyParser<?> parser1 = new PropertyParser<>(c1);
        PropertyParser<?> parser2 = new PropertyParser<>(c2);
        return parser1.getNrAllFK() - parser2.getNrAllFK();
    }

    public void createTables(Class<?>... classes) throws OrmException, SQLException {
        List<DDLWriter> writers = Arrays.stream(classes).sorted(ORM::sortClasses).map(DDLWriter::new).collect(Collectors.toList());
        if(writers.size() == 0)
            return;
        for(DDLWriter sqlScript : writers)
        {
            //System.err.println(sqlScript.getCreateSQL()+"\n\n");
            connectionManager.executeUpdateSql(sqlScript.getCreateSQL());
        }
    }

    public void dropTables(Class<?>... classes) throws SQLException {
        List<DDLWriter> writers = Arrays.stream(classes).sorted(ORM::sortClasses).map(DDLWriter::new).collect(Collectors.toList());
        Collections.reverse(writers);
        for(DDLWriter sqlScript : writers)
        {
            connectionManager.executeUpdateSql(sqlScript.getDropSQL());
        }
    }

    public <T> List<T> select(Class<T> table) throws SQLException, OrmException {
        SelectExecutor se = new SelectExecutor(connectionManager);
        return se.executeSelectAll(table);
    }

    public <T> void update(T updated, Object... pkValues) throws OrmException, SQLException {
        String SQL = DMLWriter.getUpdateSQL(updated, pkValues);
        connectionManager.executeUpdateSql(SQL);
    }

}
