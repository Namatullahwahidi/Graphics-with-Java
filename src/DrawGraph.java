
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class DrawGraph extends JPanel{
    
    ArrayList<Double> arr1_x=new ArrayList<>();
    ArrayList<Double> arr2_y=new ArrayList<>();
    ArrayList<Double> arr=new ArrayList<>();
    double x_max;
    double y_max;
    double x_min;
    double y_min;
    double find_root;
    double find_root1;
    double X_1_point;
    double X_2_point;
    double X_3_point;
    double Y_1_point;
    double Y_2_point;
    double Y_3_point;
    double findErea;
    double findEraeCondition=0;
    double conditionOf_max_min=0;
    double conditionof_fourier=0;
    
    double xx,yy;
    int r=0;
    double angle=0;
    double angle1=0;
    int time=0;
    
    int centerX=200;
    int centerY=200;
    ArrayList<String>name_config=new ArrayList<>();
    ArrayList<String>font_color_config=new ArrayList<>();
    ArrayList<Integer>R_color=new ArrayList<>();
    ArrayList<Integer>G_color=new ArrayList<>();
    ArrayList<Integer>B_color=new ArrayList<>();
    ArrayList<Integer>font_size=new ArrayList<>();
   
    public DrawGraph(){
        
        this.setBounds(250,10 ,990,400);

        setBackground(Color.yellow);
        this.angle1=0;
        setLayout(null);
    }

    
    public void run(){
        
        try {
            Thread.sleep(getTime());
            xx = Math.cos(angle1) * getR(); 
            yy = Math.sin(angle1) * getR(); 
            arr.add(yy);
            angle1+=getAngle();
        } catch (InterruptedException ex) {
            Logger.getLogger(DrawGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
       // g.fillOval(300, 200,100,100);
        
        int f=102;
        g.setColor(new Color(f,0,153));
        
        if(getR_color().size()>0) {

            for(Integer i=0;i<getName_config().size();i++){

                if((getName_config().get(i).trim().equals("line")) && (getFont_color_config().get(i).trim().equals("color"))){
                    g.setColor(new Color(getR_color().get(i), getG_color().get(i), getB_color().get(i)));

                }

                //System.out.println(getName_config().get(i)+" "+getFont_color_config().get(i)+","+getR_color().get(i)+","+getG_color().get(i)+","+getB_color().get(i));
                if((getName_config().get(i).trim().equals("Background")) && (getFont_color_config().get(i).trim().equals("color"))){
                    setBackground((new Color(getR_color().get(i), getG_color().get(i), getB_color().get(i))));

                }
            }

        }
        
        //thats coordinat for finding the root of any equations
        
        
        g.drawLine(10, 200, 980, 200);//x line
        g.drawLine(200, 30, 200, 350);// y line

        g.drawLine(980, 200, 970, 190);
        g.drawLine(980, 200, 970, 210);
        g.drawLine(200, 30, 190, 40);
        g.drawLine(200, 30, 210, 40);

        g.drawString("X", 970, 190);
        g.drawString("Y", 220, 40);
        g.setColor(Color.blue);
        
        if(getConditionof_fourier()==1){
        
                g2d.draw(new Line2D.Double(200, 200, 200 + xx, 200 + yy));
                g2d.draw(new Line2D.Double(200 + xx, 200 + yy, 300, 200 + yy));
                g.drawOval(150, 150,100,100); 
                float x = (float)xx; 
                
                for (int i = 0;  i < arr.size(); i++) { 
                    double y = arr.get(i); 
                       g.setColor(Color.red);
                       g.fillOval(300+i, 200+(int)y,4, 4);
//                    ((Graphics2D) g).setColor(Color.red);
//                    ((Graphics2D) g).drawString("", 300 + i, 200 + (float)y); 
                } 
                
                run();
                repaint();
        }
        
        if(getFont_size().size()>0) {

            for(Integer i=0;i<getName_config().size();i++){
                if((getName_config().get(i).trim().equals("numbers"))){
                    g.setFont(new Font(getFont_color_config().get(i),Font.CENTER_BASELINE,getFont_size().get(i)));
                    System.out.println(getFont_color_config().get(i));

                }

                if((getName_config().get(i).trim().equals("numbers_font")) &&(getFont_color_config().get(i).trim().equals("color"))){
                    g.setColor(new Color(getR_color().get(i), getG_color().get(i), getB_color().get(i)));

                }
            }
        }
       // g.setColor(new Color(255,0,255));
        g.drawString("Third Homework of java by Namatulla wahidi", 100,20);
        Polygon p3=  new Polygon();
        int xcoordinat=6;
        int ycoordinat=6;
        int xLength=380/2/xcoordinat;//31
        int yLength=350/2/ycoordinat;//29 
        double step;
        double dx;
        double dy=this.getY_max();
        double dx_min=Math.abs(this.getX_min());
        double dy_min=Math.abs(this.getY_min());
       
        if(this.getX_max()>dx_min){
            dx=this.getX_max();
        }
        else
            dx=dx_min;
        if(this.getY_max()>dy_min){
            dy=this.getY_max();
        }
        else
            dy=dy_min;
        double h;
       // Integer x_1,y_1;
        
        step=dx;
        double k=step*2/10;
        step*=-1;
//        System.out.println("step="+step);
//        System.out.println("x_linght="+xLength);
    
        double temp = 0;
        if(this.getArr1_x().size()>0) {
            for (int i = 1; i < xcoordinat; i++, step += k) {
                temp = (int) (step * 10);
                g.drawString(Double.toString((temp/10)), 10 + (i * xLength)-3, 200 + 20);
                g.drawString("|", 17 + (i * xLength)-3, 200);
                
                
            }
            
            step += k;
            for (int i = xcoordinat + 1; i < xcoordinat * 2; i++, step += k) {
                temp = (int) (step * 10);
                g.drawString(Double.toString((temp/10)), 15 + (i * xLength) - 3, 200 + 20);
                g.drawString("|", 15 + (i * xLength) - 3, 200);
            }
           
            
            step = dy;
            k = step * 2 / 10;
            step *= -1;
            for (int i = 1; i < ycoordinat; i++, step += k) {
                 temp = (int) (step * 10);
                g.drawString(Double.toString((temp/10)), 200 - 25, 350 + 25 - (i * yLength));
            }
            step += k;
            for (int i = ycoordinat + 1; i < ycoordinat * 2; i++, step += k) {
                 temp = (int) (step * 10);
                g.drawString(Double.toString((temp/10)), 200 - 25, 350 + 25 - (i * yLength));
            }
            // that is for find the root fo any equation
            double xx = 150/dx;
            double yy=(150/dy);
            if(getFind_root1()==1){
                   
                        //g.fillOval(200+((int) Math.round(getFind_root())),200,10,10);
                        g2d.setPaint(Color.red);
                        
                        g2d.draw(new Line2D.Double(xx *getFind_root()+200,197, xx*getFind_root()+200, 203));
                        g2d.draw(new Line2D.Double(xx *getFind_root()+201,197, xx*getFind_root()+201, 203));
                        g2d.draw(new Line2D.Double(xx *getFind_root()+199,197, xx*getFind_root()+199, 203));
                        g2d.drawString(Double.toString(getFind_root()), (int)(xx*getFind_root()+200), 195);
                     //   System.out.println("GetFindRoot::"+getFind_root()+" and step is:"+Math.round(step));
                        
                     // that is for exreme point of graph
                     setFindEraeCondition(0);//no showing the area state there
            }
//            if(getFindEraeCondition()==1){
//                g2d.setPaint(Color.red);
//                g2d.drawString(Double.toString(getFindErea()), (int)(50),290);
//                g2d.drawString( "Area", (int)(50),300);
//            }
            if(getConditionOf_max_min()==1){
                int x_line_point; 
                int y_line_point;
              
                x_line_point=(int)(200+this.getX_1_point()*xx);
                y_line_point=(int)(200-this.getY_1_point()*yy);
                   

                 g.setColor(Color.red);
                // g.fillOval(x_line_point,y_line_point,5,5);
                 //g.drawLine(x_line_point,5,y_line_point,2);
                 
                 //g2d.draw(new Line2D.Double(xx *getX_1_point()+201, xx*getFind_root()+201));
                 //p3.addPoint((int)(200+this.X_1_point*xx),(int)(200-this.Y_1_point*xx));
                 double x_nokta=this.getX_1_point();
                 double y_nokta=this.getY_1_point();
                 String str="("+String.valueOf(x_nokta)+" , "+String.valueOf(y_nokta)+")";
                 
                 g2d.draw(new Line2D.Double(x_line_point,y_line_point-3, x_line_point, y_line_point+5));
                 g2d.draw(new Line2D.Double(x_line_point+1,y_line_point-3, x_line_point+1, y_line_point+5));
                 g2d.draw(new Line2D.Double(x_line_point+2,y_line_point-3, x_line_point+2, y_line_point+5));
                 g2d.drawString(str, x_line_point, y_line_point+15);
                 g2d.drawString("Extreme ", x_line_point+25, y_line_point+30);
                 
            }
            else if(getConditionOf_max_min()==2){
                int x_line_point1; 
                int y_line_point1;
                int x_line_point2; 
                int y_line_point2;
                x_line_point1=(int)(200+this.getX_1_point()*xx);
                y_line_point1=(int)(200-this.getY_1_point()*yy);
                x_line_point2=(int)(200+this.getX_2_point()*xx);
                y_line_point2=(int)(200-this.getY_2_point()*yy);
                
                 g.setColor(Color.red);
               
                 double x_nokta=Math.round(this.getX_1_point());
                 double y_nokta=Math.round(this.getY_1_point());
                 double x_nokta2=Math.round(this.getX_2_point());
                 double y_nokta2=Math.round(this.getY_2_point());
                 
                 String str="("+String.valueOf(x_nokta)+" , "+String.valueOf(y_nokta)+")";
                 String str2="("+String.valueOf(x_nokta2)+" , "+String.valueOf(y_nokta2)+")";
                 
                 g2d.draw(new Line2D.Double(x_line_point1,y_line_point1-3, x_line_point1, y_line_point1+5));
                 g2d.draw(new Line2D.Double(x_line_point1+1,y_line_point1-3, x_line_point1+1, y_line_point1+5));
                 g2d.draw(new Line2D.Double(x_line_point1+2,y_line_point1-3, x_line_point1+2, y_line_point1+5));
                 g2d.drawString(str, x_line_point1, y_line_point1+15);
                 g2d.drawString("Extreme ", x_line_point1+25, y_line_point1+30);
                 
                 g2d.draw(new Line2D.Double(x_line_point2,y_line_point2-3, x_line_point2, y_line_point2+5));
                 g2d.draw(new Line2D.Double(x_line_point2+1,y_line_point2-3, x_line_point2+1, y_line_point2+5));
                 g2d.draw(new Line2D.Double(x_line_point2+2,y_line_point2-3, x_line_point2+2, y_line_point2+5));
                 g2d.drawString(str2, x_line_point2, y_line_point2-15);
                 g2d.drawString("Extreme ", x_line_point2+25, y_line_point2-30);
 
            }
            
             else if(getConditionOf_max_min()==4){
                int x_line_point1; 
                int y_line_point1;
                int x_line_point2; 
                int y_line_point2;
                x_line_point1=(int)(200+this.getX_1_point()*xx);
                y_line_point1=(int)(200-this.getY_1_point()*yy);
                x_line_point2=(int)(200+this.getX_2_point()*xx);
                y_line_point2=(int)(200-this.getY_2_point()*yy);
                
                 g.setColor(Color.red);
               
                 double x_nokta=Math.round(this.getX_1_point());
                 double y_nokta=Math.round(this.getY_1_point());
                 double x_nokta2=Math.round(this.getX_2_point());
                 double y_nokta2=Math.round(this.getY_2_point());
                 
                 String str="("+String.valueOf(x_nokta)+" , "+String.valueOf(y_nokta)+")";
                 String str2="("+String.valueOf(x_nokta2)+" , "+String.valueOf(y_nokta2)+")";
                 
                 g2d.draw(new Line2D.Double(x_line_point1,y_line_point1-3, x_line_point1, y_line_point1+5));
                 g2d.draw(new Line2D.Double(x_line_point1+1,y_line_point1-3, x_line_point1+1, y_line_point1+5));
                 g2d.draw(new Line2D.Double(x_line_point1+2,y_line_point1-3, x_line_point1+2, y_line_point1+5));
                 g2d.drawString(str, x_line_point1, y_line_point1+15);
                 g2d.drawString("Extreme ", x_line_point1+25, y_line_point1+30);
                 
                 g2d.draw(new Line2D.Double(x_line_point2,y_line_point2-3, x_line_point2, y_line_point2+5));
                 g2d.draw(new Line2D.Double(x_line_point2+1,y_line_point2-3, x_line_point2+1, y_line_point2+5));
                 g2d.draw(new Line2D.Double(x_line_point2+2,y_line_point2-3, x_line_point2+2, y_line_point2+5));
                 g2d.drawString(str2, x_line_point2, y_line_point2-15);
                 g2d.drawString("Extreme ", x_line_point2+25, y_line_point2-30);
 
            }
        }
        
       double r=(150/dx);
       double rr=(150/dy);

       g.setColor(Color.blue);
     
        for(int i=1;i<this.getArr2_y().size();i++){
             //p3.addPoint((int)(200+this.getArr1_x().get(i)*r),(int)(200-this.getArr2_y().get(i)*rr));
             g2d.draw(new Line2D.Double(this.getArr1_x().get(i-1)*r+200, -1*this.getArr2_y().get(i-1)*rr+200, this.getArr1_x().get(i)*r+200, -1*this.getArr2_y().get(i)*rr+200));

        }
       

        if(getR_color().size()>0) {

            for(Integer i=0;i<getName_config().size();i++){

                if((getName_config().get(i).trim().equals("graph")) && (getFont_color_config().get(i).trim().equals("color"))){
                    g.setColor(new Color(getR_color().get(i), getG_color().get(i), getB_color().get(i)));

                }

            }

        }
        g.drawPolyline(p3.xpoints, p3.ypoints, p3.npoints);
    }

    public ArrayList<Double> getArr1_x() {
        return arr1_x;
    }

    public void setArr1_x(ArrayList<Double> arr1) {
        this.arr1_x = arr1;
    }

    public ArrayList<Double> getArr2_y() {
        return arr2_y;
    }

    public void setArr2_y(ArrayList<Double> arr2) {
        this.arr2_y = arr2;
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

    public double getX_min() {
        return x_min;
    }

    public void setX_min(double x_min) {
        this.x_min = x_min;
    }

    public double getY_min() {
        return y_min;
    }

    public void setY_min(double y_min) {
        this.y_min = y_min;
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

    public ArrayList<Integer> getR_color() {
        return R_color;
    }

    public void setR_color(ArrayList<Integer> r_color) {
        R_color = r_color;
    }

    public ArrayList<Integer> getG_color() {
        return G_color;
    }

    public void setG_color(ArrayList<Integer> g_color) {
        G_color = g_color;
    }

    public ArrayList<Integer> getB_color() {
        return B_color;
    }

    public void setB_color(ArrayList<Integer> b_color) {
        B_color = b_color;
    }

    public ArrayList<Integer> getFont_size() {
        return font_size;
    }

    public void setFont_size(ArrayList<Integer> font_size) {
        this.font_size = font_size;
    }
    
    public double getFind_root() {
        return find_root;
    }

    public void setFind_root(double find_root) {
        this.find_root = find_root;
    }

    public double getFind_root1() {
        return find_root1;
    }

    public void setFind_root1(double find_root1) {
        this.find_root1 = find_root1;
    }

   
    public double getConditionOf_max_min() {
        return conditionOf_max_min;
    }

    public void setConditionOf_max_min(double conditionOf_max_min) {
        this.conditionOf_max_min = conditionOf_max_min;
    }

    public double getX_1_point() {
        return X_1_point;
    }

    public void setX_1_point(double X_1_point) {
        this.X_1_point = X_1_point;
    }

    public double getY_1_point() {
        return Y_1_point;
    }

    public void setY_1_point(double Y_1_point) {
        this.Y_1_point = Y_1_point;
    }

    public double getX_2_point() {
        return X_2_point;
    }

    public void setX_2_point(double X_2_point) {
        this.X_2_point = X_2_point;
    }

    public double getX_3_point() {
        return X_3_point;
    }

    public void setX_3_point(double X_3_point) {
        this.X_3_point = X_3_point;
    }

    public double getY_2_point() {
        return Y_2_point;
    }

    public void setY_2_point(double Y_2_point) {
        this.Y_2_point = Y_2_point;
    }

    public double getY_3_point() {
        return Y_3_point;
    }

    public void setY_3_point(double Y_3_point) {
        this.Y_3_point = Y_3_point;
    }

    public double getFindErea() {
        return findErea;
    }

    public void setFindErea(double findErea) {
        this.findErea = findErea;
    }

    public double getFindEraeCondition() {
        return findEraeCondition;
    }

    public void setFindEraeCondition(double findEraeCondition) {
        this.findEraeCondition = findEraeCondition;
    }

    public double getConditionof_fourier() {
        return conditionof_fourier;
    }

    public void setConditionof_fourier(double conditionof_fourier) {
        this.conditionof_fourier = conditionof_fourier;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    
    
    
}

        
        
        