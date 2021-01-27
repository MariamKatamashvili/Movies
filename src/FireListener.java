public interface FireListener {

    void fireGetAll();
    void fireHighestRating();
    void fireLowestRating();
    void fireSearchByName(String text);
    void fireSearchByRating(int rating);

    void fireGetAll1();
    void fireAddMovie1(Movies movies);
    void fireRemovieMovie1(String name);

    void fireGetAll2();
    void fireAddMovie2(Movies movies);
    void fireRemoveMovie2(String name);

    void fireConnectionClose();

}
