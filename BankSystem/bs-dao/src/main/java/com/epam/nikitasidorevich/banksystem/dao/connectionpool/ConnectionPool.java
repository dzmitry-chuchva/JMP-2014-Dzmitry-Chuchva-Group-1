package com.epam.nikitasidorevich.banksystem.dao.connectionpool;

import com.epam.nikitasidorevich.banksystem.dao.connectionpool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Connection pool maintained so that the connections can be reused when future
 * requests to the database are required
 *
 * @version     1.0 29 Jan 2013
 * @author      Nikita Sidorevich
 */
public final class ConnectionPool {
    private static ConnectionPool instance = null;
    private BlockingQueue<ConnectionWrapper> availableConnections;
    private BlockingQueue<ConnectionWrapper> unavailableConnections;

    /**
     * Inner class that implements the wrapper of real connection, which
     * guarantees the impossibility of creating and adding to the pool
     * connections from the outside
     *
     * @version     1.0 29 Jan 2013
     * @author      Nikita Sidorevich
     */
    public class ConnectionWrapper implements AutoCloseable {
        private Connection con;

        private ConnectionWrapper(Connection con) {
            this.con = con;
        }

        public Statement createStatement() throws SQLException {
            return con.createStatement();
        }

        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            return con.prepareStatement(sql);
        }

        public CallableStatement prepareCall(String sql) throws SQLException {
            return con.prepareCall(sql);
        }

        public String nativeSQL(String sql) throws SQLException {
            return con.nativeSQL(sql);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {
            con.setAutoCommit(autoCommit);
        }

        public boolean getAutoCommit() throws SQLException {
            return con.getAutoCommit();
        }

        public void commit() throws SQLException {
            con.commit();
        }

        public void rollback() throws SQLException {
            con.rollback();
        }

        public void close() throws SQLException {
            con.close();
        }

        public boolean isClosed() throws SQLException {
            return con.isClosed();
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return con.getMetaData();
        }

        public void setReadOnly(boolean readOnly) throws SQLException {
            con.setReadOnly(readOnly);
        }

        public boolean isReadOnly() throws SQLException {
            return con.isReadOnly();
        }

        public void setCatalog(String catalog) throws SQLException {
            con.setCatalog(catalog);
        }

        public String getCatalog() throws SQLException {
            return con.getCatalog();
        }

        public void setTransactionIsolation(int level) throws SQLException {
            con.setTransactionIsolation(level);
        }

        public int getTransactionIsolation() throws SQLException {
            return con.getTransactionIsolation();
        }

        public SQLWarning getWarnings() throws SQLException {
            return con.getWarnings();
        }

        public void clearWarnings() throws SQLException {
            con.clearWarnings();
        }

        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency) throws SQLException {
            return con.createStatement(resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType,
                                                  int resultSetConcurrency) throws SQLException {
            return con.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency) throws SQLException {
            return con.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return con.getTypeMap();
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            con.setTypeMap(map);
        }

        public void setHoldability(int holdability) throws SQLException {
            con.setHoldability(holdability);
        }

        public int getHoldability() throws SQLException {
            return con.getHoldability();
        }

        public Savepoint setSavepoint() throws SQLException {
            return con.setSavepoint();
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return con.setSavepoint(name);
        }

        public void rollback(Savepoint savepoint) throws SQLException {
            con.rollback(savepoint);
        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            con.releaseSavepoint(savepoint);
        }

        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return con.createStatement(resultSetType, resultSetConcurrency,
                    resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType,
                                                  int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return con.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return con.prepareCall(sql, resultSetType, resultSetConcurrency,
                    resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql,
                                                  int autoGeneratedKeys) throws SQLException {
            return con.prepareStatement(sql, autoGeneratedKeys);
        }

        public PreparedStatement prepareStatement(String sql,
                                                  int[] columnIndexes) throws SQLException {
            return con.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(String sql,
                                                  String[] columnNames) throws SQLException {
            return con.prepareStatement(sql, columnNames);
        }

        public Clob createClob() throws SQLException {
            return con.createClob();
        }

        public Blob createBlob() throws SQLException {
            return con.createBlob();
        }

        public NClob createNClob() throws SQLException {
            return con.createNClob();
        }

        public SQLXML createSQLXML() throws SQLException {
            return con.createSQLXML();
        }

        public boolean isValid(int timeout) throws SQLException {
            return con.isValid(timeout);
        }

        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            con.setClientInfo(name, value);
        }

        public void setClientInfo(Properties properties)
                throws SQLClientInfoException {
            con.setClientInfo(properties);
        }

        public String getClientInfo(String name) throws SQLException {
            return con.getClientInfo(name);
        }

        public Properties getClientInfo() throws SQLException {
            return con.getClientInfo();
        }

        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return con.createArrayOf(typeName, elements);
        }

        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return con.createStruct(typeName, attributes);
        }

        public void setSchema(String schema) throws SQLException {
            con.setSchema(schema);
        }

        public String getSchema() throws SQLException {
            return con.getSchema();
        }

        public void abort(Executor executor) throws SQLException {
            con.abort(executor);
        }

        public void setNetworkTimeout(Executor executor, int milliseconds)
                throws SQLException {
            con.setNetworkTimeout(executor, milliseconds);
        }

        public int getNetworkTimeout() throws SQLException {
            return con.getNetworkTimeout();
        }

    }

    private ConnectionPool() throws ConnectionPoolException {
        int maxConnectionsAmount = 20;
        try {
            Class.forName("org.postgresql.Driver");
            availableConnections = new ArrayBlockingQueue<ConnectionWrapper>(maxConnectionsAmount);
            unavailableConnections = new ArrayBlockingQueue<ConnectionWrapper>(maxConnectionsAmount);
            for (int i = 0; i < maxConnectionsAmount; ++i) {
                availableConnections.add(createConnection());
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("JDBC driver loading failed", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Pool initialization failed", e);
        }
    }

    public static synchronized ConnectionPool getInstance()
            throws ConnectionPoolException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public ConnectionWrapper getConnection() throws ConnectionPoolException {
        try {
            ConnectionWrapper conn = availableConnections.take();
            unavailableConnections.add(conn);
            return conn;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Failed getting connection", e);
        }
    }

    private ConnectionWrapper createConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user","postgres");
        properties.setProperty("password","postgres");

        String url = "jdbc:postgresql://localhost:5432/BANKSYSTEM";

        Connection conn = DriverManager.getConnection(url, properties);
        return new ConnectionWrapper(conn);
    }

    public void releaseConnection(ConnectionWrapper conn) {
        unavailableConnections.remove(conn);
        availableConnections.add(conn);
    }

    public void closeAllConnections() throws ConnectionPoolException {
        for (ConnectionWrapper cw : availableConnections) {
            try {
                cw.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Closing connection failed", e);
            }
        }
        for (ConnectionWrapper cw : unavailableConnections) {
            try {
                cw.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Closing connection failed", e);
            }
        }
    }
}
