
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sun.security.krb5.internal.rcache.AuthList;


public class Controller{
    View view=new View();
    private JTextArea area;
    private final JRadioButton rdb1,rdb2,rdb3,rdb4,rdb5;
    private JButton cal_button,root_button;
    private double a,b,c,Xs,Xe;
    private double epsilon;
    private JTextField f1,f2,f3,f4,f5,file,f6,N_interwal;
    DrawGraph drawGraph;
    private String fileName;
    Model model;
    JComboBox comboBox;
    private int equation_number=1,N_inter;
    public Controller(){
        
        this.rdb1=view.getRadio_1();
        this.rdb2=view.getRadio_2();
        this.rdb3=view.getRadio_3();
        this.rdb4=view.getRadio_4();
        this.rdb5=view.getRadio_5();
        this.f1=view.getA_value();
        this.f2=view.getB_value();
        this.f3=view.getC_value();
        this.f4=view.getXs_value();
        this.f5=view.getXe_value();
        this.N_interwal=view.getN_textField();
        this.f6=view.getEpsilon_value();
        this.cal_button=view.getCalculate();
        this.root_button=view.getFind_root();
        this.area=view.getArea();
        this.comboBox=view.getjComboBox1();
        this.drawGraph=new DrawGraph();
        this.view.add(this.drawGraph,BorderLayout.CENTER);
        this.view.setVisible(true);
        
        this.root_button.addActionListener((ActionEvent ae)->{
            this.a=Double.valueOf(this.f1.getText());
             this.b=Double.valueOf(this.f2.getText());
             this.c=Double.valueOf(this.f3.getText());
             this.Xs=Double.valueOf(this.f4.getText());
             this.Xe=Double.valueOf(this.f5.getText());
             this.N_inter=Integer.valueOf(this.N_interwal.getText());
             this.epsilon=Double.valueOf(this.f6.getText());
             this.area.setText(" ");
             this.model=new Model(a, b, c, Xs, Xe);
             this.model.setRoot_find1(1);
             this.model.setEPSILON(this.epsilon);
             int selectedIt=this.comboBox.getSelectedIndex();
             
             if(selectedIt==0){
                 //System.out.println("Besitcion Mehtod");
                if(this.model.calculate_root(equation_number)){
                    this.drawGraph.setFind_root(this.model.getRoot_find());
                    this.drawGraph.setFind_root1(1);

                    }
                else{
                    this.drawGraph.setFind_root1(0);

                } 
             }
             else if(selectedIt==1){
                 //System.out.println("Newton's Method");
                   if(this.model.calculate_root_byNewtonws(equation_number)){
                      
                       this.drawGraph.setFind_root(this.model.getRoot_newtonMethod());
                       this.drawGraph.setFind_root1(1);
 
                    }
                else{
                    this.drawGraph.setFind_root1(0);

                }
             }
             else if(selectedIt==2){
                 // reading from file 
                  File file=new File("dosya.txt");
                     if(file.exists()){  
                        this.drawGraph.setArr1_x(this.model.X_Cost_File("dosya.txt"));
                        this.drawGraph.setArr2_y(this.model.Y_Cost_File("dosya.txt"));
                        this.drawGraph.setY_max(Collections.max(this.model.Y_Cost_File("dosya.txt")));
                        this.drawGraph.setX_max(Collections.max(this.model.X_Cost_File("dosya.txt")));
                        this.drawGraph.setX_min(Collections.min(this.model.X_Cost_File("dosya.txt")));
                        this.drawGraph.setY_min(Collections.min(this.model.Y_Cost_File("dosya.txt")));
                    
                     }
                     else{
                         this.drawGraph.setArr1_x(new ArrayList<Double>());
                         this.drawGraph.setArr2_y(new ArrayList<Double>());
                         this.area.setText("Your file doesn't exist");
                     }
           
             }
             else if(selectedIt==3){
                // System.out.println("read configuration from file");
                    this.model.Configuration("colors.txt");
                    this.drawGraph.setName_config(this.model.getName_config());
                    this.drawGraph.setFont_color_config(this.model.getFont_color_config());
                    this.drawGraph.setR_color(this.model.getPart1_config());
                    this.drawGraph.setG_color(this.model.getPart2_config());
                    this.drawGraph.setB_color(this.model.getPart3_config());
                    this.drawGraph.setFont_size(this.model.getFont_size());
                    
                  
             }
             else if(selectedIt==4){
                 //Extreme
                
                this.model.Find_Max_point(getEquation_number());
                if(getEquation_number()==1){
                    if(this.Xs<this.model.getX_1() && this.model.getX_1()<this.Xe){


                    this.drawGraph.setX_1_point(this.model.getX_1());
                    this.drawGraph.setY_1_point(this.model.getY_1());
                    this.drawGraph.setConditionOf_max_min(1);
                    
                } 
                 
                else{
                    this.drawGraph.setConditionOf_max_min(0);
                }
                
                }
                else if(getEquation_number()==2){
                            if(this.Xs<this.model.getX_1() && this.model.getX_1()<this.Xe ||
                                    this.Xs<this.model.getX_2() && this.model.getX_2()<this.Xe){
                             

                            this.drawGraph.setX_1_point(this.model.getX_1());
                            this.drawGraph.setY_1_point(this.model.getY_1());
                            this.drawGraph.setX_2_point(this.model.getX_2());
                            this.drawGraph.setY_2_point(this.model.getY_2());
                            this.drawGraph.setConditionOf_max_min(2);

                             } 
                 
                            else{
                                this.drawGraph.setConditionOf_max_min(0);
                            }
                }
                else if(getEquation_number()==4){
                    if(this.Xs<this.model.getX_1() && this.model.getX_1()<this.Xe ||
                                    this.Xs<this.model.getX_2() && this.model.getX_2()<this.Xe){
                        
                            this.drawGraph.setX_1_point(this.model.getX_1());
                            this.drawGraph.setY_1_point(this.model.getY_1());
                            this.drawGraph.setX_2_point(this.model.getX_2());
                            this.drawGraph.setY_2_point(this.model.getY_2());
                            this.drawGraph.setConditionOf_max_min(4);
                        
                    }
                    else{
                               this.drawGraph.setConditionOf_max_min(0);
                           }
                    
                }
             }
             else if(selectedIt==5){
                 //System.out.println("find the area ");
                 this.model.find_ereaByRectangle(this.N_inter,getEquation_number());
                 this.drawGraph.setFindEraeCondition(1);
                 this.drawGraph.setFindErea(this.model.getFind_area());
                 this.area.setText("");
                 this.area.append("Trapezoidal Method:\n");
                 this.view.getArea_label().setText(String.valueOf(this.model.getFind_area()));
                 this.area.append(String.valueOf(this.model.getFind_area()));
                 
             }
             else if(selectedIt==6){
                  //System.out.println("find the area ");
                 this.model.find_ereaByRiemenn(this.N_inter,getEquation_number());
                 this.drawGraph.setFindEraeCondition(1);
                 this.drawGraph.setFindErea(this.model.getFind_area());
                 this.area.setText("");
                 this.area.append("Rectangle Method:\n");
                 this.view.getArea_label().setText(String.valueOf(this.model.getFind_area()));
                 this.area.append(String.valueOf(this.model.getFind_area()));
             }
             else if(selectedIt==7){
                 this.drawGraph.setR((int)(this.a));
                 this.drawGraph.setAngle(this.b);
                 this.drawGraph.setTime(this.N_inter);
                 this.drawGraph.setConditionof_fourier(1);
                 
             }
             this.drawGraph.repaint();
        
        });
        
        this.cal_button.addActionListener((ActionEvent ae) -> {
       
                 
                this.a=Double.valueOf(this.f1.getText());
                this.b=Double.valueOf(this.f2.getText());
                this.c=Double.valueOf(this.f3.getText());
                this.Xs=Double.valueOf(this.f4.getText());
                this.Xe=Double.valueOf(this.f5.getText());
               
                this.area.setText("");
          
                this.model=new Model(a, b, c, Xs, Xe);
                
                
                this.model.setRoot_find1(0);// don't show the root of equation when pushing the calculate button
                this.drawGraph.setConditionOf_max_min(0);// don't show the exreme point of equation when pushing the calculate button
                
                
                 if(rdb1.isSelected()){
                    this.drawGraph.setBackground(Color.yellow);
                    this.area.append("x            y\n");
                    for(int i=0;i<this.model.GetData_x().size();i++){
                        this.area.append(this.model.GetData_x().get(i)+"         "+this.model.GetData_y(1).get(i)+"\n");
                     }
                    this.drawGraph.setArr1_x(this.model.GetData_x());
                    this.drawGraph.setArr2_y(this.model.GetData_y(1));
                    this.drawGraph.setX_max(this.model.getX_max());
                    this.drawGraph.setY_max(Collections.max(this.model.GetData_y(1)));
                    this.drawGraph.setX_min(Collections.min(this.model.GetData_x()));
                    this.drawGraph.setY_min(Collections.min(this.model.GetData_y(1)));
                    
                   this.setEquation_number(1);// that is for find the root
                }  
                
                else if(rdb2.isSelected()){
                     this.drawGraph.setBackground(Color.green);
                     this.area.append("x            y\n");
                     for(int i=0;i<this.model.GetData_x().size();i++){
                        this.area.append(this.model.GetData_x().get(i)+"         "+this.model.GetData_y(2).get(i)+"\n");
                    }
                    this.drawGraph.setArr1_x(this.model.GetData_x());
                    this.drawGraph.setArr2_y(this.model.GetData_y(2));
                    this.drawGraph.setX_max(this.model.getX_max());
                    this.drawGraph.setY_max(Collections.max(this.model.GetData_y(2)));
                    this.drawGraph.setX_min(Collections.min(this.model.GetData_x()));
                    this.drawGraph.setY_min(Collections.min(this.model.GetData_y(2)));
                    
                     this.setEquation_number(2);// that is for find the root
                    
                }
                
                else if(rdb3.isSelected()){
                    this.drawGraph.setBackground(Color.cyan);
                    this.area.append("x            y\n");
                    for(int i=0;i<this.model.GetData_x().size();i++){
                        this.area.append(this.model.GetData_x().get(i)+"         "+this.model.GetData_y(3).get(i)+"\n");
                     }
                    this.drawGraph.setArr1_x(this.model.GetData_x());
                    this.drawGraph.setArr2_y(this.model.GetData_y(3));
                    this.drawGraph.setX_max(this.model.getX_max());
                    this.drawGraph.setY_max(Collections.max(this.model.GetData_y(3)));
                    this.drawGraph.setX_min(Collections.min(this.model.GetData_x()));
                    this.drawGraph.setY_min(Collections.min(this.model.GetData_y(3)));
                    
                     this.setEquation_number(3);// that is for find the root
                }
                
                
                 else if(rdb4.isSelected()){
                    this.drawGraph.setBackground(Color.green);
                    this.area.append("x           sin\n");
                    for(int i=0;i<this.model.GetData_x().size();i++){
                        this.area.append(this.model.GetData_x().get(i)+"         "+this.model.GetData_y(4).get(i)+"\n");
                     }
                    this.drawGraph.setArr1_x(this.model.GetData_x());
                    this.drawGraph.setArr2_y(this.model.GetData_y(4));
                    this.drawGraph.setX_max(this.model.getX_max());
                    this.drawGraph.setY_max(Collections.max(this.model.GetData_y(4)));
                    this.drawGraph.setX_min(Collections.min(this.model.GetData_x()));
                    this.drawGraph.setY_min(Collections.min(this.model.GetData_y(4)));
                    
                    this.setEquation_number(4);// that is for find the root
                    
                }
                 
                 else if(rdb5.isSelected()){
                    this.drawGraph.setBackground(Color.cyan);  
                    this.area.append("x           cos\n");
                    for(int i=0;i<this.model.GetData_x().size();i++){
                        this.area.append(this.model.GetData_x().get(i)+"         "+this.model.GetData_y(5).get(i)+"\n");
                     }
                    this.drawGraph.setArr1_x(this.model.GetData_x());
                    this.drawGraph.setArr2_y(this.model.GetData_y(5));
                    this.drawGraph.setX_max(this.model.getX_max());
                    this.drawGraph.setY_max(Collections.max(this.model.GetData_y(5)));
                    this.drawGraph.setX_min(Collections.min(this.model.GetData_x()));
                    this.drawGraph.setY_min(Collections.min(this.model.GetData_y(5)));
                    
                    this.setEquation_number(5);// that is for find the root
                }
                
                
            this.drawGraph.repaint();
        });
        
        this.drawGraph.setBackground(Color.yellow);
        this.drawGraph.setLayout(null);
    }

    public int getEquation_number() {
        return equation_number;
    }

    public void setEquation_number(int equation_number) {
        this.equation_number = equation_number;
    }
    
    
    public static void main(String[] args) {
        new Controller();
    }
    
    
}