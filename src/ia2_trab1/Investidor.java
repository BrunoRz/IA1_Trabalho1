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
        
        int numReg = empresa.registro.size(), cont = 0,
            valorVenda = 0, totalMedio = 0, totalValorVenda = 0,
            qtdeSubida = 0, qtdeDescida = 0, totalTotNeg = 0, totalQuaTot = 0, totalVolTot = 0;
        float probSubida, probDescida, probCompra, probVenda, mediaVenda, valorMedio;
            
        if (numReg > 0) {
            Arrays.sort(empresa.registro.toArray());                
            for (Registro r : empresa.registro) {
                totalMedio += r.getPrecoMed();
                totalTotNeg += r.getTotNeg();
                totalQuaTot += r.getQuantTot();
                totalVolTot += r.getVolumeTotal();
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
        return probabilidades;
    }
}