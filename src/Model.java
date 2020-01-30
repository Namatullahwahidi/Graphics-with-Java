
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Model {
    private double EPSILON;
    private final double a;
    private final double b;
    private final double c;
    private final double Xs;
    private final double Xe;
    private double x_max;
    private double y_max;
    private double root_find;
    private int root_find1;
    private double root_newtonMethod;
    private double find_area;
    double extreme_1;
    double X_1;
    double X_2;
    double X_3;
    double Y_1;
    double Y_2;
    double Y_3;
    double xx,yy;
    private ArrayList<String>name_config=new ArrayList<>();
    private ArrayList<String >font_color_config=new ArrayList<>();
    private ArrayList<Integer> font_size=new ArrayList<Integer>();
    private ArrayList<Integer>part1_config=new ArrayList<>();
    private ArrayList<Integer>part2_config=new ArrayList<>();
    private ArrayList<Integer>part3_config=new ArrayList<>();
    
    public Model(double a, double b, double c, double Xs, double Xe ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.Xs = Xs;
        this.Xe = Xe;
      
    }
    
 
    
    void find_ereaByRectangle(int interwall,int n){
            double h = (Xe - Xs) / interwall;
            double s = equation1(n,Xs) + equation1(n,Xe);
            for (double i = Xs+h; i < Xe; i+=h) {
                s += 2 * equation1(n,i);
            }
            System.out.println("First method:"+((h / 2) * s));
            setFind_area((h / 2) * s);
           
    }
    public void find_ereaByRiemenn(int interwall,int n ){
        double deltaX=(Xe-Xs)/interwall,x0=0,FxUp=0;
        double FxDow=equation1(n, Xs)*deltaX;//f(x0)*delta
        int h=1;
 
        for(int i=0;i<interwall;i++){
            x0=(Xs+h*deltaX);
            FxUp+=(equation1(n, x0)*deltaX);//
            h++;
        }
         h=1;
         for(int i=0;i<interwall-1;i++){
            x0=(Xs+h*deltaX);
            FxDow+=(equation1(n, x0)*deltaX);
            h++;
        }
        double result=(FxDow+FxUp)/2;
        System.out.println("ByRiemenn integral is:"+result);
        setFind_area(result);
    }
   
    public double equation1(int n,double x){
        if(n==1){
            double result=a*x*x+b*x+c;
            x=getX_1();
            setY_1(a*x*x+b*x+c);
        return result;
        }
        else if(n==2){
            double result=a*x*x*x+b*x*x+c;
            x=getX_1();
            setY_1(a*x*x*x+b*x*x+c);
            x=getX_2();
            setY_2(a*x*x*x+b*x*x+c);
            return result;
        }
        else if(n==3){
            double result=a*x*x*x*x+b*x+c;
//            x=getX_1();
//            setY_1(a*x*x*x*x+b*x+c);
//            x=getX_2();
//            setY_2(a*x*x*x*x+b*x+c);
//            x=getX_3();
//            setY_3(a*x*x*x*x+b*x+c);
            return result;
        }
        else if(n==4){
            double result=a*Math.sin(Math.toRadians(x*b))+c;
           //(-1*a)+c<= a*sin(b*x)+c <=(1*a)+ c
            setY_1((-1*a)+c);// the minimum point of sin 
            setY_2((1*a)+c);// the maximum point of sin
            return result;
           
        }
        else if(n==5){
            double result=a*Math.cos(Math.toRadians(x*b))+c;
            setY_1((-1*a)+c);// the minimum point of sin 
            setY_2((1*a)+c);// the maximum point of sin
            return result;
        }
        return 0;
    }
    
     public double derivFunc(int n,double x){
        if(n==1){
            double result=2*a*x+b;//a*x*x+b*x+c;
            //(-(double)(b/2*a));
            setX_1(-(double)b/(2*a));
 
        return result;
        }
        else if(n==2){
            double result=3*a*x*x+2*b*x;//a*x*x*x+b*x*x+c;;
            setX_1(0);
            setX_2(-(double)2*b/(3*a));
            return result;
        }
        else if(n==3){
            double result=4*a*x*x*x+b;//a*x*x*x*x+b*x+c;
//            setX_1(0);
//            setX_2(-(double)2*b/3*a);
            return result;
        }
        else if(n==4){
            double result=a*b*Math.cos(Math.toRadians(x*b));//a*Math.sin(Math.toRadians(x*b))+c;
            setX_1(Math.asin(-1)/b);// that is the x axist coordinat of minimum point
            setX_2(Math.asin(1)/b);// that is the x axist coordinat of maximum point
            return result;
        }
        else if(n==5){
            double result=-a*b*Math.sin(Math.toRadians(x*b));//a*Math.cos(Math.toRadians(x*b))+c;
            setX_1(Math.acos(-1)/b);// that is the x axist coordinat of minimum point
            setX_2(Math.acos(1)/b);// that is the x axist coordinat of maximum point
            return result;
        }
        return 0;
    }
  
    
    public void Find_Max_point(int n){
        for(double i=Xs;i<=Xe;i++){
            derivFunc(n, i);
            equation1(n, i);
        }
        
    }
     
    public boolean calculate_root_byNewtonws(int n){
     
            
        if(equation1(n,Xs)*equation1(n,Xe)>=0){
            
            System.out.println("That is working why newton");
            return false;
        }
           double x=equation1(n, Xs)*derivFunc(n, Xe) >0 ? Xs:Xe;
           double x0=x-equation1(n, x)/derivFunc(n, x);
           while(Math.abs(x0-x)>=getEPSILON()){
               x=x0;
               x0-=equation1(n, x)/derivFunc(n, x);
           }
            setRoot_newtonMethod(x);
            System.out.println("X is in newton="+x);
            return true;
    }
    
    // Besiction method 
    public boolean calculate_root(int n){
        double a=Xs;
        double b=Xe;
        double c=0;
        
         
    if(equation1(n,a)*equation1(n,b)>=0){
        System.out.println("Bisction is not working");
        return false;
    }
        System.out.println("GetEPSILION"+getEPSILON());
    c=a;
    while ((b-a) >= getEPSILON()) 
            { 
                    System.out.println("GetEpSILOn:"+getEPSILON());
                    // Find middle point 
                    c = (a+b)/2; 

                    // Check if middle point is root 
                    if (equation1(n,c) == 0.0) 
                            break; 

                    // Decide the side to repeat the steps 
                    else if (equation1(n,c)*equation1(n,a) < 0) 
                            b = c; 
                    else
                            a = c; 
            } 

 
        setRoot_find(c);
        return true;
    }
    
    
    
    
    public ArrayList<Double> GetData_x(){
        double size;
        if(Xs<0 && Xe<0){
            size=Xs-Xe;
        }
        else if(Xs>0 && Xe>0){
            size=Xe-Xs;
        }
        else{
         size=Math.abs(Xs)+Math.abs(Xe);   
        }
        size=Math.abs(size);
        ArrayList<Double>arr=new ArrayList<>();
        double xincre=this.Xs;
        for(int i=0;i<size+1;i++){
            arr.add((double)xincre);
            xincre++;
            

        }
        //that is for find the maximum number of this array
        double max=0;
        for(int i=0;i<size+1;i++){
            if(max<arr.get(i)){
                max=arr.get(i);
            }
        }
        this.setX_max(max);
        return arr;
        
    
    }
   
    public ArrayList<Double> GetData_y(int n){
        double size;
        if(Xs<0 && Xe<0){
            size=Xs-Xe;
        }
        else if(Xs>0 && Xe>0){
            size=Xe-Xs;
        }
        else{
         size=Math.abs(Xs)+Math.abs(Xe);   
        }
        size=Math.abs(size);
        ArrayList<Double> arr1=new ArrayList<>();
        double xincre=this.Xs;
        
        //there we can describe the radio button 1 to 5 
        if(n==1){
            //ax^2+bx+c
            for(int i=0;i<size+1;i++){
                double result=a*xincre*xincre+b*xincre+c;
                xincre++;
                arr1.add((double)result);
            }
            if(getRoot_find1()==1){
                calculate_root(1);
                System.out.println("Your root is namatullah:"+getRoot_find());
                
            }
           
            
           // setRoot_find(calculate_root(1));
            
         
         }
        else if(n==2){
             // "ax^3+bx^2+c";  
            for(int i=0;i<size+1;i++){
                double result=a*xincre*xincre*xincre+b*xincre*xincre+c;
                xincre++;
                arr1.add((double)result);
            }
            if(getRoot_find1()==1){
                calculate_root(2);
                System.out.println("Your root is:"+getRoot_find());
            }
         }
        else if(n==3){
             // "ax^4+bx+c";
            for(int i=0;i<size+1;i++){
                double result=a*xincre*xincre*xincre*xincre+b*xincre+c;
                xincre++;
                arr1.add((double)result);
            }
            
             if(getRoot_find1()==1){
                calculate_root(3);
                System.out.println("Your root is:"+getRoot_find());
            }

         }
        else if(n==4){
           // System.out.println("sin equations");
            for(int i=0;i<size+1;i++){
                Double result=a*Math.sin(Math.toRadians(xincre*b))+c;
                xincre++;
                arr1.add((double)result);
            }
          
             if(getRoot_find1()==1){
                calculate_root(4);
                System.out.println("Your root is:"+getRoot_find());
            }

        }
        else if(n==5){
          //  System.out.println("cos equations");
            for(int i=0;i<size+1;i++){
                Double result=a*Math.cos(Math.toRadians(xincre*b))+c;
                xincre++;
                arr1.add((double)result);
            }
            
             if(getRoot_find1()==1){
                calculate_root(5);
                System.out.println("Your root is:"+getRoot_find());
            }

        }
        
        return arr1;
    }
    // we can read the coordinat of x from  txt file 
    public  ArrayList<Double> X_Cost_File(String fileName){
        
        ArrayList<Double>x_cost = new ArrayList<>();
            
        try(Scanner scanner=new Scanner(new BufferedReader(new FileReader(fileName)))){
           
             
            while (scanner.hasNext()) {
                String next = scanner.next();
                String []info=next.split(",");
                x_cost.add(Double.valueOf(info[0]));
               
            }
             
        } catch (FileNotFoundException ex) {
            System.out.println(" file not found");
        }
         return x_cost;
    }
    // we can read the coordinat of y from  txt file 
    public ArrayList<Double> Y_Cost_File(String fileName){
        
        ArrayList<Double>y_cost = new ArrayList<>();
        try(Scanner scanner=new Scanner(new BufferedReader(new FileReader(fileName)))){
           
            while (scanner.hasNext()) {
                String next = scanner.next();
                String []info=next.split(",");
                y_cost.add(Double.valueOf(info[1]));
                 
            }
             
            
        } catch (FileNotFoundException ex) {
            System.out.println(" file not found");
        }
        
       return y_cost;
    }
    public ArrayList<String> Configuration(String fileName){

        ArrayList<String> configuration_file=new ArrayList<>();
        try(Scanner scanner=new Scanner(new BufferedReader(new FileReader("colors.txt")))){
            while (scanner.hasNextLine()){
                String next=scanner.nextLine();
                String []informations=next.split(",");


                if(informations.length<7){
                    this.name_config.add(informations[0]);
                    this.font_color_config.add(informations[1]);


                    if(informations.length<4){
                        this.part1_config.add(0);
                        this.part2_config.add(0);
                        this.part3_config.add(0);
                        this.font_size.add(Integer.valueOf(informations[2]));
                    }
                    else{
                        this.font_size.add(0);
                        this.part1_config.add(Integer.valueOf(informations[2]));
                        this.part2_config.add(Integer.valueOf(informations[3]));
                        this.part3_config.add(Integer.valueOf(informations[4]));
                    }
                }

            }
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }


        return name_config;
    }
    
    
    
    public double getX_max() {
        return x_max;
    }

    public void setX_max(double x_max) {
        this.x_max = x_max;
    }

    public double getY_max() {
        return y_max;
    }

    public void setY_max(double y_max) {
        this.y_max = y_max;
    }


    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }


    public ArrayList<String> getName_config() {
        return name_config;
    }

    public void setName_config(ArrayList<String> name_config) {
        this.name_config = name_config;
    }

    public ArrayList<String> getFont_color_config() {
        return font_color_config;
    }

    public void setFont_color_config(ArrayList<String> font_color_config) {
        this.font_color_config = font_color_config;
    }

    public ArrayList<Integer> getPart1_config() {
        return part1_config;
    }

    public void setPart1_config(ArrayList<Integer> part1_config) {
        this.part1_config = part1_config;
    }

    public ArrayList<Integer> getPart2_config() {
        return part2_config;
    }

    public void setPart2_config(ArrayList<Integer> part2_config) {
        this.part2_config = part2_config;
    }

    public ArrayList<Integer> getPart3_config() {
        return part3_config;
    }

    public void setPart3_config(ArrayList<Integer> part3_config) {
        this.part3_config = part3_config;
    }

    public ArrayList<Integer> getFont_size() {
        return font_size;
    }

    public void setFont_size(ArrayList<Integer> font_size) {
        this.font_size = font_size;
    }
    
    public double getRoot_find() {
        return root_find;
    }

    public void setRoot_find(double root_find) {
        this.root_find = root_find;
    }

    public double getEPSILON() {
        return EPSILON;
    }

    public void setEPSILON(double EPSILON) {
        this.EPSILON = EPSILON;
    }
     public int getRoot_find1() {
        return root_find1;
    }

    public void setRoot_find1(int root_find1) {
        this.root_find1 = root_find1;
    }

    public double getRoot_newtonMethod() {
        return root_newtonMethod;
    }

    public void setRoot_newtonMethod(double root_newtonMethod) {
        this.root_newtonMethod = root_newtonMethod;
    }
   
    public void setExtreme_1(double extreme_1) {
        this.extreme_1 = extreme_1;
    }

    public double getX_1() {
        return X_1;
    }

    public void setX_1(double X_1) {
        this.X_1 = X_1;
    }

    public double getX_2() {
        return X_2;
    }

    public void setX_2(double X_2) {
        this.X_2 = X_2;
    }

    public double getX_3() {
        return X_3;
    }

    public void setX_3(double X_3) {
        this.X_3 = X_3;
    }

    public double getY_1() {
        return Y_1;
    }

    public void setY_1(double Y_1) {
        this.Y_1 = Y_1;
    }

    public double getY_2() {
        return Y_2;
    }

    public void setY_2(double Y_2) {
        this.Y_2 = Y_2;
    }

    public double getY_3() {
        return Y_3;
    }

    public void setY_3(double Y_3) {
        this.Y_3 = Y_3;
    }

    public double getFind_area() {
        return find_area;
    }

    public void setFind_area(double find_area) {
        this.find_area = find_area;
    }
    
}
 
 
