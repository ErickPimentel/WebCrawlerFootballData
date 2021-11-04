public class Dados {
    private String link_csv;
    private String country;
    private String league;
    private String season;

    public Dados(String link_csv, String country, String league, String season) {
        this.link_csv = link_csv;
        this.country = country;
        this.league = league;
        this.season = season;
    }

    public String getLink_csv() {
        return link_csv;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }


    public String getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "link_csv='" + link_csv + '\'' +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}