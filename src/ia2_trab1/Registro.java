package ia2_trab1;

/**
 *
 * @author Bruno
 */
public class Registro implements Comparable<Registro> {
    Integer dataCompar;
    int tipoRegistro, dataPregao, codBDI;
    String codNEG;
    int tipoMerc;
    String nomeRes;
    
    String especificacao, prazoT, modRef;
    int precoAbertura, precoMax, precoMin, precoMed, precoUlt, precoOfc, precoOfv, totNeg;
    
    long quantTot, volumeTotal;
    int preEx, indOpc, dataVencimento, fatCot, ptoexe;
    String codISIN;
    int disMes;

    public Registro(String tipoRegistro, String dataPregao, String codBDI, String codNEG, String tipoMerc, String nomeRes,
                    String especificacao, String prazoT, String modRef, String precoAbertura, String precoMax, String precoMin, String precoMed, String precoUlt, String precoOfc, String precoOfv, String totNeg, String quantTot,
                    String volumeTotal, String preEx, String indOpc, String dataVencimento, String fatCot, String ptoexe, String codISIN, String disMes) {
        this.tipoRegistro = Integer.parseInt(tipoRegistro);
        this.dataPregao = Integer.parseInt(dataPregao);
        this.codBDI = Integer.parseInt(codBDI);
        this.codNEG = codNEG;
        this.tipoMerc = Integer.parseInt(tipoMerc);
        this.nomeRes = nomeRes;
        this.especificacao = especificacao;
        this.prazoT = prazoT;
        this.modRef = modRef;
        this.precoAbertura = Integer.parseInt(precoAbertura);
        this.precoMax = Integer.parseInt(precoMax);
        this.precoMin = Integer.parseInt(precoMin);
        this.precoMed = Integer.parseInt(precoMed);
        this.precoUlt = Integer.parseInt(precoUlt);
        this.precoOfc = Integer.parseInt(precoOfc);
        this.precoOfv = Integer.parseInt(precoOfv);
        this.totNeg = Integer.parseInt(totNeg);
        this.quantTot = Long.parseLong(quantTot);
        this.volumeTotal = Long.parseLong(volumeTotal);
        this.preEx = Integer.parseInt(preEx);
        this.indOpc = Integer.parseInt(indOpc);
        this.dataVencimento = Integer.parseInt(dataVencimento);
        this.fatCot = Integer.parseInt(fatCot);
        this.ptoexe = Integer.parseInt(ptoexe);
        this.codISIN = codISIN;
        this.disMes = Integer.parseInt(disMes);
        this.dataCompar = this.dataPregao;
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public int getDataPregao() {
        return dataPregao;
    }

    public int getCodBDI() {
        return codBDI;
    }

    public String getCodNEG() {
        return codNEG;
    }

    public int getTipoMerc() {
        return tipoMerc;
    }

    public String getNomeRes() {
        return nomeRes;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public String getPrazoT() {
        return prazoT;
    }

    public String getModRef() {
        return modRef;
    }

    public int getPrecoAbertura() {
        return precoAbertura;
    }

    public int getPrecoMax() {
        return precoMax;
    }

    public int getPrecoMin() {
        return precoMin;
    }

    public int getPrecoMed() {
        return precoMed;
    }

    public int getPrecoUlt() {
        return precoUlt;
    }

    public int getPrecoOfc() {
        return precoOfc;
    }

    public int getPrecoOfv() {
        return precoOfv;
    }

    public int getTotNeg() {
        return totNeg;
    }

    public long getQuantTot() {
        return quantTot;
    }

    public long getVolumeTotal() {
        return volumeTotal;
    }

    public int getPreEx() {
        return preEx;
    }

    public int getIndOpc() {
        return indOpc;
    }

    public int getDataVencimento() {
        return dataVencimento;
    }

    public int getFatCot() {
        return fatCot;
    }

    public int getPtoexe() {
        return ptoexe;
    }

    public String getCodISIN() {
        return codISIN;
    }

    public int getDisMes() {
        return disMes;
    }

    @Override
    public int compareTo(Registro t) {
        return this.dataCompar.compareTo(t.dataCompar);
    }
}
