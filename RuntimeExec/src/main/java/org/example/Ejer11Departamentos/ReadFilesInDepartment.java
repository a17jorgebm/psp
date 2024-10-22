package org.example.Ejer11Departamentos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.list;

/*
Make a program that goes through all the files in the departments directory calculating the sum
 of the values stored in each of the files and creates a report in a file with this information.

The program must create one process for each file. The code for each process must be the same.
 */
public class ReadFilesInDepartment {
    private static final String FORMATO_SAIDA_PROCESO="%s\n==================\n%.2f\n==================\n\n";
    private static final Path DEPARTMENT_DIRECTORY=Paths.get("departments");
    private static final Path REPORT_FILE=Paths.get("report.txt");

    public static void main(String[] args) {
        if (!Files.exists(DEPARTMENT_DIRECTORY)) {
            System.out.println("Non existe o directorio");
            System.exit(0);
        }

        try {
            //pillo todos o ficheiros do directorio filtrandoos polo nome
            List<Path> ficheiros= Files.list(DEPARTMENT_DIRECTORY)
                    .filter((p) -> {
                        Matcher regex=Pattern.compile("department[0-9]+\\.txt$").matcher(p.getFileName().toString());
                        return regex.find();
                    })
                    .collect(Collectors.toList());
            //inicio os procesos e metoos nunha coleccion xunto co nome do ficheiro
            ProcessBuilder pBuilder=new ProcessBuilder();
            LinkedHashMap<String,Process> procesos=new LinkedHashMap<>();
            for (Path f: ficheiros){
                String relativeFileRoute=f.getParent().getFileName().toString() +"/"+ f.getFileName().toString();
                pBuilder.command("java","-cp","target/classes","org.example.Ejer11Departamentos.SumValuesInFile",relativeFileRoute);
                Process process=pBuilder.start();
                procesos.put(f.getFileName().toString(),process);
            }
            //leo as salidas dos procesos e metoos no ficheiro
            BufferedWriter outputReport=new BufferedWriter(new FileWriter(DEPARTMENT_DIRECTORY.resolve(REPORT_FILE).toFile()));
            int claveProcesos=0; //para acceder ao nombre de arquivo
            for (Process proceso:procesos.values()){
                try(
                    BufferedReader readerProceso=new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                    BufferedReader readerErros=new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                ){
                    proceso.waitFor();
                    String linea;
                    if (proceso.exitValue()==0){
                        outputReport.write(String.format(
                                Locale.US, //pa que poña punto e non coma no decimal
                                FORMATO_SAIDA_PROCESO, //formato
                                procesos.keySet().toArray()[claveProcesos++], //pillo o nombre do arquivo
                                Double.valueOf(readerProceso.readLine())
                                )
                        );
                    }else {
                        while ((linea=readerErros.readLine())!=null){
                            System.out.println(linea);
                        }
                    }
                }catch (InterruptedException e){

                }
            }
            outputReport.close(); //importante cerrar esto
            System.out.println("Report generation completed");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
