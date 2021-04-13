package model;

public class Campo {
    private int id;
    private String nome;
    private String comune;
    private String indirizzo;
    private String desc;
    private String data;
    private String ora;
    private String renter;
    private String sport;
    private String prezzo;
    private String modPagamento;
    private int isAffittabile;
    

   
    public Campo(String nome, String comune, String indirizzo) {
        
        this.nome = nome;
        this.comune = comune;
        this.indirizzo = indirizzo;
       
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

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getModPagamento() {
		return modPagamento;
	}

	public void setModPagamento(String modPagamento) {
		this.modPagamento = modPagamento;
	}

    public int getIsAffittabile() {
        return isAffittabile;
    }

    public void setIsAffittabile(int isAffittabile) {
        this.isAffittabile = isAffittabile;
    }

    public String toString(){
        return "id=" + getId() +
                ";nome=" + getNome() + "" +
                ";comune=" + getComune() + "" +
                ";indirizzo=" + getIndirizzo() + "" +
                ";data=" + getData() + "" +
                ";ora=" + getOra() + "";
    }
}
