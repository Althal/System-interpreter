package javaapplication17;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Interpreter {
    protected Memory memory;
    protected HardDrive hardDrive;
    protected ProcessManager processManager;

    public Interpreter(Memory memory, HardDrive hardDrive, ProcessManager processManager) {
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.processManager = processManager;
    }

    private int getNumber(String order){
        if(order.equals("ADD")) return 1;
        else if(order.equals("SUB")) return 2;
        else if(order.equals("MUL")) return 3;
        else if(order.equals("DIV")) return 4;
        else if(order.equals("INC")) return 5;
        else if(order.equals("DEC")) return 6;
        else if(order.equals("MOV")) return 7;
        else if(order.equals("SHR")) return 8;
        else if(order.equals("SV")) return 9;
        else if(order.equals("PQ")) return 10;
        else if(order.equals("NQP")) return 11;
        else if(order.equals("SL")) return 12;
        else if(order.equals("SD")) return 13;
        else if(order.equals("SFS")) return 14;
        else if(order.equals("CF")) return 15;
        else if(order.equals("CFD")) return 16;
        else if(order.equals("OF")) return 17;
        else if(order.equals("EF")) return 18;
        else if(order.equals("DF")) return 19;
        else if(order.equals("WF")) return 20;
        else if(order.equals("ED")) return 21;
        else if(order.equals("RF")) return 22;
        else if(order.equals("RNF")) return 23;
        else if(order.equals("FOR")) return 24;
        else if(order.equals("DI")) return 25;
        else if(order.equals("CP")) return 26;
        else if(order.equals("KP")) return 27;
        else if(order.equals("DT")) return 28;
        else if(order.equals("GRP")) return 29;
        else if(order.equals("SRP")) return 30;
        else if(order.equals("GPS")) return 31;
        else if(order.equals("RM")) return 32;
        else if(order.equals("DFQ")) return 33;
        else if(order.equals("DPS")) return 34;
        else if(order.equals("DFT")) return 35;
        else if(order.equals("SHM")) return 36;
        else if(order.equals("RPQ")) return 37;
        else if(order.equals("MT")) return 38;
        else if(order.equals("JZ")) return 39;
        else if(order.equals("JP")) return 40;
        
        return -1;
    }
    private int numberOfArgs(String order){
        int ord = getNumber(order);
        switch(ord){
            case 1: return 2; //dodawanie
            case 2: return 2; //odejmowanie
            case 3: return 2; //mnożenie
            case 4: return 2; //dzielenie            
            case 5: return 1; //inkrementacja
            case 6: return 1; //dekrementacja     
            
            case 7: return 2; //przeniesienie do rejestru
            case 8: return 0; //pokaż rejestry
            
            //case "CLS": return 0; //wyczyść ekran
            case 9: return 0; //wartości semaforow
            case 10: return 1; //kolejka procesów
            case 11: return 1; //liczba oczekujących procesów
            
            case 12: return 0; //pokaż listę plików
            case 13: return 0; //pokaż dysk
            case 14: return 0; //pokaż wolne miejsce
            case 15: return 1; //stwórz plik
            case 16: return 2; //stwórz plik z danymi
            case 17: return 1; //otwórz plik
            case 18: return 1; //zamknij plik
            case 19: return 1; //usuń plik
            case 20: return 2; //napisz do pliku
            case 21: return 2; //edytuj plik
            case 22: return 1; //czytaj z pliku
            case 23: return 2; //zmiana nazwy pliku
            case 24: return 0; //format dysku
            case 25: return 1; //pokaż informacje o pliku
            
            case 26: return 2; //stwórz proces
            case 27: return 1; //zabij proces
            case 28: return 1; //narysuj drzewo procesów
            case 29: return 0; //pokaż wykonywany proces
            case 30: return 0; //zatrzymaj wykonywany proces
            case 31: return 1; //pokaż stan procesu
            
            case 32: return 1; //czytaj pamięć
            //case "WM": return 1; //nadpisz pamięć
            case 33: return 0; //pokaż kolejkę FIFO
            case 34: return 1; //pokaż stronice
            case 35: return 0; //pokaż ramki
	    case 36: return 0; //pokaż pamiec

            case 37: return 0; //pokaż kolejkę gotowych procesów
            
            case 38: return 0; //utwórz etykietę
            case 39: return 0; //przejdź do etykiety, jeżeli flaga zera
            case 40: return 1; //skok bezwarunkowy
            
            
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

    public void execute(String[] stream) throws Exception{
        int numberOfArgs = numberOfArgs(stream[0]);
        
        if(numberOfArgs == -1) throw new Exception("Incorrect command");
        if(numberOfArgs != (stream.length - 1)) throw new Exception ("Incorrect syntax");
        
        int command = getNumber(stream[0]);
        
        switch(command){
            case 1: {
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
                    case "A": {Procesor.A+=value;break;}
                    case "B": {Procesor.B+=value;break;}
                    case "C": {Procesor.C+=value;break;}
                    case "D": {Procesor.D+=value;break;}
                    case "E": {Procesor.E+=value;break;}
                    case "F": {Procesor.F+=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case 2: {
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
                    case "A": {Procesor.A-=value;break;}
                    case "B": {Procesor.B-=value;break;}
                    case "C": {Procesor.C-=value;break;}
                    case "D": {Procesor.D-=value;break;}
                    case "E": {Procesor.E-=value;break;}
                    case "F": {Procesor.F-=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   

                break;
            }
            case 3: {
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
                    case "A": {Procesor.A*=value;break;}
                    case "B": {Procesor.B*=value;break;}
                    case "C": {Procesor.C*=value;break;}
                    case "D": {Procesor.D*=value;break;}
                    case "E": {Procesor.E*=value;break;}
                    case "F": {Procesor.F*=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case 4: {
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
                    case "A": {Procesor.A*=value;break;}
                    case "B": {Procesor.B*=value;break;}
                    case "C": {Procesor.C*=value;break;}
                    case "D": {Procesor.D*=value;break;}
                    case "E": {Procesor.E*=value;break;}
                    case "F": {Procesor.F*=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }          
            case 5: {
                switch(stream[1]){
                    case "A": {Procesor.A+=1;break;}
                    case "B": {Procesor.B+=1;break;}
                    case "C": {Procesor.C+=1;break;}
                    case "D": {Procesor.D+=1;break;}
                    case "E": {Procesor.E+=1;break;}
                    case "F": {Procesor.F+=1;break;}
                    default: {System.out.println("Incorrect syntax");}
                } 
                
                break;
            }
            case 6: {
                switch(stream[1]){
                    case "A": {Procesor.A-=1;break;}
                    case "B": {Procesor.B-=1;break;}
                    case "C": {Procesor.C-=1;break;}
                    case "D": {Procesor.D-=1;break;}
                    case "E": {Procesor.E-=1;break;}
                    case "F": {Procesor.F-=1;break;}
                    default: {System.out.println("Incorrect syntax");}
                } 
                
                break;
            }           
            case 7: {
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
                    case "A": {Procesor.A=value;break;}
                    case "B": {Procesor.B=value;break;}
                    case "C": {Procesor.C=value;break;}
                    case "D": {Procesor.D=value;break;}
                    case "E": {Procesor.E=value;break;}
                    case "F": {Procesor.F=value;break;}
                    default: {System.out.println("Incorrect syntax");}
                }   
                
                break;
            }
            case 8: {
                System.out.println("Registers values:");
                System.out.println("A: " + Procesor.A);
                System.out.println("B: " + Procesor.B);
                System.out.println("C: " + Procesor.C);
                System.out.println("D: " + Procesor.D);
                System.out.println("E: " + Procesor.E);
                System.out.println("F: " + Procesor.F);
                break;
            }
            case 9:{
                System.out.println(hardDrive.semaphore.getValue());
                break;
            }
            case 10:{
                hardDrive.semaphore.showQueue();
                break;
            }
            case 11:{
                System.out.println(hardDrive.semaphore.getNumberOfQueuedProcesses());
                break;
            }
            case 15: {
                hardDrive.CreateFile("test.txt");
                break;
            }
            case 16: {
                hardDrive.CreateFile(stream[1], stream[2]);
                break;
            }
            case 17: {
                hardDrive.OpenFile(stream[1]);
                System.out.println("ABC");
                break;
            }
            case 18: {
                hardDrive.CloseFile(stream[1]);
                break;
            }
            case 13: {
		hardDrive.ShowDisc();
                break;
            }
            case 12: {
                hardDrive.ShowFileList();
                break;
            }
            case 14: {
                hardDrive.ShowFreeSpace();
                break;
            }
            case 19: {
                hardDrive.DeleteFile(stream[1]);
                break;
            }
            case 20: {
                //WRITE FILE nie ma
                break;
            }
            case 21: {
                hardDrive.EditFile(stream[1], stream[2]);
                break;
            }
            case 22: {
                hardDrive.ReadFile(stream[1]);
                break;
            }
            case 23: {
                hardDrive.Rename(stream[1], stream[2]);
                break;
            }        
            case 24:{
                hardDrive.FormatDrive();
		break;
            }
            case 25: {
                hardDrive.DisplayInfo(stream[0]);
		break;
            }
            case 26: {
                processManager.stworzProces(stream[1], Integer.parseInt(stream[2]));
                break;
            }
            case 27: {
                ProcessManager.usunProces(stream[1]);
                break;
            }    
            case 28: {
		ProcessManager.rysujDrzewo(stream[1]);
                break;
            }
            case 29: {
               	(ProcessManager.getODPALONY()).toString();
                break;
            }
            case 30: {
               	ProcessManager.stopODPALONY();
                break;
            }
            case 31: {
               	//POKA� STAN PROCESU
                break;
            }
            case 32: {
                System.out.println(readMemory(Integer.parseInt(removeBracket(stream[1]))));
                break;
            }
            /*case "WM": {
                memory.Zapisz_do_pamieci(Integer.parseInt(stream[1]), stream[2]);
                break;
            }*/ 
            case 33: {
                memory.wyswietlKolejke();
                break;
            }
            /*case "SHM": {
                memory.wyswietlPamiec();
                break;
            }*/
	    case 34: {
                memory.WyswietlTabliceStronic(stream[1]);
                break;
            }
            case 35: {
                //?????
                break;
            }

            case 38: {
                
                break;
            }
            case 39: {
                
                break;
            }
            case 40: {
                
                break;
            }            
            default: break;
        }
    }
}
