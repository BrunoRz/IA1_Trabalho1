package ia2_trab1;

import java.util.ArrayList;
import java.util.Arrays;

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
        probabilidades.forEach((p) -> {
            System.out.println("Probs: " + p);
        });
        return probabilidades;
    }
    
    public Float probCond(Float probA, Float probB) {
        return (probA * probB)/probB;
    }
    
    public ArrayList<Float> atualizaProb(ArrayList<Float> probabilidades, Integer SD, int CV) {
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                probabilidades.set(i, probCond(probabilidades.get(i), probabilidades.get(SD)));
            } else {
                probabilidades.set(i, probCond(probabilidades.get(i), probabilidades.get(CV)));
            }
        }
        return probabilidades;
    }
    
    public Float predicao(Empresa empresa, ArrayList<Float> probabilidades) throws Exception {
        int nroAcoes = 0; 
        float saldo = 0.0f;
        
        Leitor l = new Leitor();
        
        empresa.registro = l.interpretar(l.getHistoricoEmpresa(empresa.getNome(), 1996));
        
        Arrays.sort(empresa.registro.toArray());
        
        for (Registro r : empresa.registro) {
            
        }
        System.out.println(nroAcoes);
        System.out.println("SALDO: " + saldo);
        return saldo;
    }
    
}