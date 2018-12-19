
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interpreter {
    protected Memory memory;
    protected FileSystem fileSystem;
    protected Procesor procesor; 
    protected HardDrive hardDrive;
    protected ProcessMenager processMenager;


    private int numberOfArgs(String order){
        switch(order){
            case "ADD": return 2; //dodawanie
            case "SUB": return 2; //odejmowanie
            case "MUL": return 2; //mnożenie
            case "DIV": return 2; //dzielenie            
            case "INC": return 1; //inkrementacja
            case "DEC": return 1; //dekrementacja     
            
            case "MOV": return 2; //przeniesienie do rejestru
            case "SHR": return 0; //pokaż rejestry
            
            //case "CLS": return 0; //wyczyść ekran
            case "SV": return 0; //wartości semaforow
            case "PQ": return 1; //kolejka procesów
            case "NQP": return 1; //liczba oczekujących procesów
            
            case "FL": return 0; //pokaż listę plików
            case "SD": return 0; //pokaż dysk
            case "SFS": return 0; //pokaż wolne miejsce
            case "CF": return 1; //stwórz plik
            case "CFD": return 2; //stwórz plik z danymi
            case "OF": return 1; //otwórz plik
            case "EF": return 1; //zamknij plik
            case "DF": return 1; //usuń plik
            case "WF": return 2; //napisz do pliku
            case "ED": return 2; //edytuj plik
            case "RF": return 1; //czytaj z pliku
            case "RNF": return 2; //zmiana nazwy pliku
            case "FOR": return 0; //format dysku
            case "DI": return 1; //pokaż informacje o pliku
            
            case "CP": return 2; //stwórz proces
            case "KP": return 1; //zabij proces
            case "DT": return 0; //narysuj drzewo procesów
            case "GRP": return 0; //pokaż wykonywany proces
            case "SRP": return 0; //zatrzymaj wykonywany proces
            case "GPS": return 1; //pokaż stan procesu
            
            case "RM": return 1; //czytaj pamięć
            //case "WM": return 1; //nadpisz pamięć
            case "DFQ": return 0; //pokaż kolejkę FIFO
            case "DPS": return 0; //pokaż stronice
            case "DFT": return 0; //pokaż ramki

            case "RPQ": return 0; //pokaż kolejkę gotowych procesów
            
            case "MT": return 0; //utwórz etykietę
            case "JZ": return 0; //przejdź do etykiety, jeżeli flaga zera
            case "JP": return 1; //skok bezwarunkowy
            
            
            default: return -1;
        }
    }
    
    private String removeBracket(String a) throws Exception{
        if(a.charAt(0)!='[' || a.charAt(a.length()-1)!= ']') throw new Exception("Argument exception");
        return a.substring(1, a.length());
    }
    
    private char readMemory(int place){
        return memory.pobierzZnak(place);        
    }

    private void execute(String... stream) throws Exception{
        int numberOfArgs = numberOfArgs(stream[0]);
        
        if(numberOfArgs == -1) throw new Exception("Incorrect command");
        if(numberOfArgs == stream.length - 1) throw new Exception ("Incorrect syntax");
        
        String command = stream[0];
        
        switch(command){
            case "ADD": {
                int value = 0;
                
                if(stream[2].charAt(0)=='['){
                    char memoryChar = readMemory(Integer.parseInt(removeBracket(stream[2])));
                    
                    try{value = Character.getNumericValue(memoryChar);}
                    catch(Exception e){System.out.println("Cast exception");}
                
                }
                else{
                    try{value = Integer.parseInt(stream[2]);}
                    catch(Exception e){System.out.println("Incorrect syntax");}
                }
                
                switch(stream[1]){
                    case "A": {procesor.A+=value;break;}
                    case "B": {procesor.B+=value;break;}
                    case "C": {procesor.C+=value;break;}
                    case "D": {procesor.D+=value;break;}
                    case "E": {procesor.E+=value;break;}
                    case "F": {procesor.F+=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case "SUB": {
                int value = 0;
                
                if(stream[2].charAt(0)=='['){
                    char memoryChar = readMemory(Integer.parseInt(removeBracket(stream[2])));
                    
                    try{value = Character.getNumericValue(memoryChar);}
                    catch(Exception e){System.out.println("Cast exception");}
                
                }
                else{
                    try{value = Integer.parseInt(stream[2]);}
                    catch(Exception e){System.out.println("Incorrect syntax");}
                }
                
                switch(stream[1]){
                    case "A": {procesor.A-=value;break;}
                    case "B": {procesor.B-=value;break;}
                    case "C": {procesor.C-=value;break;}
                    case "D": {procesor.D-=value;break;}
                    case "E": {procesor.E-=value;break;}
                    case "F": {procesor.F-=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   

                break;
            }
            case "MUL": {
                int value = 0;
                
                if(stream[2].charAt(0)=='['){
                    char memoryChar = readMemory(Integer.parseInt(removeBracket(stream[2])));
                    
                    try{value = Character.getNumericValue(memoryChar);}
                    catch(Exception e){System.out.println("Cast exception");}
                
                }
                else{
                    try{value = Integer.parseInt(stream[2]);}
                    catch(Exception e){System.out.println("Incorrect syntax");}
                }
                
                switch(stream[1]){
                    case "A": {procesor.A*=value;break;}
                    case "B": {procesor.B*=value;break;}
                    case "C": {procesor.C*=value;break;}
                    case "D": {procesor.D*=value;break;}
                    case "E": {procesor.E*=value;break;}
                    case "F": {procesor.F*=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case "DIV": {
                int value = 0;
                
                if(stream[2].charAt(0)=='['){
                    char memoryChar = readMemory(Integer.parseInt(removeBracket(stream[2])));
                    
                    try{value = Character.getNumericValue(memoryChar);}
                    catch(Exception e){System.out.println("Cast exception");}
                
                }
                else{
                    try{value = Integer.parseInt(stream[2]);}
                    catch(Exception e){System.out.println("Incorrect syntax");}
                }
                
                switch(stream[1]){
                    case "A": {procesor.A*=value;break;}
                    case "B": {procesor.B*=value;break;}
                    case "C": {procesor.C*=value;break;}
                    case "D": {procesor.D*=value;break;}
                    case "E": {procesor.E*=value;break;}
                    case "F": {procesor.F*=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }          
            case "INC": {
                switch(stream[1]){
                    case "A": {procesor.A+=1;break;}
                    case "B": {procesor.B+=1;break;}
                    case "C": {procesor.C+=1;break;}
                    case "D": {procesor.D+=1;break;}
                    case "E": {procesor.E+=1;break;}
                    case "F": {procesor.F+=1;break;}
                    default: {System.out.println("Incorrect syntax");}
                } 
                
                break;
            }
            case "DEC": {
                switch(stream[1]){
                    case "A": {procesor.A-=1;break;}
                    case "B": {procesor.B-=1;break;}
                    case "C": {procesor.C-=1;break;}
                    case "D": {procesor.D-=1;break;}
                    case "E": {procesor.E-=1;break;}
                    case "F": {procesor.F-=1;break;}
                    default: {System.out.println("Incorrect syntax");}
                } 
                
                break;
            }           
            case "MOV": {
                int value = 0;
                
                if(stream[2].charAt(0)=='['){
                    char memoryChar = readMemory(Integer.parseInt(removeBracket(stream[2])));
                    
                    try{value = Character.getNumericValue(memoryChar);}
                    catch(Exception e){System.out.println("Cast exception");}                
                }
                else{
                    try{value = Integer.parseInt(stream[2]);}
                    catch(Exception e){System.out.println("Incorrect syntax");}
                }
                
                switch(stream[1]){
                    case "A": {procesor.A=value;break;}
                    case "B": {procesor.B=value;break;}
                    case "C": {procesor.C=value;break;}
                    case "D": {procesor.D=value;break;}
                    case "E": {procesor.E=value;break;}
                    case "F": {procesor.F=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case "SHR": {
                System.out.println("Registers values:");
                System.out.println("A: " + procesor.A);
                System.out.println("B: " + procesor.B);
                System.out.println("C: " + procesor.C);
                System.out.println("D: " + procesor.D);
                System.out.println("E: " + procesor.E);
                System.out.println("F: " + procesor.F);
                break;
            }            
            case "CF": {
                hardDrive.CreateFile(stream[1]);
                break;
            }
            case "CFD": {
                hardDrive.CreateFile(stream[1], stream[2]);
                break;
            }
            case "OF": {
                
                break;
            }
            case "EF": {
                
                break;
            }
            case "SD": {

                break;
            }
            case "FL": {
                hardDrive.ShowFileList();
                break;
            }
            case "SFS": {
                hardDrive.ShowFreeSpace();
                break;
            }
            case "DF": {
                hardDrive.DeleteFile(stream[1]);
                break;
            }
            case "WF": {
                hardDrive.EditFile(stream[1], stream[2]);
                break;
            }
            case "ED": {
                
                break;
            }
            case "RF": {
                hardDrive.ShowFile(stream[1]);
                break;
            }
            case "RNF": {
                hardDrive.Rename(stream[1], stream[2]);
                break;
            }        
            case "FOR":{
                hardDrive.FormatDrive();
		break;
            }
            case "DI": {
                
		break;
            }
            case "CP": {
                processMenager.stworzProces(stream[1], Integer.parseInt(stream[2]));
                break;
            }
            case "KP": {
               
                break;
            }    
            case "DT": {
               
                break;
            }
            case "GRP": {
               
                break;
            }
            case "SRP": {
               
                break;
            }
            case "GPS": {
               
                break;
            }
            case "RM": {
                System.out.println(readMemory(Integer.parseInt(removeBracket(stream[1]))));
                break;
            }
            /*case "WM": {
                memory.Zapisz_do_pamieci(Integer.parseInt(stream[1]), stream[2]);
                break;
            }*/ 
            case "DFQ": {
                
                break;
            }
            case "DPS": {
                
                break;
            }
            case "DFT": {
                
                break;
            }
            case "MT": {
                
                break;
            }
            case "JZ": {
                
                break;
            }
            case "JP": {
                
                break;
            }            
            default: break;
        }
    }
}
