package app.template.orm.sql;

import app.template.orm.ConnectionManager;
import app.template.orm.classparser.MethodCaller;
import app.template.orm.classparser.PropertyChecker;
import app.template.orm.classparser.PropertyParser;
import app.template.orm.exceptions.DataNotFoundException;
import app.template.orm.exceptions.OrmException;
import app.template.orm.exceptions.PrimaryKeyException;
import app.template.orm.sql.utils.JavaSQLMapper;
import app.template.orm.sql.utils.Utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SelectExecutor {
    ConnectionManager connectionManager;
    //private final PropertyParser<?> parser; // TABLE

    public SelectExecutor(ConnectionManager connectionManager){
        this.connectionManager = connectionManager; }

    public <T> T findByPK(Class<T> the_class, Object... pkValues) throws OrmException, SQLException {
        PropertyParser<Class<T>> parser = new PropertyParser<>(the_class);
        List<Field> pks = parser.getPKs();
        if(pks.size() != pkValues.length)
            throw new PrimaryKeyException("Wrong number of PKs in SELECT for table "+parser.getName()+" "+pkValues.length+", instead of "+pks.size());
        String SQL = "SELECT * FROM \""+parser.getName()+"\" WHERE "+Utils.createConditionSequence(pks, pkValues)+" LIMIT 1";

        T rez;
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery()) {
            if(resultSet.next())
                rez = createEntityFromResultSet(resultSet, the_class);
            else throw new DataNotFoundException("Can not retrieve data from table "+the_class.getSimpleName()+" with the given primary keys!");
        }
        return rez;
    }

    public <T> List<T> executeSelectAll(Class<T> the_class) throws SQLException, OrmException {
        String SQL = "SELECT * FROM \""+the_class.getSimpleName()+"\"";
        List<T> rez = new LinkedList<>();

        try(Connection connection=connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                T elem = createEntityFromResultSet(resultSet, the_class);
                rez.add(elem);
            }
        }
        return rez;
    }

    private <T> T createEntityFromResultSet(ResultSet resultSet, Class<T> the_class) throws OrmException, SQLException {
        PropertyParser<Class<T>> parser = new PropertyParser<>(the_class);
        List<Field> fields = parser.getFields();
        Object instance = MethodCaller.ctor(the_class);

        for (Field field : fields){
            Object obj;
            if(!PropertyChecker.isFKEntity(field))
                obj = resultSet.getObject(field.getName());
            else {
                Object idObj = resultSet.getObject(field.getName());
                obj = findByPK(field.getType(), idObj);
            }

            if(!field.getType().isEnum())
                obj = JavaSQLMapper.toJavaValue(obj, field.getType());
            else
                obj = JavaSQLMapper.toJavaEnum(obj, field.getType());
            MethodCaller.callSetter(instance, field.getName(), obj);
        }
        return the_class.cast(instance);
    }

}
