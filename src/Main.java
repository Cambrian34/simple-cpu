// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        

        int aluctr = 0;
        int a = 1;
        int b = 2;
        int zero = 0;
        // timer/clock{0,1}{
        int clock =0;
        for (int i = 0; i < 6; i++) {
            int[] out = cpu.cpu2(clock);
            System.out.println(out[0]);
            if (clock == 0){
                clock = 1;
            }else{
                clock = 0;
            }
        }

        //System.out.println(out);
    }
}
class cpu{

    public static int ALU(int aluctr,int a, int b,int zero){
        int out = 0;
        switch (aluctr){
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

        return out;
    }

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


    public static int[] cpu2(int clock){
        int[] reg = new int[16];

        int imem[] = {0,0,1,0,1,1,0,1,0,0,0,0,1,1,1,1};

        int dmem[] = new int[1024];
        int pc = 0;
        int pcplus4 = 0;
        int IR = 0;
        int writeData = 0;
        int readData1 = 0;
        int readData2 = 0;
        int aluResult = 0;

        int regfileResult= 0;
        //regfileResult= reg_file(imem[0:12],0,0,0,0)[0];
        aluResult = ALU(imemtoAluctrl(imem,6),3,2,0);




        return new int[]{aluResult,pc};

    }
    public static int combine(){
        return 0;
    }
}