package entity;

public class Campo {
    private String nome;
    private String comune;
    private String indirizzo;
    private String desc;
    private String data;
    private String ora;
    private String renter;
    private String sport;

    public Campo(String nome, String comune, String indirizzo, String desc, String data, String ora, String renter, String sport) {
        this.nome = nome;
        this.comune = comune;
        this.indirizzo = indirizzo;
        this.desc = desc;
        this.data = data;
        this.ora = ora;
        this.renter = renter;
        this.sport = sport;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
