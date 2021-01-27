import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.MarshalledObject;
import java.util.List;

public class MVCView {
    JFrame jFrame1;
    JPanel jPanel;
    private TextField searchByName, searchByRating;
    private JButton getAll, highestRating, lowestRating, searchByNameButton, searchByRatingButton, watchList, watchedMovies;
    private JLabel searchByNameLabel, searchByRatingLabel;
    private FireListener fireListener;
    private JTable jTable, jTable2, jTable3;
    private TextField addMovieNameText, addMovieGenreText, addMovieRatingText, removeMovieText, addMovieNameText1, addMovieGenreText1, addMovieRatingText1,  removeMovieText1 ;
    private JTextField name, genre, rating;
    private JButton getALL, addMovie, removeMovie;
    private JLabel addMovieNameLabel, addMovieGenreLabel, addMovieRatingLabel, removeMovieLabel;

    public MVCView() {
        init();
        north();
        south();
        setUpTable();
    }

    private void init() {
        jFrame1 = new JFrame("Movies");
        jFrame1.setSize(600,800);
        jFrame1.setDefaultCloseOperation(jFrame1.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout(5,1));
        jFrame1.add(jPanel);
    }

    private void north() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3,3));
        jPanel.add(northPanel, BorderLayout.NORTH);

        getAll = new JButton("All Movies");
        getAll.setBackground(Color.orange);
        getAll.addActionListener(e -> {
            fireListener.fireGetAll();
        });
        northPanel.add(getAll);

        highestRating = new JButton("Highest Rated Movies");
        highestRating.setBackground(Color.pink);
        highestRating.addActionListener(e -> {
            fireListener.fireHighestRating();
        });
        northPanel.add(highestRating);

        lowestRating = new JButton("Lowest Rated Movies");
        lowestRating.setBackground(Color.pink);
        lowestRating.addActionListener(e -> {
            fireListener.fireLowestRating();
        });
        northPanel.add(lowestRating);

        searchByNameLabel = new JLabel("Search by Name");
        searchByNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(searchByNameLabel);
        searchByName = new TextField();
        northPanel.add(searchByName);
        searchByNameButton = new JButton("Search by Name");
        searchByNameButton.setBackground(Color.CYAN);
        searchByNameButton.addActionListener(e -> {
            fireListener.fireSearchByName(searchByName.getText());
            setTextFieldsEmptyN();
        });
        northPanel.add(searchByNameButton);

        searchByRatingLabel = new JLabel("Search by Rating");
        searchByRatingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(searchByRatingLabel);
        searchByRating = new TextField();
        northPanel.add(searchByRating);
        searchByRatingButton = new JButton("Search by Rating");
        searchByRatingButton.setBackground(Color.CYAN);
        searchByRatingButton.addActionListener(e -> {
            fireListener.fireSearchByRating(Integer.parseInt(searchByRating.getText()));
            setTextFieldsEmptyR();
        });
        northPanel.add(searchByRatingButton);
    }

    private void south() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        jPanel.add(southPanel, BorderLayout.SOUTH);

        watchList = new JButton("My Watch List");
        watchList.setBackground(Color.GREEN);
        watchList.addActionListener(e -> {
            JFrame jFrame2 =  new JFrame("Watch List");
            jFrame2.setSize(600, 800);
            JPanel jPanel2 = new JPanel();
            jPanel2.setBackground(Color.orange);
            jFrame2.add(jPanel2);
            JPanel buttonPannel1 = new JPanel();
            buttonPannel1.setLayout(new GridLayout(1,3));
            jPanel2.add(buttonPannel1, BorderLayout.NORTH);
            JPanel buttonPannel2 = new JPanel();
            jPanel2.add(buttonPannel2, BorderLayout.SOUTH);

            getALL = new JButton("Get All Movies");
            getALL.setBackground(Color.green);
            getALL.addActionListener(e13 -> {
                fireListener.fireGetAll1();
            });
            buttonPannel1.add(getALL);

            addMovieNameLabel = new JLabel("Name: ");
            buttonPannel2.add(addMovieNameLabel);
            addMovieNameText = new TextField();
            buttonPannel2.add(addMovieNameText);
            addMovieGenreLabel = new JLabel("Genre: ");
            buttonPannel2.add(addMovieGenreLabel);
            addMovieGenreText = new TextField();
            buttonPannel2.add(addMovieGenreText);
            addMovieRatingLabel = new JLabel("Rating: ");
            buttonPannel2.add(addMovieRatingLabel);
            addMovieRatingText = new TextField();
            buttonPannel2.add(addMovieRatingText);
            addMovie = new JButton("Add a Movie");
            addMovie.setBackground(Color.green);
            addMovie.addActionListener(e1 -> {
                int number = parseint();
                fireListener.fireAddMovie1(new Movies(addMovieNameText.getText(), addMovieGenreText.getText(), number));
                setTextFieldsEmptyAdd();
            });
            buttonPannel2.add(addMovie);

            removeMovieLabel = new JLabel("Remove a movie");
            buttonPannel2.add(removeMovieLabel);
            removeMovieText = new TextField();
            buttonPannel2.add(removeMovieText);
            removeMovie = new JButton("Remove a Movie");
            removeMovie.setBackground(Color.green);
            removeMovie.addActionListener(e12 -> {
                fireListener.fireRemovieMovie1(removeMovieText.getText());
                setTextFieldsEmptyRemove();
            });
            buttonPannel2.add(removeMovie);

            String[] collNames = {"Movie Name", "Genre", "Rating"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(3);
            model.setColumnIdentifiers(collNames);
            jTable2 = new JTable(model);
            jPanel2.add(new JScrollPane(jTable2), BorderLayout.CENTER);

            jFrame2.setVisible(true);
        });
        southPanel.add(watchList);

        watchedMovies = new JButton("Watched Movies");
        watchedMovies.setBackground(Color.yellow);
        watchedMovies.setForeground(Color.BLACK);
        watchedMovies.addActionListener(e -> {
            JFrame jFrame3 = new JFrame("Watched Movies");
            jFrame3.setSize(600,800);
            JPanel jPanel3 = new JPanel();
            jPanel3.setBackground(Color.pink);
            jFrame3.add(jPanel3);
            JPanel buttonPannel1 = new JPanel();
            buttonPannel1.setLayout(new GridLayout(1,3));
            jPanel3.add(buttonPannel1, BorderLayout.NORTH);
            JPanel buttonPannel2 = new JPanel();
            jPanel3.add(buttonPannel2, BorderLayout.SOUTH);


            getALL = new JButton("Get All Movies");
            getALL.setBackground(Color.yellow);
            getALL.addActionListener(e13 -> {
                fireListener.fireGetAll2();
            });
            buttonPannel1.add(getALL);

            addMovieNameLabel = new JLabel("Name: ");
            buttonPannel2.add(addMovieNameLabel);
            addMovieNameText1 = new TextField();
            buttonPannel2.add(addMovieNameText1);
            addMovieGenreLabel = new JLabel("Genre: ");
            buttonPannel2.add(addMovieGenreLabel);
            addMovieGenreText1 = new TextField();
            buttonPannel2.add(addMovieGenreText1);
            addMovieRatingLabel = new JLabel("Rating: ");
            buttonPannel2.add(addMovieRatingLabel);
            addMovieRatingText1 = new TextField();
            buttonPannel2.add(addMovieRatingText1);
            addMovie = new JButton("Add a Movie");
            addMovie.setBackground(Color.yellow);
            addMovie.addActionListener(e1 -> {
                int number = parseint1();
                fireListener.fireAddMovie2(new Movies(addMovieNameText1.getText(), addMovieGenreText1.getText(), number));
                setTextFieldsEmptyAdd2();
            });
            buttonPannel2.add(addMovie);

            removeMovieLabel = new JLabel("Remove a movie");
            buttonPannel2.add(removeMovieLabel);
            removeMovieText1 = new TextField();
            buttonPannel2.add(removeMovieText1);
            removeMovie = new JButton("Remove a Movie");
            removeMovie.setBackground(Color.yellow);
            removeMovie.addActionListener(e12 -> {
                fireListener.fireRemoveMovie2(removeMovieText1.getText());
                setTextFieldsEmptyRemove2();
            });
            buttonPannel2.add(removeMovie);

            String[] collNames = {"Movie Name","Genre","Rating"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(3);
            model.setColumnIdentifiers(collNames);
            jTable3 = new JTable(model);
            jPanel3.add(new JScrollPane(jTable3), BorderLayout.CENTER);

            jFrame3.setVisible(true);
        });
        southPanel.add(watchedMovies);
    }

    public void setFireListener (FireListener fireListener) {
        this.fireListener = fireListener;
    }

    private void setUpTable() {
        String[] collNames = {"Movie Name","Overview","Rating"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(3);
        model.setColumnIdentifiers(collNames);
        jTable = new JTable(model);
        jPanel.add(new JScrollPane(jTable), (BorderLayout.CENTER));
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        while(model.getRowCount() >= 1) model.removeRow(0);
    }

    public void clearTable1() {
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        while(model.getRowCount() >= 1) model.removeRow(0);
    }

    public void clearTable2() {
        DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
        while(model.getRowCount() >= 1) model.removeRow(0);
    }

    public void changeTable(List<Movies> list) {
        clearTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable.getModel();
        for(Movies movies : list) {
            String[] arr = {movies.getMovieName(), movies.getOverview(), Integer.toString(movies.getRating())};
            defaultTableModel.addRow(arr);
        }
    }

    public void changeTable1(List<Movies> list) {
        clearTable1();
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable2.getModel();
        for(Movies movies : list) {
            String[] arr = {movies.getMovieName(), movies.getOverview(), Integer.toString(movies.getRating())};
            defaultTableModel.addRow(arr);
        }
    }

    public void changeTable2(List<Movies> list) {
        clearTable2();
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable3.getModel();
        for(Movies movies : list) {
            String[] arr = {movies.getMovieName(), movies.getOverview(), Integer.toString(movies.getRating())};
            defaultTableModel.addRow(arr);
        }
    }

    private int parseint() {
        int number = 0;
        try {
            number = Integer.parseInt(addMovieRatingText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    private int parseint1() {
        int number = 0;
        try {
            number = Integer.parseInt(addMovieRatingText1.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    private void setTextFieldsEmptyAdd() {
        addMovieNameText.setText("");
        addMovieGenreText.setText("");
        addMovieRatingText.setText("");
    }

    private void setTextFieldsEmptyRemove() {
        removeMovieText.setText("");
    }

    private void setTextFieldsEmptyAdd2() {
        addMovieNameText1.setText("");
        addMovieGenreText1.setText("");
        addMovieRatingText1.setText("");
    }

    private void setTextFieldsEmptyN() {
        searchByName.setText("");
    }

    private void setTextFieldsEmptyR() {
        searchByRating.setText("");
    }

    private void setTextFieldsEmptyRemove2() {
        removeMovieText1.setText("");
    }
    public void start() {
        jFrame1.setVisible(true);
    }
}