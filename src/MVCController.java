import java.util.ArrayList;
import java.util.List;

public class MVCController implements FireListener {

    private MVCView mvcView;
    private MVCModel mvcModel;

    public MVCController(MVCView mvcView, MVCModel mvcModel) {
        this.mvcView = mvcView;
        this.mvcModel = mvcModel;
        mvcView.setFireListener(this);
    }

    public void start() {
        mvcView.start();
    }

    @Override
    public void fireGetAll() {
        List<Movies> list = mvcModel.getAllMovies();
        mvcView.changeTable(list);
    }

    @Override
    public void fireHighestRating() {
        mvcView.clearTable();
        List<Movies> topMovie = new ArrayList<>();
        topMovie.add(mvcModel.highestRating());
        mvcView.changeTable(topMovie);
    }

    @Override
    public void fireLowestRating() {
        mvcView.clearTable();
        List<Movies> worstMovie = new ArrayList<>();
        worstMovie.add(mvcModel.lowestRating());
        mvcView.changeTable(worstMovie);
    }

    @Override
    public void fireSearchByName(String name) {
        mvcView.clearTable();
        List<Movies> movies = new ArrayList<>();
        movies.add(mvcModel.searchByName(name));
        mvcView.changeTable(movies);
    }

    @Override
    public void fireSearchByRating(int rating) {
        mvcView.clearTable();
        List<Movies> movies = new ArrayList<>();
        movies.add(mvcModel.searchByRating(rating));
        mvcView.changeTable(movies);
    }

    @Override
    public void fireGetAll1() {
        List<Movies> list = mvcModel.getAllMovies1();
        mvcView.changeTable1(list);
    }

    @Override
    public void fireAddMovie1(Movies movies) {
        mvcModel.addMovie1(movies);
    }

    @Override
    public void fireRemovieMovie1(String name) {
        mvcModel.removeMovie1(name);
    }


    @Override
    public void fireGetAll2() {
        List<Movies> list = mvcModel.getAllMovies2();
        mvcView.changeTable2(list);
    }

    @Override
    public void fireAddMovie2(Movies movies) {
        mvcModel.addMovie2(movies);
    }

    @Override
    public void fireRemoveMovie2(String name) {
        mvcModel.removeMovie2(name);
    }

    @Override
    public void fireConnectionClose() {
        mvcModel.closeTheConnection();
    }

}
