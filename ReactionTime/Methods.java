import java.util.*;

class Methods {

    private static Scanner in = new Scanner(System.in);

    static void println(String s) {
        System.out.println(s);
    }
    static void println() {
        System.out.println();
    }
    
    static void println(boolean b) {
        println(String.valueOf(b));
    }
    static void println(char c) {
        println(String.valueOf(c));
    }
    static void println(int i) {
        println(String.valueOf(i));
    }
    static void println(long l) {
        println(String.valueOf(l));
    }
    static void println(float f) {
        println(String.valueOf(f));
    }
    static void println(double d) {
        println(String.valueOf(d));
    }
    static void println(char[] s) {
        println(String.valueOf(s));
    }
    static void println(Object obj) {
        println(String.valueOf(obj));
    }

    static void printerr(String s) {
        System.err.println(s);
    }

    static void printerr(boolean b) {
        printerr(String.valueOf(b));
    }
    static void printerr(char c) {
        printerr(String.valueOf(c));
    }
    static void printerr(int i) {
        printerr(String.valueOf(i));
    }
    static void printerr(long l) {
        printerr(String.valueOf(l));
    }
    static void printerr(float f) {
        printerr(String.valueOf(f));
    }
    static void printerr(double d) {
        printerr(String.valueOf(d));
    }
    static void printerr(char[] s) {
        printerr(String.valueOf(s));
    }
    static void printerr(String s, Object obj) {
        printerr(String.valueOf(obj));
    }

    static void print(String s) {
        System.out.print(s);
    }
    
    static void print(boolean b) {
        print(String.valueOf(b));
    }
    static void print(char c) {
        print(String.valueOf(c));
    }
    static void print(int i) {
        print(String.valueOf(i));
    }
    static void print(long l) {
        print(String.valueOf(l));
    }
    static void print(float f) {
        print(String.valueOf(f));
    }
    static void print(double d) {
        print(String.valueOf(d));
    }
    static void print(char[] s) {
        print(String.valueOf(s));
    }
    static void print(Object obj) {
        print(String.valueOf(obj));
    }

    static double getDouble (){
        in.reset();
        double x;
        while(!in.hasNextDouble()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextDouble();
        return x;
    }
    static int getInt (){
        in.reset();
        int x;
        while(!in.hasNextInt()){
            String word = in.next();
            System.err.println(word + " is not an integer");
        }
        x = in.nextInt();
        return x;
    }
    static long getLong (){
        in.reset();
        long x;
        while(!in.hasNextLong()){
            String word = in.next();
            System.err.println(word + " is not an integer");
        }
        x = in.nextLong();
        return x;
    }
    static short getShort (){
        in.reset();
        short x;
        while(!in.hasNextShort()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextShort();
        return x;
    }
    static String getString (){
        in.reset();
        String x;
        x = in.next();
        return x;
    }
    static char getChar (){
        in.reset();
        char x = in.next(".").charAt(0);
        return x;
    }
    static boolean getBoolean (){
        in.reset();
        boolean x;
        while(!in.hasNextBoolean()){
            String word = in.next();
            System.err.println(word + " is not a boolean");
        }
        x = in.nextBoolean();
        return x;
    }
    static float getFloat (){
        in.reset();
        float x;
        while(!in.hasNextFloat()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextFloat();
        return x;
    }

    static double getDouble (String s){
        println(s);
        in.reset();
        double x;
        while(!in.hasNextDouble()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextDouble();
        return x;
    }
    static int getInt (String s){
        println(s);
        in.reset();
        int x;
        while(!in.hasNextInt()){
            String word = in.next();
            System.err.println(word + " is not an integer");
        }
        x = in.nextInt();
        return x;
    }
    static long getLong (String s){
        println(s);
        in.reset();
        long x;
        while(!in.hasNextLong()){
            String word = in.next();
            System.err.println(word + " is not an integer");
        }
        x = in.nextLong();
        return x;
    }
    static short getShort (String s){
        println(s);
        in.reset();
        short x;
        while(!in.hasNextShort()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextShort();
        return x;
    }
    static String getString (String s){
        println(s);
        in.reset();
        String x;
        x = in.next();
        return x;
    }
    static char getChar (String s){
        println(s);
        in.reset();
        char x = in.next(".").charAt(0);
        return x;
    }
    static boolean getBoolean (String s){
        println(s);
        in.reset();
        boolean x;
        while(!in.hasNextBoolean()){
            String word = in.next();
            System.err.println(word + " is not a boolean");
        }
        x = in.nextBoolean();
        return x;
    }
    static float getFloat (String s){
        println(s);
        in.reset();
        float x;
        while(!in.hasNextFloat()){
            String word = in.next();
            System.err.println(word + " is not a number");
        }
        x = in.nextFloat();
        return x;
    }

}