public class Main {

    public static void main(String[] args) throws Exception {
        //this obj used for any thing FileIO related(read, write, parse etc.)
        FileIO fileIO = new FileIO();

        fileIO.parseFile();

        fileIO.showShipments();
    }

}
