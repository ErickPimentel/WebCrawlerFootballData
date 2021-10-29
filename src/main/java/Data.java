public class Data {
    private String link_csv;
    private String Campeonato;
    private String Season;

    public Data(String link_csv, String campeonato) {
        this.link_csv = link_csv;
        Campeonato = campeonato;
    }

    public String getLink_csv() {
        return link_csv;
    }

    public void setLink_csv(String link_csv) {
        this.link_csv = link_csv;
    }

    public String getCampeonato() {
        return Campeonato;
    }

    public void setCampeonato(String campeonato) {
        Campeonato = campeonato;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    @Override
    public String toString() {
        return "Data{" +
                "link_csv='" + link_csv + '\'' +
                ", Campeonato='" + Campeonato + '\'' +
                '}';
    }
}
