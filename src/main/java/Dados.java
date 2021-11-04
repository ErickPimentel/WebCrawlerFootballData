public class Dados {
    private String link_csv;
    private String divisao;
    private String season;

    public Dados(String link_csv, String divisao) {
        this.link_csv = link_csv;
        this.divisao = divisao;
    }

    public String getLink_csv() {
        return link_csv;
    }

    public void setLink_csv(String link_csv) {
        this.link_csv = link_csv;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "link_csv='" + link_csv + '\'' +
                ", divisao='" + divisao + '\'' +
                '}';
    }
}
