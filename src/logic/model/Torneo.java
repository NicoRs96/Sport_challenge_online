package model;

public class Torneo {
    private int id;
    private String nome;
    private String campo;
    private int campoId;
    private String data;
    private String ora;
    private double prezzo;
    private int etaMin;
    private int numMinPart;
    private String dataScadenza;
    private String metodoPagamento;
    private String desc;
    private String citta;
    private String indirizzo;
    private String sport;
    private int isConfermato;
    private String confermato;

    
    public Torneo(String nome, String campo, String data, String ora, double prezzo, int etaMin, int numMinPart){
        
        this.nome = nome;
        this.campo = campo;
        this.data = data;
        this.ora = ora;
        this.prezzo = prezzo;
        this.etaMin = etaMin;
        this.numMinPart = numMinPart;
       
    }
    
    

    public int getCampoId() {
        return campoId;
    }

    public void setCampoId(int campoId) {
        this.campoId = campoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getEtaMin() {
        return etaMin;
    }

    public void setEtaMin(int etaMin) {
        this.etaMin = etaMin;
    }

    public int getNumMinPart() {
        return numMinPart;
    }

    public void setNumMinPart(int numMinPart) {
        this.numMinPart = numMinPart;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getIsConfermato() {
        return isConfermato;
    }

    public void setIsConfermato(int isConfermato) {
        this.isConfermato = isConfermato;
    }
    
    public void setConfermato() {
    	confermato = (isConfermato==1)?"SI": "NO";
    	}
    public String getConfermato() {
    	return confermato;
    }
    	
   
}
