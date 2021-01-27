import java.sql.Connection;
import java.sql.SQLException;

public class MVCModel extends MoviesDAO{
    public MVCModel(Connection connection) throws SQLException {
        super(connection);
    }

    private  DataBase dataBase;
    public  MVCModel(DataBase dataBase) throws SQLException {
        super(dataBase.getConnection());
        this.dataBase = dataBase;
    }

    public void closeTheConnection() {
        dataBase.closeConnection();
    }

}