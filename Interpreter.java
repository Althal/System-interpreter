package thanos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Interpreter {
    private int A = 0;
    private int B = 0;
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private int counter = 0;
    
    private void saveRegistersToPCB(PCB pcb){
        pcb.A = this.A;
        pcb.B = this.B;
        pcb.C = this.C;
        pcb.D = this.D;
        pcb.E = this.E;
        pcb.F = this.F;
        saveCounterToPCB(pcb);
    }
    
    private void saveCounterToPCB(PCB pcb){
        pcb.wskaznik = this.counter;
    }
    
    private void getRegistersFromPCB(PCB pcb){
        A = pcb.A;
        B = pcb.B;
        C = pcb.C;
        D = pcb.D;
        E = pcb.E;
        F = pcb.F;
        counter = pcb.wskaznik;
    }
    
    private void showRegisters(){
        System.out.println("Reg. A: " + A);
        System.out.println("Reg. B: " + B);
        System.out.println("Reg. C: " + C);
        System.out.println("Reg. D: " + D);
        System.out.println("Reg. E: " + E);
        System.out.println("Reg. F: " + F);
        System.out.println("Counter: " + counter);
    }
    
    private String getOrder(Memory m, PCB pcb){
        String order = m.czytaj(counter);
        counter += (order.length()+1);
        return order;
    }
    
    public void execute(Memory m, PCB pcb, HardDrive hardDrive){
        getRegistersFromPCB(pcb);
        
        String order = getOrder(m, pcb);
        System.out.println("Order: " + order);
        
        //DODAWANIE (REJESTR, WARTOSC)
        //DODAWANIE (REJESTR, ADRES)
        if("ADD".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartoœæ
            System.out.println("Args: " + arg1 + " " + arg2);
            
            int value;
            
            //Z PAMIECI
            if(arg2.charAt(0)=='['){
                int mem = Integer.parseInt(arg2.substring(1, arg2.length()));
                value = m.pobierzZnak(mem);
            }
            //WARTOSC
            else{
               value = Integer.parseInt(arg2);
            }
            
            if("A".equals(arg1)) A+=value;
            else if("B".equals(arg1)) B+=value;
            else if("C".equals(arg1)) C+=value;
            else if("D".equals(arg1)) D+=value;
            else if("E".equals(arg1)) E+=value;
            else if("F".equals(arg1)) F+=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }  
        
        //ODEJMOWANIE (REJESTR, WARTOSC)
        //ODEJMOWANIE (REJESTR, ADRES)
        else if("SUB".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartoœæ
            System.out.println("Args: " + arg1 + " " + arg2);
            
            int value;
            
            //Z PAMIECI
            if(arg2.charAt(0)=='['){
                int mem = Integer.parseInt(arg2.substring(1, arg2.length()));
                value = m.pobierzZnak(mem);
            }
            //WARTOSC
            else{
               value = Integer.parseInt(arg2);
            }
            
            if("A".equals(arg1)) A-=value;
            else if("B".equals(arg1)) B-=value;
            else if("C".equals(arg1)) C-=value;
            else if("D".equals(arg1)) D-=value;
            else if("E".equals(arg1)) E-=value;
            else if("F".equals(arg1)) F-=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //MNOZENIE (REJESTR, WARTOSC)
        //MNOZENIE (REJESTR, ADRES)
        else if("MUL".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartoœæ
            System.out.println("Args: " + arg1 + " " + arg2);
            
            int value;
            
            //Z PAMIECI
            if(arg2.charAt(0)=='['){
                int mem = Integer.parseInt(arg2.substring(1, arg2.length()));
                value = m.pobierzZnak(mem);
            }
            //WARTOSC
            else{
               value = Integer.parseInt(arg2);
            }
            
            if("A".equals(arg1)) A*=value;
            else if("B".equals(arg1)) B*=value;
            else if("C".equals(arg1)) C*=value;
            else if("D".equals(arg1)) D*=value;
            else if("E".equals(arg1)) E*=value;
            else if("F".equals(arg1)) F*=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //DZIELENIE (REJESTR, WARTOSC)
        //DZIELENIE (REJESTR, ADRES)
        else if("DIV".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartoœæ
            System.out.println("Args: " + arg1 + " " + arg2);
            
            int value;
            
            //Z PAMIECI
            if(arg2.charAt(0)=='['){
                int mem = Integer.parseInt(arg2.substring(1, arg2.length()));
                value = m.pobierzZnak(mem);
            }
            //WARTOSC
            else{
               value = Integer.parseInt(arg2);
            }
            
            if("A".equals(arg1)) A/=value;
            else if("B".equals(arg1)) B/=value;
            else if("C".equals(arg1)) C/=value;
            else if("D".equals(arg1)) D/=value;
            else if("E".equals(arg1)) E/=value;
            else if("F".equals(arg1)) F/=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //MODULO (REJESTR WYNIKU, REJESTR DZIELNA, REJESTR DZIELNIK)
        else if("MOD".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr wyniku
            String arg2 = getOrder(m, pcb); //rejestr dzielna
            String arg3 = getOrder(m, pcb); //rejestr dzielnik
            System.out.println("Args: " + arg1 + " " + arg2 + " " + arg3);
            
            int base = 0;
            int mul = 0;
            
            int value;
            
            if("A".equals(arg2)) base = A;
            else if("B".equals(arg2)) base = B;
            else if("C".equals(arg2)) base = C;
            else if("D".equals(arg2)) base = D;
            else if("E".equals(arg2)) base = E;
            else if("F".equals(arg2)) base = F;
            
            if("A".equals(arg3)) mul = A;
            else if("B".equals(arg3)) mul = B;
            else if("C".equals(arg3)) mul = C;
            else if("D".equals(arg3)) mul = D;
            else if("E".equals(arg3)) mul = E;
            else if("F".equals(arg3)) mul = F;
            
            value = base%mul;
            
            if("A".equals(arg1)) A=value;
            else if("B".equals(arg1)) B=value;
            else if("C".equals(arg1)) C=value;
            else if("D".equals(arg1)) D=value;
            else if("E".equals(arg1)) E=value;
            else if("F".equals(arg1)) F=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //ZAPIS DO REJESTRU (REJESTR, WARTOSC)
        //ZAPIS DO REJESTRU (REJESTR, PAMIEC)
        else if("MOV".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartoœæ
            System.out.println("Args: " + arg1 + " " + arg2);
            
            int value;
            
            //ZAPIS Z PAMIECI
            if(arg2.charAt(0)=='['){
                int mem = Integer.parseInt(arg2.substring(1, arg2.length()));
                value = m.pobierzZnak(mem);
            }
            
            //ZAPIS MIEDZY REJESTRAMI
            else if("A".equals(arg2)) value=A;
            else if("B".equals(arg2)) value=B;
            else if("C".equals(arg2)) value=C;
            else if("D".equals(arg2)) value=D;
            else if("E".equals(arg2)) value=E;
            else if("F".equals(arg2)) value=F;
            
            //ZAPIS WARTOSCI
            else {
               value = Integer.parseInt(arg2);
            }
            
            if("A".equals(arg1)) A=value;
            else if("B".equals(arg1)) B=value;
            else if("C".equals(arg1)) C=value;
            else if("D".equals(arg1)) D=value;
            else if("E".equals(arg1)) E=value;
            else if("F".equals(arg1)) F=value;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //INKREMENTACJA (REJESTR)
        else if("INC".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            System.out.println("Args: " + arg1);
            
            if("A".equals(arg1)) A++;
            else if("B".equals(arg1)) B++;
            else if("C".equals(arg1)) C++;
            else if("D".equals(arg1)) D++;
            else if("E".equals(arg1)) E++;
            else if("F".equals(arg1)) F++;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //DEKREMENTACJA (REJESTR)
        else if("DEC".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            System.out.println("Args: " + arg1);
            
            if("A".equals(arg1)) A--;
            else if("B".equals(arg1)) B--;
            else if("C".equals(arg1)) C--;
            else if("D".equals(arg1)) D--;
            else if("E".equals(arg1)) E--;
            else if("F".equals(arg1)) F--;
            
            showRegisters();
            saveRegistersToPCB(pcb);
        }
        
        //ZAPIS DO PAMIECI (MIEJSCE, ZNAK)
        else if("MWR".equals(order)){
            String arg1 = getOrder(m, pcb); //miejsce (moze byc [x] moze byc samo x)
            String arg2 = getOrder(m, pcb); //znak
            System.out.println("Args: " + arg1 + " " + arg2);
            
            if(arg1.charAt(0)=='['){
                String newArg1 = arg1.substring(1, arg1.length());
                arg1 = newArg1;
            }

            m.Zapisz_do_pamieci(Integer.parseInt(arg1), arg2.charAt(0));
            
            saveCounterToPCB(pcb);
        }
        
        else if("BOO".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            System.out.println("Args: " + arg1);
            
            int value = 0;
            
            if("A".equals(arg1)) value = A;
            else if("B".equals(arg1)) value = B;
            else if("C".equals(arg1)) value = C;
            else if("D".equals(arg1)) value = D;
            else if("E".equals(arg1)) value = E;
            else if("F".equals(arg1)) value = F;
            
            if(value != 0)System.out.println("Answer: TRUE");
            else System.out.println("Answer: FALSE");
                
            saveCounterToPCB(pcb);
        }
        
        //SKOK DO (ADRES)
        else if("JP".equals(order)){
            String arg1 = getOrder(m, pcb); //adres
            System.out.println("Args: " + arg1);
            
            this.counter = Integer.parseInt(arg1);
            
            saveCounterToPCB(pcb);
        }
        
        //SKOK DO JEZELI NIE ZERO (REJESTR, ADRES)
        else if("JNZ".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //adres
            System.out.println("Args: " + arg1 + " " + arg2);
            
            boolean isZero = false;
            
            if("A".equals(arg1) && A==0) isZero = true;
            else if("B".equals(arg1) && B==0) isZero = true;
            else if("C".equals(arg1) && C==0) isZero = true;
            else if("D".equals(arg1) && D==0) isZero = true;
            else if("E".equals(arg1) && E==0) isZero = true;
            else if("F".equals(arg1) && F==0) isZero = true;
            
            if(!isZero) this.counter = Integer.parseInt(arg2);

            saveCounterToPCB(pcb);
        }
        
        //POKAZ LISTE PLIKOW ()
        else if("FL".equals(order)){
            hardDrive.ShowFileList();
            saveCounterToPCB(pcb);
        }
        
        //POKAZ LISTE DYSK ()
        else if("SD".equals(order)){
            hardDrive.ShowDisc();
            saveCounterToPCB(pcb);
        }
        
        //POKAZ WOLNE MIEJSCE ()
        else if("SFS".equals(order)){
            hardDrive.ShowFreeSpace();
            saveCounterToPCB(pcb);
        }
        
        //STWORZ PLIK (NAZWA)
        else if("CF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.CreateFile(arg1);
            saveCounterToPCB(pcb);
        }
        
        //STWORZ PLIK Z DANYMI (NAZWA, DANE)
        else if("CFD".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            String arg2 = getOrder(m, pcb); //dane
            System.out.println("Args: " + arg1 + " " + arg2);
            
            hardDrive.CreateFile(arg1,arg2);
            saveCounterToPCB(pcb);
        }
        
        //OTWORZ PLIK (NAZWA)
        else if("OF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.OpenFile(arg1);
            saveCounterToPCB(pcb);
        }
        
        //ZAMKNIJ PLIK (NAZWA)
        else if("EF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.CloseFile(arg1);
            saveCounterToPCB(pcb);
        }
        
        //USUÑ PLIK (NAZWA)
        else if("DF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.DeleteFile(arg1);
            saveCounterToPCB(pcb);
        }
        
        //NAPISZ DO PLIKU (NAZWA, DANE)
        else if("WF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            String arg2 = getOrder(m, pcb); //dane
            System.out.println("Args: " + arg1 + " " + arg2);
            
            //TODO
            //hardDrive.WriteFile(arg1,arg2);
            saveCounterToPCB(pcb);
        }
        
        //EDYTUJ PLIK (NAZWA, DANE)
        else if("ED".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            String arg2 = getOrder(m, pcb); //dane
            System.out.println("Args: " + arg1 + " " + arg2);
            
            hardDrive.EditFile(arg1,arg2);
            saveCounterToPCB(pcb);
        }
        
        //CZYTAJ PLIK (NAZWA)
        else if("RF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.ReadFile(arg1);
            saveCounterToPCB(pcb);
        }
        
        //ZMIEN NAZWE PLIKU (NAZWA PRZED, NAZWA PO)
        else if("RNF".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            String arg2 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1 + " " + arg2);
            
            hardDrive.Rename(arg1,arg2);
            saveCounterToPCB(pcb);
        }
        
        //FORMAT ()
        else if("FOR".equals(order)){
            hardDrive.FormatDrive();
            saveCounterToPCB(pcb);
        }
        
        //POKAZ INFO (NAZWA)
        else if("DI".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            hardDrive.GetDataToShow(arg1);
            saveCounterToPCB(pcb);
        }
             
        //POKAZ PAMIEC ()
        else if("SHM".equals(order)){
            m.wyswietlPamiec();
            saveCounterToPCB(pcb);
        }
        
        //POKAZ DRZEWO PROCESOW
        else if("XT".equals(order)){
            String arg1 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1);
            
            ProcessManager.rysujDrzewo(arg1);
            
            saveCounterToPCB(pcb);
        }
        
        //STWORZ PROCES (PLIK, NAZWA)
        else if("CP".equals(order)){
            PCB p = new PCB(0, "INIT");
            
            String arg1 = getOrder(m, pcb); //plik
            String arg2 = getOrder(m, pcb); //nazwa
            System.out.println("Args: " + arg1 + " " + arg2);
            
            try {
                p.fork(arg1+".txt", arg2);
            } catch (IOException ex) {
                Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            saveCounterToPCB(pcb);
        }
        
        //ZABIJ PROCES
        else if("KP".equals(order)){
            Scheduler.getRunningPCB().zakoncz();
            saveCounterToPCB(pcb);
        }
        
        //KONIEC PLIKU PROCES ZAKONCZONY
        else if("HT".equals(order)){
            System.out.println(Scheduler.getRunningPCB());
            Scheduler.getRunningPCB().zakoncz();
            saveCounterToPCB(pcb);
        }
        
        //IF (REJESTR, WARTOSC, SKOK PRZY FALSZU)
        else if("IF".equals(order)){
            String arg1 = getOrder(m, pcb); //rejestr
            String arg2 = getOrder(m, pcb); //wartosc
            String arg3 = getOrder(m, pcb); //skok przy false
            System.out.println("Args: " + arg1 + " " + arg2 + " " + arg3);
            
            int value = 0;
            int value2 = Integer.parseInt(arg2);
            int jump = Integer.parseInt(arg3);
            
            if("A".equals(arg1)) value = A;
            else if("B".equals(arg1)) value = B;
            else if("C".equals(arg1)) value = C;
            else if("D".equals(arg1)) value = D;
            else if("E".equals(arg1)) value = E;
            else if("F".equals(arg1)) value = F;
            
            if(value != value2) this.counter += jump;
            
            saveCounterToPCB(pcb); 
        }
        
        else{
            System.out.println("Wrong order");
        }       
    }
}
