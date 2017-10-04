/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia2_trab1;

import ia2_trab1.Empresa;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alessandra
 */
public class Investidor {
    ArrayList<Empresa> eeer;
    
    public void setEeer(ArrayList<Empresa> eeer) {
        this.eeer = eeer;
    }
    
    public ArrayList<Float> calculaProbabilidades(ArrayList<Empresa> empresas) {
        ArrayList<Float> probabilidades = new ArrayList<>();
        // probabilidades[i]: probSubida;
        // probabilidades[i+1]: probDescida;
        // probabilidades[i+2]: probCompra;
        // probabilidades[i+3]: probVenda.
        
        for (Empresa e: empresas) {
            int numReg, cont = 0;
            int valorVenda = 0, totalMedio = 0, totalValorVenda = 0;
            int qtdeSubida = 0, qtdeDescida = 0, totalTotNeg = 0, totalQuaTot = 0, totalVolTot = 0;
            float probSubida, probDescida, probCompra, probVenda, mediaVenda, valorMedio;
            
            numReg = e.registro.size();
            
            Arrays.sort(e.registro.toArray());
            
            for (Registro r : e.registro) {
                totalMedio += r.getPrecoMed();
                totalTotNeg += r.getTotNeg();
                totalQuaTot += r.getQuantTot();
                totalVolTot += r.getVolumeTotal();
                if (cont == 0) {
                    valorVenda = r.getPrecoOfv();
                } else {
                    if (r.getPrecoOfv() > valorVenda) {
                        qtdeSubida++;
                    } else if (r.getPrecoOfv() < valorVenda) {
                        qtdeDescida++;
                    }
                    valorVenda = r.getPrecoOfv();
                }
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
        
        
        return probabilidades;
    }
}
