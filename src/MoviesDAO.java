import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {

    private Connection connection;

    public MoviesDAO(Connection connection) throws SQLException {
        this.connection = connection;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("USE movies;");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movies> getAllMovies() {
        List<Movies> moviesList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title, overview, vote_average FROM movies.movie;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                moviesList.add(new Movies(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    Movies highestRating() {
        return highOrLowRating(true);
    }

    Movies lowestRating() {
        return highOrLowRating(false);
    }

    private Movies highOrLowRating (boolean highOrLow) {
        try {
            String s = "SELECT * FROM movies.movie ORDER BY vote_average;";
            if (highOrLow) {
                s += "DESC";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Movies(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<Movies> searchByName (String movieName) {
//        List<Movies> movies1 = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies.movie WHERE name = ?")) {
//            preparedStatement.setString(1, String.valueOf(movies));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                return (List<Movies>) new Movies(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3));
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public Movies searchByName (String movieName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies.movie WHERE name = ?");
            preparedStatement.setString(1, movieName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Movies(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Movies searchByRating (int rating) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies.movie WHERE vote_average = ?");
            preparedStatement.setString(1, String.valueOf(rating));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Movies(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Movies> getAllMovies1() {
        List<Movies> moviesList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies.watchlist");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                moviesList.add(new Movies(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public void addMovie1(Movies movies) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movies.watchlist (movieName, Genre, Rating) VALUES (?, ?, ?);");
            setInfo(preparedStatement, movies);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMovie1(String name) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movies.watchlist where movieName = ?;");
            preparedStatement.setString(1,name);
           preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
public List<Movies> getAllMovies2() {
    List<Movies> moviesList = new ArrayList<>();
    try{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies.neewwatchedmovies;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            moviesList.add(new Movies(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return moviesList;
}

    public void addMovie2(Movies movies) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movies.neewwatchedmovies (newMovieTitle, newMovieGenre, newMovieRating) VALUES (?, ?, ?);");
            setInfo(preparedStatement, movies);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMovie2(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movies.neewwatchedmovies WHERE newMovieTitle = ?;");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInfo(PreparedStatement preparedStatement, Movies movies) {
        try{
            preparedStatement.setString(1, movies.getMovieName());
            preparedStatement.setString(2, movies.getOverview());
            preparedStatement.setInt(3, movies.getRating());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
