package fer.hr.masterthesis;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Catalog implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("naziv")
    private String naziv;
    @SerializedName("link")
    private String link;
    @SerializedName("proizvodjac")
    private String proizvodjac;
    @SerializedName("opis")
    private String opis;
    @SerializedName("jezici")
    private String jezici;
    @SerializedName("prikaz")
    private String prikaz;
    @SerializedName("vrstaPoteskoce")
    private String vrstaPoteskoce;
    @SerializedName("ICTUredjaj")
    private String ICTUredjaj;
    @SerializedName("asistivnaTehnologija")
    private String asistivnaTehnologija;
    @SerializedName("platforma")
    private String platforma;
    @SerializedName("cijena")
    private String cijena;
    @SerializedName("uporabljivost")
    private String uporabljivost;
    @SerializedName("recenzije")
    private String recenzije;
    @SerializedName("dostupnost")
    private String dostupnost;
    @SerializedName("vrstaPrimjene")
    private String vrstaPrimjene;
    @SerializedName("mjestoPrimjene")
    private String mjestoPrimjene;

    public Catalog(int id, String naziv, String link, String proizvodjac, String opis, String jezici,
                   String prikaz, String vrstaPoteskoce, String ICTUredjaj, String asistivnaTehnologija,
                   String platforma, String cijena, String uporabljivost, String recenzije, String dostupnost,
                   String vrstaPrimjene, String mjestoPrimjene) {
        this.id = id;
        this.naziv = naziv;
        this.link = link;
        this.proizvodjac = proizvodjac;
        this.opis = opis;
        this.jezici = jezici;
        this.prikaz = prikaz;
        this.vrstaPoteskoce = vrstaPoteskoce;
        this.ICTUredjaj = ICTUredjaj;
        this.asistivnaTehnologija = asistivnaTehnologija;
        this.platforma = platforma;
        this.cijena = cijena;
        this.uporabljivost = uporabljivost;
        this.recenzije = recenzije;
        this.dostupnost = dostupnost;
        this.vrstaPrimjene = vrstaPrimjene;
        this.mjestoPrimjene = mjestoPrimjene;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getJezici() {
        return jezici;
    }

    public void setJezici(String jezici) {
        this.jezici = jezici;
    }

    public String getPrikaz() {
        return prikaz;
    }

    public void setPrikaz(String prikaz) {
        this.prikaz = prikaz;
    }

    public String getVrstaPoteskoce() {
        return vrstaPoteskoce;
    }

    public void setVrstaPoteskoce(String vrstaPoteskoce) {
        this.vrstaPoteskoce = vrstaPoteskoce;
    }

    public String getICTUredjaj() {
        return ICTUredjaj;
    }

    public void setICTUredjaj(String iCTUredjaj) {
        ICTUredjaj = iCTUredjaj;
    }

    public String getAsistivnaTehnologija() {
        return asistivnaTehnologija;
    }

    public void setAsistivnaTehnologija(String asistivnaTehnologija) {
        this.asistivnaTehnologija = asistivnaTehnologija;
    }

    public String getPlatforma() {
        return platforma;
    }

    public void setPlatforma(String platforma) {
        this.platforma = platforma;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public String getUporabljivost() {
        return uporabljivost;
    }

    public void setUporabljivost(String uporabljivost) {
        this.uporabljivost = uporabljivost;
    }

    public String getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(String recenzije) {
        this.recenzije = recenzije;
    }

    public String getDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(String dostupnost) {
        this.dostupnost = dostupnost;
    }

    public String getVrstaPrimjene() {
        return vrstaPrimjene;
    }

    public void setVrstaPrimjene(String vrstaPrimjene) {
        this.vrstaPrimjene = vrstaPrimjene;
    }

    public String getMjestoPrimjene() {
        return mjestoPrimjene;
    }

    public void setMjestoPrimjene(String mjestoPrimjene) {
        this.mjestoPrimjene = mjestoPrimjene;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", link='" + link + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", opis='" + opis + '\'' +
                ", jezici='" + jezici + '\'' +
                ", prikaz='" + prikaz + '\'' +
                ", vrstaPoteskoce='" + vrstaPoteskoce + '\'' +
                ", ICTUredjaj='" + ICTUredjaj + '\'' +
                ", asistivnaTehnologija='" + asistivnaTehnologija + '\'' +
                ", platforma='" + platforma + '\'' +
                ", cijena='" + cijena + '\'' +
                ", uporabljivost='" + uporabljivost + '\'' +
                ", recenzije='" + recenzije + '\'' +
                ", dostupnost='" + dostupnost + '\'' +
                ", vrstaPrimjene='" + vrstaPrimjene + '\'' +
                ", mjestoPrimjene='" + mjestoPrimjene + '\'' +
                '}';
    }
}
