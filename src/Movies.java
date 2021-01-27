public class Movies {

    private String movieName;
    private String overview;
    private int rating;

    public Movies(String movieName, String overview, int rating) {
        this.movieName = movieName;
        this.overview = overview;
        this.rating = rating;
    }

    public String[] getInfoForRow() {
        String[] res = new String[3];
        res[0] = movieName;
        res[1] = overview;
        res[2] = Integer.toString(rating);
        return res;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
