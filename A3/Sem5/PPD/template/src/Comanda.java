public class Comanda {
    private Integer id_comanda;
    private String tip_mancare;
    private String status;

    public Comanda(Integer id_comanda, String tip_mancare, String status) {
        this.id_comanda = id_comanda;
        this.tip_mancare = tip_mancare;
        this.status = status;
    }

    public Integer getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(Integer id_comanda) {
        this.id_comanda = id_comanda;
    }

    public String getTip_mancare() {
        return tip_mancare;
    }

    public void setTip_mancare(String tip_mancare) {
        this.tip_mancare = tip_mancare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
