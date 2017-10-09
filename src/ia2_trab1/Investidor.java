package ia2_trab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import viul.ListaEmpresas;

/**
 *
 * @author Alessandra
 */
public class Investidor {
    ArrayList<Empresa> listaEmpresas = new ArrayList<>();
    
    public void setListaEmpresas(ArrayList<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public ArrayList<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
    
    public void calcularProbabilidades(Empresa empresa) {
        int numReg = empresa.registro.size(), cont = 0, qtdeSubida = 0, qtdeDescida = 0;
        float valorVenda = 0f, totalMedio = 0f, totalValorVenda = 0f,
            totalTotNeg = 0f, totalQuaTot = 0f, totalVolTot = 0f,
            mediaVenda, valorMedio;
                    
        if (numReg > 0) {
            Arrays.sort(empresa.registro.toArray());                
            for (Registro r : empresa.registro) {
                totalMedio += (float) r.getPrecoMed();
                totalTotNeg += (float) r.getTotNeg();
                totalQuaTot += (float) r.getQuantTot();
                totalVolTot += (float) r.getVolumeTotal();
                if (cont > 0)
                    if (r.getPrecoOfv() > valorVenda)
                        qtdeSubida++;
                    else if (r.getPrecoOfv() < valorVenda)
                        qtdeDescida++;
                valorVenda = r.getPrecoOfv();
                totalValorVenda += valorVenda;
                cont++;
            }
            mediaVenda = (float) totalValorVenda/numReg;
            valorMedio = (float) mediaVenda/(totalMedio/numReg);
            empresa.setProbSubida((float) qtdeSubida/numReg);
            empresa.setProbDescida((float) qtdeDescida/numReg);
            empresa.setProbCompra((float) totalTotNeg/totalQuaTot + valorMedio);
            empresa.setProbVenda((float) totalQuaTot/totalVolTot + valorMedio);
        }
    }
    
    public boolean sucesso(Float prob) {
        return new Random().nextInt(101) > prob;
    }
    
    public Float probCondicional(Float probA, Float probB) {
        return (probA * probB)/probB;
    }
    
    public void atualizarSubDesc(Empresa e, Integer SD) {
        Float prob;
        switch (SD) {
            case 0: {
                prob = e.getProbSubida();
                break;
            }
            case 1: {
                prob = e.getProbDescida();
                break;
            }
            default: {
                prob = 1 - (e.getProbSubida() + e.getProbDescida());
            }
        }        
        e.setProbSubida(probCondicional(e.getProbSubida(), prob));
        e.setProbDescida(probCondicional(e.getProbDescida(), prob));
        e.setProbCompra(probCondicional(e.getProbCompra(), prob));
        e.setProbVenda(probCondicional(e.getProbVenda(), prob));
    }
        
    public float[] predicao(Empresa e) throws Exception {
        float[] predicao = new float[3]; // [comprou, vendeu, saldo]
        int nroAcoes = 0;
        boolean bom, regular, ruim, comprou, vendeu;
        Leitor l = new Leitor();
        
        e.registro = l.interpretar(l.getHistoricoEmpresa(e.getNome(), 1996));
        Arrays.sort(e.registro.toArray());
        
        for (Registro r : e.registro) {
            if (comprou = sucesso(e.getProbCompra()))
                predicao[0] += 1;
            if (vendeu = sucesso(e.getProbVenda()))
                predicao[1] += 1;
            if (e.getProbSubida() > e.getProbDescida()) {
                bom = true;
                regular = false;
                ruim = false;
            } else if (e.getProbSubida() < e.getProbDescida()) {
                bom = false;
                regular = false;
                ruim = true;
            } else {
                bom = false;
                regular = true;
                ruim = false;
            }
            
            if (nroAcoes == 0) {
                if (bom && comprou) {
                    nroAcoes++;
                    predicao[2] -= (float) r.precoOfc;
                    atualizarSubDesc(e, 0);
                } else if (regular && comprou) {
                    if (predicao[2] >= 0){
                        nroAcoes++;
                        predicao[2] -= (float) r.precoOfc;
                        atualizarSubDesc(e, 0);
                    } else {
                        atualizarSubDesc(e, -1);
                    }
                } else if (ruim) {
                    atualizarSubDesc(e, 1);
                } else {
                    atualizarSubDesc(e, -1);
                }
            } else {
                if (bom) {
                    if (comprou) {
                        if (vendeu) {
                            predicao[2] += (float) r.precoOfv;
                            nroAcoes--;
                        }
                        predicao[2] -= (float) r.precoOfc;
                        nroAcoes++;
                    } else {
                        if (vendeu) {
                            predicao[2] += (float) r.precoOfv;
                            nroAcoes--;
                        }
                    }
                    atualizarSubDesc(e, 0);
                } else if (ruim) {
                    if (vendeu) {
                        predicao[2] += (float) r.precoOfv;
                        nroAcoes--;
                    }
                    atualizarSubDesc(e, 1);
                } else {
                    if (comprou) {
                        if (vendeu) {
                            predicao[2] += (float) r.precoMed;
                            nroAcoes--;
                        }
                        predicao[2] -= (float) r.precoMed;
                        nroAcoes++;
                    } else {
                        if (vendeu) {
                            predicao[2] += (float) r.precoMed;
                            nroAcoes--;
                        }
                    }
                    atualizarSubDesc(e, -1);
                }
            }
        }
        return predicao;
    }
    
    public Float analise(Empresa empresa) {
        float saldo = 0f;
        int nroAcoes = 0;
        
        for (Registro r : empresa.registro) {
            if (nroAcoes > 0) {
                saldo += r.precoOfv;
                saldo -= r.precoOfc;
            } else {
                saldo -= r.precoOfc;
                nroAcoes++;
            }
        }
        
        return saldo;
    }
}