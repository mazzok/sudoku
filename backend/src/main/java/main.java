import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.generator.GeneratedSudoku;
import at.homeproductions.sudoku.generator.SudokuGenerator;

public class main {




    public static void main(String [] args) throws InterruptedException {
//
//        Sudoku s = new Sudoku();
//
//        s.addInitialField(1,0,3);
//        s.addInitialField(3,1,1);
//        s.addInitialField(4,1,9);
//        s.addInitialField(5,1,5);
//        s.addInitialField(2,2,8);
//        s.addInitialField(7,2,6);
//        s.addInitialField(0,3,8);
//        s.addInitialField(4,3,6);
//        s.addInitialField(0,4,4);
//        s.addInitialField(3,4,8);
//        s.addInitialField(8,4,1);
//        s.addInitialField(4,5,2);
//        s.addInitialField(1,6,6);
//        s.addInitialField(6,6,2);
//        s.addInitialField(7,6,8);
//        s.addInitialField(3,7,4);
//        s.addInitialField(4,7,1);
//        s.addInitialField(5,7,9);
//        s.addInitialField(8,7,5);
//        s.addInitialField(7,8,7);
//
//        s.solve();
        GeneratedSudoku s = SudokuGenerator.generate();
        System.out.println(s);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    List<Sudoku.SudokoFieldSolvingCallable> l = s.sudokuFieldSolvingList.stream()
//                            .collect(Collectors.toList());
//                l.forEach(s->System.out.println(s.field+"["+s.field.getX()+"]["+s.field.getY()+"] in bock ["+s.field.getX()+"]["+s.field.getY()+"] value = "+s.field.getValue()+", possible values ["+s.field.getPossibleValues().size()+"]"));
//                    System.out.println("------------");
//                    System.out.println(s.toString());
//
//                    System.exit(-1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();;
//

//        Long l1 = System.currentTimeMillis();
//        System.out.println(s.i);
//        System.out.println("Solving took "+(l1-l)/1000d+" seconds");


    }

}
