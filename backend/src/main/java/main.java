import at.homeproductions.sudoku.entity.Sudoku;

public class main {




    public static void main(String [] args) throws InterruptedException {

        Sudoku s = new Sudoku();

        s.addStandardConfigField(1,0,3);
        s.addStandardConfigField(3,1,1);
        s.addStandardConfigField(4,1,9);
        s.addStandardConfigField(5,1,5);
        s.addStandardConfigField(2,2,8);
        s.addStandardConfigField(7,2,6);
        s.addStandardConfigField(0,3,8);
        s.addStandardConfigField(4,3,6);
        s.addStandardConfigField(0,4,4);
        s.addStandardConfigField(3,4,8);
        s.addStandardConfigField(8,4,1);
        s.addStandardConfigField(4,5,2);
        s.addStandardConfigField(1,6,6);
        s.addStandardConfigField(6,6,2);
        s.addStandardConfigField(7,6,8);
        s.addStandardConfigField(3,7,4);
        s.addStandardConfigField(4,7,1);
        s.addStandardConfigField(5,7,9);
        s.addStandardConfigField(8,7,5);
        s.addStandardConfigField(7,8,7);


        Long l = System.currentTimeMillis();

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
        s.solve();

//        Long l1 = System.currentTimeMillis();

        System.out.println(s.toString());

        s.printSolutionTrail();

//        System.out.println(s.i);
//        System.out.println("Solving took "+(l1-l)/1000d+" seconds");


    }

}
