package ia2_trab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Bruno
 */
public class Leitor {
 
    public ArrayList<String> readHist(String symbol, int ano) throws Exception {
        String filePath = "C:\\Users\\Alessandra\\Documents\\GitHub\\IA2_Trabalho01\\src\\cotahist\\COTAHIST.A",
               fileYear = String.valueOf(ano);
        List<String> histValues = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath + fileYear))) {
            histValues = stream
                .filter(line -> line.contains(symbol))
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading file."); throw e;
        }
        return (ArrayList<String>) histValues;
    }
    
    public ArrayList<Registro> interpretar(ArrayList<String> histValues){
        ArrayList<Registro> registros = new ArrayList<>();
        histValues.forEach((h) -> {
            registros.add(new Registro(
                    h.substring(0, 2),
                    h.substring(2, 10),
                    h.substring(10, 12),
                    h.substring(12, 24),
                    h.substring(24, 27),
                    h.substring(27, 39),
                    h.substring(39, 49),
                    h.substring(49, 52),
                    h.substring(52, 56),
                    h.substring(56, 69),
                    h.substring(69, 82),
                    h.substring(82, 95),
                    h.substring(95, 108),
                    h.substring(108, 121),
                    h.substring(121, 134),
                    h.substring(134, 147),
                    h.substring(147, 152),
                    h.substring(152, 170),
                    h.substring(170, 188),
                    h.substring(188, 201),
                    h.substring(201, 202),
                    h.substring(202, 210),
                    h.substring(210, 217),
                    h.substring(217, 230),
                    h.substring(230, 242),
                    h.substring(242, 245)));
        });
        return registros;
    }
}