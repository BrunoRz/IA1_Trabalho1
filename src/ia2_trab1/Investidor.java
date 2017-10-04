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
        
        empresas.forEach((Empresa e) -> {
            int numReg, cont = 0;
            int valorVenda = 0;
            int qtdeSubida = 0, qtdeDescida = 0;
            float probSubida = 0f, probDescida = 0f, probCompra = 0f, probVenda = 0f;
            
            numReg = e.registro.size();
            
            Arrays.sort(e.registro.toArray());
            
            for (Registro r : e.registro) {
                if (cont == 0) {
                    valorVenda = r.getPrecoOfv();
                } else if (cont < numReg) {
                    if (r.getPrecoOfv() > valorVenda) {
                        qtdeSubida++;
                    } else if (r.getPrecoOfv() < valorVenda) {
                        qtdeDescida++;
                    }
                    valorVenda = r.getPrecoOfv();
                }
                cont++;
            }
            
            probSubida = qtdeSubida/numReg;
            probDescida = qtdeDescida/numReg;
            
            // falta calcular probCompra e probVenda
        });
        
        
        return probabilidades;
    }
}
