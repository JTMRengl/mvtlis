package helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import util.CollectionUtil;
import util.JsonUtil;
import util.PropsUtil;

/**
 * 数据库助手
 * 
 * @author rengl 2016-03-24
 */
public class DatabaseHelper {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		
		Properties config = PropsUtil.loadProps("resources.properties");

		DATA_SOURCE.setDriverClassName(config.getProperty("jdbc.driver"));
		DATA_SOURCE.setUrl(config.getProperty("jdbc.url"));
		DATA_SOURCE.setUsername(config.getProperty("jdbc.username"));
		DATA_SOURCE.setPassword(config.getProperty("jdbc.password"));
		
		DATA_SOURCE.setMaxWaitMillis(1000);
		//maxIdle: 最大空闲连接
		DATA_SOURCE.setMaxIdle(20);

		Connection connection = CONNECTION_HOLDER.get();
		if (connection == null) {
			try {
				connection = DATA_SOURCE.getConnection();
				// connection = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				logger.error("get s failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(connection);
			}

		}
		return connection;
	}

	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				logger.error("开启事务出错！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				logger.error("提交事务出错！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}

	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				logger.error("回滚事务出错！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
    /**
     * 查询实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("query entity failure", e);
            throw new RuntimeException(e);
        }
        return entity;
    }	

	/**
	 * 根据 SQL 语句查询 Entity 列表
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {

		List<T> entityList;

		Connection connection = getConnection();
		try {
			entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			logger.error("query entity list failure", e);
			throw new RuntimeException(e);
		}

		return entityList;
	}

    /**
     * 插入实体
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
    	
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();
        
        return executeUpdate(sql, params) == 1;
    }
    
    /**
     * 更新实体
     */
    public static <T> boolean updateEntity(Class<T> entityClass, int id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.error("can not update entity: fieldMap is empty");
            return false;
        }

        String sql = "UPDATE " + entityClass.getSimpleName() + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(" = ?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE " + entityClass.getSimpleName() +  "_id = ?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, int id) {
        String sql = "DELETE FROM " + entityClass.getSimpleName() + " WHERE " + entityClass.getSimpleName() +  "_id = ?";
        return executeUpdate(sql, id) == 1;
    }    
    
    /**
     * 执行更新语句（包括：update、insert、delete）
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            logger.error("execute update failure", e);
            throw new RuntimeException(e);
        }
        return rows;
    }    
 	
    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }    
}
