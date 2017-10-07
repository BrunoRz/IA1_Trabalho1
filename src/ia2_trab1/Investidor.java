package ia2_trab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Alessandra
 */
public class Investidor {
    ArrayList<Empresa> listaEmpresas;
    
    public void setListaEmpresas(ArrayList<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public ArrayList<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
    
    public ArrayList<Float> calculaProbabilidades(Empresa empresa) {
        ArrayList<Float> probabilidades = new ArrayList<>();
        // probabilidades[probSubida, probDescida, probCompra, probVenda]
        
        int numReg = empresa.registro.size(), cont = 0, qtdeSubida = 0, qtdeDescida = 0;
        float valorVenda = 0f, totalMedio = 0f, totalValorVenda = 0f,
            totalTotNeg = 0f, totalQuaTot = 0f, totalVolTot = 0f;
        float probSubida, probDescida, probCompra, probVenda, mediaVenda, valorMedio;
                    
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

            probSubida = (float) qtdeSubida/numReg;
            probDescida = (float) qtdeDescida/numReg;
            probCompra = (float) totalTotNeg/totalQuaTot + valorMedio;
            probVenda = (float) totalQuaTot/totalVolTot + valorMedio;

            probabilidades.add(probSubida);
            probabilidades.add(probDescida);
            probabilidades.add(probCompra);
            probabilidades.add(probVenda);
        }
//        probabilidades.forEach((p) -> {
//            System.out.println("Probs: " + p);
//        });
        return probabilidades;
    }
    
    public boolean sucesso(Float prob) {
        return new Random().nextInt(101) > prob;
    }
    
    public Float probCond(Float probA, Float probB) {
        return (probA * probB)/probB;
    }
    
    public ArrayList<Float> atualizaSubDesc(ArrayList<Float> probabilidades, Integer SD) {
        switch (SD) {
            case 0: {
                for (int i = 0; i < 4; i++)
                    probabilidades.set(i, probCond(probabilidades.get(i), probabilidades.get(0)));
                break;
            }
            case 1: {
                for (int i = 0; i < 4; i++) 
                    probabilidades.set(i, probCond(probabilidades.get(i), probabilidades.get(1)));
                break;
            }
            default: {
                for (int i = 0; i < 4; i++)
                    probabilidades.set(i, probCond(probabilidades.get(i), 1 - (probabilidades.get(0) + probabilidades.get(1))));
            }
        }
        return probabilidades;
    }
        
    public Float predicao(Empresa empresa, ArrayList<Float> probabilidades) throws Exception {
        int nroAcoes = 0; 
        float saldo = 0.0f;
        boolean bom, regular, ruim, comprou, vendeu;
        Leitor l = new Leitor();
        
        empresa.registro = l.interpretar(l.getHistoricoEmpresa(empresa.getNome(), 1996));
        Arrays.sort(empresa.registro.toArray());
        
        for (Registro r : empresa.registro) {
            comprou = sucesso(probabilidades.get(2));
            vendeu = sucesso(probabilidades.get(3));
            if (probabilidades.get(0) > probabilidades.get(1)) {
                bom = true;
                regular = false;
                ruim = false;
            } else if (probabilidades.get(0) < probabilidades.get(1)) {
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
                    saldo -= (float) r.precoOfc;
                    atualizaSubDesc(probabilidades, 0);
                } else if (regular && comprou) {
                    if (saldo >= 0){
                        nroAcoes++;
                        saldo -= (float) r.precoOfc;
                        atualizaSubDesc(probabilidades, 0);
                    } else {
                        atualizaSubDesc(probabilidades, -1);
                    }
                } else if (ruim) {
                    atualizaSubDesc(probabilidades, 1);
                } else {
                    atualizaSubDesc(probabilidades, -1);
                }
            } else {
                if (bom) {
                    if (comprou) {
                        if (vendeu) {
                            saldo += (float) r.precoOfv;
                            nroAcoes--;
                        }
                        saldo -= (float) r.precoOfc;
                        nroAcoes++;
                    } else {
                        if (vendeu) {
                            saldo += (float) r.precoOfv;
                            nroAcoes--;
                        }
                    }
                    atualizaSubDesc(probabilidades, 0);
                } else if (ruim) {
                    if (vendeu) {
                        saldo += (float) r.precoOfv;
                        nroAcoes--;
                    }
                    atualizaSubDesc(probabilidades, 1);
                } else {
                    if (comprou) {
                        if (vendeu) {
                            saldo += (float) r.precoMed;
                            nroAcoes--;
                        }
                        saldo -= (float) r.precoMed;
                        nroAcoes++;
                    } else {
                        if (vendeu) {
                            saldo += (float) r.precoMed;
                            nroAcoes--;
                        }
                    }
                    atualizaSubDesc(probabilidades, -1);
                }
            }
        }
        return saldo;
    }
    
    public Float analise(Empresa empresa) {
        float saldo = 0f;
        
        for (Registro r : empresa.registro) {
            saldo += r.precoOfv;
            saldo -= r.precoOfc;
        }
        
        return saldo;
    }
}