import sudoku.Sudoku;

public class main {


    public static void main(String [] args) {

        Sudoku s = new Sudoku();

        s.addField(1,0,3);
        s.addField(3,1,1);
        s.addField(4,1,9);
        s.addField(5,1,5);
        s.addField(2,2,8);
        s.addField(7,2,6);
        s.addField(0,3,8);
        s.addField(4,3,6);
        s.addField(0,4,4);
        s.addField(3,4,8);
        s.addField(8,4,1);
        s.addField(4,5,2);
        s.addField(1,6,6);
        s.addField(6,6,2);
        s.addField(7,6,8);
        s.addField(3,7,4);
        s.addField(4,7,1);
        s.addField(5,7,9);
        s.addField(8,7,5);
        s.addField(7,8,7);


        Long l = System.currentTimeMillis();

        s.solve();

        Long l1 = System.currentTimeMillis();

        System.out.println(s.toString());
        System.out.println("Solving took "+(l1-l)/1000d+" seconds");
    }

}
