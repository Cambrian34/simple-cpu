import java.math.BigInteger;
import java.util.Arrays;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        

        int aluctr = 0;
        int a = 1;
        int b = 2;
        int zero = 0;
        // timer/clock{0,1}{
        cpu cpu1 = new cpu();
        int clock =0;
        for (int i = 0; i < 3; i++) {

            System.out.println("Clock pulse: "+clock);
            int[] out = cpu1.cpu2(clock,i);
            //System.out.println(out[0]);
            if (clock == 0){
                clock = 1;
            }else{
                clock = 0;
            }

        }
/*
        //System.out.println(out);
        reg_file reg = new reg_file(12);
        reg.writereg(1, 2);
        int outb =reg.readreg(1);
        System.out.println(outb);

        ALU alu = new ALU(aluctr,a,b);
        int out = alu.aluout();
        System.out.println(out);

        maincontrol main = new maincontrol(2);
        int[] out2 = main.maincontrolout();
        for (int i = 0; i < out2.length; i++) {
            System.out.println(out2[i]);
        }

        alucontrol alucon = new alucontrol(0,2,2);
        int out3 = alucon.getAluout();
        System.out.println(out3);*/
    }
}
class cpu{
    static int[][] imem = {{1,1,1,0,1,1,0,1,0,0,0,0,1,1,1,1},
            {1,0,1,0,1,1,0,1,0,0,0,0,1,1,1,0},
            {0,0,1,0,1,1,1,1,0,0,0,0,1,1,0,1}};

    static reg_file regfile = new reg_file(16);

    public static int imemtoAluctrl(int[] in, int numberbits){
        int out1 = 0;
        //change first six bits to decimal
        int[] opcode = new int[numberbits];
        System.arraycopy(in, 0, opcode, 0, numberbits);
        int op = todecimal(opcode);
        return op;
    }
    public static int todecimal(int[] binary){
        int out = 0;
        for (int i = 0; i < binary.length; i++) {
            out += binary[i] * Math.pow(2, binary.length - i - 1);
        }
        return out;
    }


    public static int[] cpu2(int clock, int mem_increm){
        int[] reg = new int[16];

        // eight  dimensional array

        //System.out.println("Imem :"+imem[mem_increm][0]);
        int dmem[] = new int[1024];
        int pc = 0;
        int pcplus4 = 0;
        // inncrements imem by 2
        int[][]IR = new int[8][16]; // 8 memory locations with 16 bits
        System.out.println("Memmory increment: "+mem_increm);
        IR = new int[][]{imem[mem_increm],datainmem(imem,mem_increm)};
        /*for (int i = 0; i < IR.length; i++) {
            System.out.println("IR"+ (IR[i][i]) +" Data In Mem: "+ Arrays.toString(datainmem(IR, i)));
        }*/

        int writeData = 0;
        int readData1 = 0;
        int readData2 = 0;
        int aluResult = 0;
        boolean regDst = true;
        //assigns wr to regfile
        int[] wr= new int[4];
       //wr= (regDst) ? datain_arr(test,6,8 ): datain_arr(test,7,9 );
        //System.out.println(Arrays.toString(datain_arr(datainmem(IR, mem_increm), 6, 7)));
        /*int [] test = new int[16];
        for (int i = 0; i < IR.length; i++) {
            test=  datainmem(IR, i);
        }
        System.out.println("Test: "+ Arrays.toString(test));
        //
        int wr98 = todecimal(datain_arr(test,6,8 ));
        System.out.println("WR98: "+ wr98);
        //wr= mux(datain_arr(test,6,7 ), datain_arr(test,8,9 ), regDst);
        //System.out.println("WR: "+ Arrays.toString(wr));*/

        writetoreg(IR, regfile,mem_increm);
        int[] readfromreg = readfromreg( regfile,mem_increm);
        System.out.println("Read from reg: "+ Arrays.toString(readfromreg));
        /*// tested datain_arr
        //int[] imem4 = {1,1,1,0,1,1,1,2,0,0,0,0,1,1,1,1};

        int[] oiu= datain_arr(test, 0, 3);
        
        for (int i = 0; i < oiu.length; i++) {
            System.out.println("oiu: "+oiu[i]);
        }*/
        regfile.printregs();


        return new int[]{aluResult,pc};

    }
    public static int[] datainmem(int[][] data, int memaddr){
        int[] out = new int[16];
        for (int i = 0; i < 16; i++) {
            out[i] = data[memaddr][i];
        }
        return out;

    }
    // gets specific data from array
    public static int[] datain_arr(int [] data, int start, int end){
        int[] out = new int[end-start];
        int o=0;
        for (int i = start; i <= end; i++) {
            if(i== out.length-1 || o <= out.length+1){
                break;
            }
            out[o] = data[i];
            o++;

        }
        System.out.println("Data in arr: "+ Arrays.toString(out));
        return out;
    }

    public static void writetoreg(int[] []imem, reg_file regfile,int mem_increm){
        int byte1 =0;
        for (int i = 0; i < imem.length; i++) {
            regfile.writereg(mem_increm, todecimal(datainmem(imem,i)));
            //writes to regfile
            //regfile.writereg();
        }
    }
    public static int[] readfromreg( reg_file regfile,int mem_increm){
        int[] out;// new int[16];

        out = tobinary(regfile.readreg(mem_increm));
        //(out);

        return out;
    }
    // converts decimal to binary array
    public static int[] tobinary(int i) {
        String o =Integer.toBinaryString(i);
        int[] out = new int[o.length()];
        for (int j = 0; j < o.length(); j++) {
            out[j] = Integer.parseInt(String.valueOf(o.charAt(j)));
        }
        System.out.println("Out: "+ Arrays.toString(out));

        return out;

    }
    /*private static int tobinary(int i) {
        String o =Integer.toBinaryString(i);
        //int out = Integer.parseInt(o);
        System.out.println("Out: "+ o);

        return o.length();

    }*/

    // combines data in array
    public static int combine(int[] data){
        int out = 0;
        String outstr = "";
        for(int i=0;  i<data.length; i++){
            outstr += data[i];
        }

        BigInteger big = new BigInteger(outstr);
        System.out.println("Outstr: "+ big);
        out = big.intValue();
        return out;
    }
    // 2x1 multiplexer
    public int mux(int a, int b, boolean sel){
        if(sel){
            return a;
        }
        else{
            return b;
        }
    }
    // 4x1 multiplexer
    public int mux4(int a, int b, int c, int d, boolean sel1, boolean sel2){
        if(sel1 && sel2){
            return a;
        }
        else if(sel1 && !sel2){
            return b;
        }
        else if(!sel1 && sel2){
            return c;
        }
        else{
            return d;
        }
    }

}
class reg_file{
    public int[] registers;
    reg_file(int regsize){
        registers = new int[regsize];
    }
    public int readreg(int regnum){
        if (regnum< 0 || regnum >= registers.length){
            System.out.println("Error: register number out of range");
            return 0;
        }
        else{
            return registers[regnum];
        }
    }

    public void writereg(int regnum, int value){
        if (regnum< 0 || regnum >= registers.length){
            System.out.println("Error: register number out of range");
        }
        else{
            registers[regnum] = value;
        }
    }
    // converts decimal to binary array
    public static int[] tobinary(int i) {
        String o =Integer.toBinaryString(i);
        int[] out = new int[o.length()];
        for (int j = 0; j < o.length(); j++) {
            out[j] = Integer.parseInt(String.valueOf(o.charAt(j)));
        }
        System.out.println("Out: "+ Arrays.toString(out));

        return out;

    }
    //prints out registers
    public void printregs(){
        for (int i = 0; i < registers.length; i++) {
            System.out.println("Reg "+i+": "+registers[i]+ " "+Arrays.toString(tobinary(registers[i])));
        }
    }

}
class ALU{
    // arithmetic logic unit
    int aluctrl;
    int a;
    int b;
    int out;
    boolean zero;
    ALU(int aluctrl, int a, int b){
        this.aluctrl = aluctrl;
        this.a = a;
        this.b = b;
        this.out = 0;
        this.zero = false;
    }
    public int aluout(){
        switch (aluctrl){
            case 0:
                // add
                out = a + b;
                break;
            case 1:
                // sub
                out = a - b;
                break;
            case 2:
                // and
                out = a & b;
                break;
            case 3:
                // or
                out = a | b;
                break;
            case 4:
                // slt
                out = a << b;
                break;
            case 5:
                // sll
                out = a >> b;
                break;
            case 6:
                // srl
                out = a >>> b;
                break;
            case 7:
                // xor
                out = a ^ b;
                break;
            case 8:
                // nor
                out = ~(a | b);
                break;
            case 9:
                // nand
                out = ~(a & b);
                break;
            case 10:
                // sra
                out = a >> b;
                break;
            case 11:
                // mul
                out = a * b;
                break;
            case 12:
                // div
                out = a / b;
                break;
            case 13:
                // mod
                out = a % b;
                break;
            case 14:
                // addi
                out = a + b;
                break;
            case 15:
                // andi
                out = a & b;
                break;
            case 16:
                // ori
                out = a | b;
                break;
            case 17:
                // xori
                out = a ^ b;
                break;
            case 18:
                // slti
                out = a < b ? 1 : 0;
                break;
            case 19:
                // lui
                out = a << 16;
                break;
            case 20:
                // lw
                out = a + b;
                break;
            case 21:
                // sw
                out = a + b;
                break;
            case 22:
                // beq
                out = a == b ? 1 : 0;
                break;
            case 23:
                // bne
                out = a != b ? 1 : 0;
                break;
            case 24:
                // bgt
                out = a > b ? 1 : 0;
                break;
            case 25:
                // blt
                out = a < b ? 1 : 0;
                break;
            case 26:
                // bge
                out = a >= b ? 1 : 0;
                break;
            case 27:
                // ble
                out = a <= b ? 1 : 0;
                break;
            case 28:
                // j
                out = a;
                break;
            case 29:
                // jr
                out = a;
                break;
            case 30:
                // jal
                out = a;
                break;
            case 31:
                // jalr
                out = a;
                break;
            case 32:
                // halt
                out = 0;
                break;
            case 33:
                // nop
                out = 0;
                break;
            case 34:
                // move
                out = a;
                break;
            case 35:
                // mfhi
                out = a;
                break;
            case 36:
                // mflo
                out = a;
                break;
            case 37:
                // mthi
                out = a;
                break;
            case 38:
                // mtlo
                out = a;
                break;
            case 39:
                // syscall
                out = 0;
                break;
            case 40:
                // not
                out = ~a;
                break;
            case 41:
                // neg
                out = -a;
                break;
            case 42:
                // seq
                out = a == b ? 1 : 0;
                break;
            case 43:
                // sne
                out = a != b ? 1 : 0;
                break;
            case 44:
                // sgt
                out = a > b ? 1 : 0;
                break;

            case 45:
                // sge
                out = a >= b ? 1 : 0;
                break;
            case 46:
                // sle
                out = a <= b ? 1 : 0;
                break;
            case 47:
                // sllv
                out = a << b;
                break;
            case 48:
                // srlv
                out = a >>> b;
                break;
            case 49:
                // srav
                out = a >> b;
                break;

        }
        // // Zero is true if ALUOut is 0
         if (out == 0){
                zero = true;
         }
         else{
                zero = false;
         }
        return out;
    }
}
class maincontrol {
    // main control unit
    int opcode;
    int aluctrl;
    int regdst;
    int regwrite;
    int alusrc;
    int memtoreg;
    int memwrite;
    int memread;
    int branch;
    int jump;

    maincontrol(int opcode) {
        this.opcode = opcode;
        this.aluctrl = 0;
        this.regdst = 0;
        this.regwrite = 0;
        this.alusrc = 0;
        this.memtoreg = 0;
        this.memwrite = 0;
        this.memread = 0;
        this.branch = 0;
        this.jump = 0;
    }

    public int[] maincontrolout() {
        switch (opcode) {
            case 0:
                // R-type
                aluctrl = 0;
                regdst = 1;
                regwrite = 1;
                alusrc = 0;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
            case 1:
                // addi
                aluctrl = 14;
                regdst = 0;
                regwrite = 1;
                alusrc = 1;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
            case 2:
                // andi
                aluctrl = 15;
                regdst = 0;
                regwrite = 1;
                alusrc = 1;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
            case 3:
                // ori
                aluctrl = 16;
                regdst = 0;
                regwrite = 1;
                alusrc = 1;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
            case 4:
                // xori
                aluctrl = 17;
                regdst = 0;
                regwrite = 1;
                alusrc = 1;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
            case 5:
                // slti
                aluctrl = 18;
                regdst = 0;
                regwrite = 1;
                alusrc = 1;
                memtoreg = 0;
                memwrite = 0;
                memread = 0;
                branch = 0;
                jump = 0;
                break;
        }
        return new int[] { aluctrl, regdst, regwrite, alusrc, memtoreg, memwrite, memread, branch, jump };
    }
}
class alucontrol {
    // ALU control unit
    int aluctrl;
    int funct;
    int aluop;
    int aluout;

    alucontrol(int aluctrl, int funct, int aluop) {
        this.aluctrl = aluctrl;
        this.funct = funct;
        this.aluop = aluop;
        this.aluout = 0;
        alucontrolout();
    }

    public void alucontrolout() {
        switch (aluctrl) {
            case 0:
                // R-type
                switch (funct) {
                    case 0 ->
                        // add
                            aluout = 0;
                    case 1 ->
                        // sub
                            aluout = 1;
                    case 2 ->
                        // mul
                            aluout = 2;
                    case 3 ->
                        // div
                            aluout = 3;
                    case 4 ->
                        // and
                            aluout = 4;
                    case 5 ->
                        // or
                            aluout = 5;
                    case 6 ->
                        // xor
                            aluout = 6;
                    case 7 ->
                        // nor
                            aluout = 7;
                    case 8 ->
                        // slt
                            aluout = 8;
                    case 9 ->
                        // sll
                            aluout = 9;
                    case 10 ->
                        // srl
                            aluout = 10;
                    case 11 ->
                        // sra
                            aluout = 11;
                    case 12 ->
                        // sllv
                            aluout = 12;
                    case 13 ->
                        // srlv
                            aluout = 13;
                    case 14 ->
                        // srav
                            aluout = 14;
                    case 15 ->
                        // seq
                            aluout = 15;
                    case 16 ->
                        // sne
                            aluout = 16;
                    case 17 ->
                        // sgt
                            aluout = 17;
                    case 18 ->
                        // sge
                            aluout = 18;
                    case 19 ->
                        // sle
                            aluout = 19;
                    case 20 ->
                        // not
                            aluout = 20;
                    case 21 ->
                        // neg
                            aluout = 21;
                    case 22 ->
                        // move
                            aluout = 22;
                }
        }
    }

     public int getAluout() {
         return aluout;
     }
}