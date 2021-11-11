public class Arquivo {
    private String link_csv;
    private String country;
    private String league;
    private String season;
    private String filePath;
    private String fileName;

    public Arquivo(String link_csv, String country, String league, String season) {
        this.link_csv = link_csv;
        this.country = country;
        this.league = league;
        this.season = season;
    }

    public Arquivo(String link_csv) {
        this.link_csv = link_csv;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "link_csv='" + link_csv + '\'' +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", season='" + season + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}