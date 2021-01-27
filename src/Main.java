import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBase dataBase = new DataBase();
        MVCView mvcView = new MVCView();
        MVCModel mvcModel = new MVCModel(dataBase);
        MVCController mvcController = new MVCController(mvcView, mvcModel);
        mvcController.start();
    }
}
