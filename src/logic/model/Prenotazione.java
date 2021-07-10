package model;

public class Prenotazione {
    private int id;
    private String nomeCampo;
    private String data;
    private String ora;
    private String prezzo;
    private String nomeCliente;
    private String cognomeCliente;
    private String telefonoCliente;

    public Prenotazione(int id, String nomeCampo, String data, String ora, String prezzo, String nomeCliente, String cognomeCliente) {
        this.id = id;
        this.nomeCampo = nomeCampo;
        this.data = data;
        this.ora = ora;
        this.prezzo = prezzo;
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
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

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCognomeCliente() {
        return cognomeCliente;
    }

    public void setCognomeCliente(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}
