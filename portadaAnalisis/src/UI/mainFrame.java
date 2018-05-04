/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import analisisnumerico.Bairstow;
import analisisnumerico.Evaluador;
import analisisnumerico.Funcion;
import analisisnumerico.Horner;
import analisisnumerico.Muller;
import analisisnumerico.ProcesoTarFerrari;
import analisisnumerico.Secante;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class mainFrame extends javax.swing.JFrame {

    
    private double x1;
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    public String getFuncion(){
    int funcion=cmbFuncion.getSelectedIndex()+1;
     switch(funcion){
         case 1:
             
             return "x³+0x²+3x-1";
                
         case 2:
            
             return "x³-4.65x²-49.92x-76.69";    
         case 3:
            
             return "x⁴+x³+0.56x²-1.44x-2.88";
         case 4:
            
             return "x⁴+0x³-3x²+5x+2";
         case 5:
             txtMenor.setText("-5");
             txtMayor.setText("2");
             return "x⁵-3x⁴-23x³+55x²+74x-120";
         case 6:
             txtMenor.setText("-3");
             txtMayor.setText("3");
             return "x⁶-0x⁵+7x⁴+x³+0x²+3x-1";
         
         default:
             JOptionPane.showMessageDialog(null, "ERROR");
     }
        
        
     return "";
    }
    
    public String getFuncionMuller(){
    int funcion=cmbFuncion.getSelectedIndex()+1;
     switch(funcion){
         case 1:
             txtMenor.setText("0");
             txtMayor.setText("1");
             this.x1=0.5;
             return "x^3+0*x^2+3*x-1";
         case 2:
              txtMenor.setText("-5");
             txtMayor.setText("3");
             this.x1=0;
             return "x^3-4.65*x^3-49.92*x-76.69";    
         case 3:
             txtMenor.setText("-2");
             txtMayor.setText("2");
             this.x1=1;
             return "x^4+x^3+0.56*x^2-1.44*x-2.88";
         case 4:
              txtMenor.setText("-4");
             txtMayor.setText("0");
             this.x1=-1;
             return "x^4+0*x^3-3*x^2+5*x+2";
         case 5:
             txtMenor.setText("-5");
             txtMayor.setText("2");
             this.x1=1;
             return "x^5-3*x^4-23*x^3+55*x^2+74*x-120";
         case 6:
             txtMenor.setText("-3");
             txtMayor.setText("3");
             this.x1=0;
             return "x^6-0*x^5+7*x^4+x^3+0*x^2+3*x-1";
         case 7:
             txtMenor.setText("0");
             txtMayor.setText("2");
             this.x1=1;
             return "ln(1+x)-cos(x)";
         case 8:
             txtMenor.setText("-1");
             txtMayor.setText("13");
             this.x1=0;
             return "10*sin(x)+3*cos(x)";
         case 9:
             txtMenor.setText("1.1");
             txtMayor.setText("2");
             this.x1=1.2;
             return "exp(3*x-3)-ln(x-1)^2+1";
         case 10:
             txtMenor.setText("1");
             txtMayor.setText("2");
             this.x1=1.5;
             return "cos(0.785-x*(1+x^2)^(1/2))";
         default:
             JOptionPane.showMessageDialog(null, "ERROR");
     }
        
        
     return "";
    }
    
    public void horner(){
        String funcion = getFuncion();
         
        double[] coeficientes = Funcion.SacarCoeficientes(funcion);
        double Es = 0.05;

        double x0 = -1.8;
        Horner h = new Horner();
        double [] rs= Horner.raizHorny(coeficientes, x0,Es);

        txtRaizHorner.setText(String.valueOf(rs[0]));
        txtErrorHorner.setText(String.valueOf(rs[1]));
    
    }
    
    public void muller(){
        
        double x0,x2,x3,c;
        String funcion=getFuncionMuller();
        //x^3 +3*x^2 +3*x +2
         //la entrada de exponente debe de ser con ^
        x0=Double.parseDouble(txtMenor.getText());
        
        x2=Double.parseDouble(txtMayor.getText());
        c=3.0;
        Funcion ff =new Funcion(funcion);
        Muller mu = new Muller();
        double exp=2-c;
        double Err=(0.5)*Math.pow(10, exp);
        System.out.println("error="+Err);
        //Secante sec = new Secante();
        double [] r=Muller.ABC(ff, x0, x1,x2, Err);

        txtRaizmuller.setText(""+r[1]);
        txtErrormuller.setText(""+r[0]);
    
    
    }
    
    public void tartaglia(){
       double a, b, c, cubo;
        String funcion = getFuncion();
        
        double[] coeficientes= Funcion.SacarCoeficientes(funcion);
        a=coeficientes[1];
        b=coeficientes[2];
        c=coeficientes[3];
       cubo=coeficientes[0];

        if (cubo != 1) {
            a = a / cubo;
            b = b / cubo;
            c = c / cubo;
            cubo = 1;
        }

        double resp[] = ProcesoTarFerrari.tartaglia(a, b, c);

        if (resp.length == 4) {
            txtx2iTarttaglia.setText(String.valueOf(resp[3]));
            txtx3iTarttaglia.setText(String.valueOf(resp[3]));
           
        } else {
           
        }
        txtx1Tarttaglia.setText(String.valueOf(resp[0]));
        txtx2Tarttaglia.setText(String.valueOf(resp[1]));
        txtx3Tarttaglia.setText(String.valueOf(resp[2]));
      
    }
    
    public void ferrari(){
      double a, b, c, d,cuarta;

 
        String funcion=getFuncion();
        
        double[] coeficientes= Funcion.SacarCoeficientes(funcion);
        a=coeficientes[1];
        b=coeficientes[2];
        c=coeficientes[3];
        d=coeficientes[4];
       cuarta=coeficientes[0];

     if (cuarta != 1) {
            a = a / cuarta;
            b = b / cuarta;
            c = c / cuarta;
            d=d/cuarta;
            cuarta = 1;
        }

        double raiz[] = ProcesoTarFerrari.ferrari(a, b, c, d);

        txtFerrari1.setText(String.valueOf(raiz[0]));
        txtFerrari2.setText(String.valueOf(raiz[1]));
        txtFerrari3.setText(String.valueOf(raiz[2]));
        txtFerrari4.setText(String.valueOf(raiz[3]));
    
    
    }
    
    public void secante(){
    
     double x0,x2,c;
        //int c;
        String funcion=getFuncionMuller();
        
        x0=Double.parseDouble(txtMenor.getText());
        x2=Double.parseDouble(txtMayor.getText());
        c=3.0;
        double exp=2-c;
        double Err=(0.5)*Math.pow(10, exp);

        Funcion ff =new Funcion(funcion);
        Secante sec = new Secante();
        double [] r=sec.raiz(ff, x0, x2, Err);

        txtRaizsecante.setText(""+r[0]);
        txtErrorsecante.setText(""+r[1]);
    }
    
     public void bairstow() {
        Evaluador eva = new Evaluador();
        String funcion = cmbFuncion.getSelectedItem().toString(), raices = "Raices:\n";
        Bairstow b = new Bairstow();
        b.setGrado(eva.gradoMayor(funcion));
        Object[] bair = b.bairstow(-2, -2, eva.obtenerCoeficientes(eva.gradoMayor(funcion), funcion));
        for (Object ob : bair) {
            if (ob != null) {
                raices += ob.toString() + "\n";
            }
        }
        if (eva.gradoMayor(funcion) > 4) {
                for (Object obj : b.getRaices2()) {
                    if (obj != null) {
                        raices += obj.toString() + "\n";
                    }
                }
            }
        txtBairstow.setText(raices);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbFuncion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMenor = new javax.swing.JTextField();
        txtMayor = new javax.swing.JTextField();
        tabPanel = new javax.swing.JTabbedPane();
        panelHorner = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtRaizHorner = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtErrorHorner = new javax.swing.JTextField();
        panelTartaglia = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtx1Tarttaglia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtx2Tarttaglia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtx3Tarttaglia = new javax.swing.JTextField();
        txtx2iTarttaglia = new javax.swing.JTextField();
        txtx3iTarttaglia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelMuller = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtRaizmuller = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtErrormuller = new javax.swing.JTextField();
        panelBairstow = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBairstow = new javax.swing.JTextArea();
        panelSecante = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtRaizsecante = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtErrorsecante = new javax.swing.JTextField();
        panelFerrari = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtFerrari1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFerrari2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFerrari3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtFerrari4 = new javax.swing.JTextField();
        calcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbFuncion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "x^3 + 3x - 1", "x^3 - 4.65x^2 - 49.92x - 76.69", "x^4 + x^3 + 0.56x^2 - 1.44x - 2.88", "x^4 - 3x^2 + 5x + 2", "x^5 - 3x^4 - 23x^3 + 55x^2 + 74x - 120", "x^6 - 7x^4 + x^3 - 1", "ln(1+x) - cos(x)", "10*sin(x) + 3*cos(x)", "e^3(x-1) - ln(x-1)^2 + 1", "cos(0.785 - x*sqrt(1+x^2))" }));
        getContentPane().add(cmbFuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 12, 312, -1));

        jLabel1.setText("Escoja una funcion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 17, -1, -1));

        jLabel2.setText("Intervalo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 59, -1, -1));
        getContentPane().add(txtMenor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 57, 27, -1));
        getContentPane().add(txtMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 57, 27, -1));

        panelHorner.setBackground(new java.awt.Color(199, 221, 244));

        jLabel5.setText("Raiz:");

        jLabel6.setText("Error:");

        javax.swing.GroupLayout panelHornerLayout = new javax.swing.GroupLayout(panelHorner);
        panelHorner.setLayout(panelHornerLayout);
        panelHornerLayout.setHorizontalGroup(
            panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHornerLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtErrorHorner)
                    .addComponent(txtRaizHorner, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(420, Short.MAX_VALUE))
        );
        panelHornerLayout.setVerticalGroup(
            panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHornerLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRaizHorner, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHornerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtErrorHorner, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        tabPanel.addTab("Horner", panelHorner);

        jLabel3.setText("x1 =");

        jLabel4.setText("x2 =");

        jLabel7.setText("x3 =");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("+");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("-");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("i");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("i");

        javax.swing.GroupLayout panelTartagliaLayout = new javax.swing.GroupLayout(panelTartaglia);
        panelTartaglia.setLayout(panelTartagliaLayout);
        panelTartagliaLayout.setHorizontalGroup(
            panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTartagliaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTartagliaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtx2Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelTartagliaLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtx3Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelTartagliaLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtx1Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtx3iTarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtx2iTarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        panelTartagliaLayout.setVerticalGroup(
            panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTartagliaLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtx1Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTartagliaLayout.createSequentialGroup()
                        .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtx2iTarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtx3iTarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(panelTartagliaLayout.createSequentialGroup()
                        .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtx2Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(panelTartagliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtx3Tarttaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(106, 106, 106))
        );

        tabPanel.addTab("Tartaglia", panelTartaglia);

        jLabel18.setText("Raiz");

        jLabel19.setText("Error");

        javax.swing.GroupLayout panelMullerLayout = new javax.swing.GroupLayout(panelMuller);
        panelMuller.setLayout(panelMullerLayout);
        panelMullerLayout.setHorizontalGroup(
            panelMullerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMullerLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(panelMullerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMullerLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtErrormuller))
                    .addGroup(panelMullerLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtRaizmuller, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(477, 477, 477))
        );
        panelMullerLayout.setVerticalGroup(
            panelMullerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMullerLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(panelMullerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtRaizmuller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMullerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtErrormuller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        tabPanel.addTab("Muller", panelMuller);

        txtBairstow.setColumns(20);
        txtBairstow.setRows(5);
        jScrollPane1.setViewportView(txtBairstow);

        javax.swing.GroupLayout panelBairstowLayout = new javax.swing.GroupLayout(panelBairstow);
        panelBairstow.setLayout(panelBairstowLayout);
        panelBairstowLayout.setHorizontalGroup(
            panelBairstowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBairstowLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        panelBairstowLayout.setVerticalGroup(
            panelBairstowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBairstowLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabPanel.addTab("Bairstow", panelBairstow);

        jLabel16.setText("Raiz :");

        jLabel17.setText("Error :");

        javax.swing.GroupLayout panelSecanteLayout = new javax.swing.GroupLayout(panelSecante);
        panelSecante.setLayout(panelSecanteLayout);
        panelSecanteLayout.setHorizontalGroup(
            panelSecanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSecanteLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panelSecanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSecanteLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtErrorsecante, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSecanteLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRaizsecante, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(441, Short.MAX_VALUE))
        );
        panelSecanteLayout.setVerticalGroup(
            panelSecanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSecanteLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelSecanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtRaizsecante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelSecanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtErrorsecante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        tabPanel.addTab("Secante", panelSecante);

        jLabel12.setText("Raiz 1:");

        jLabel13.setText("Raiz 2:");

        jLabel14.setText("Raiz 3:");

        jLabel15.setText("Raiz 4:");

        javax.swing.GroupLayout panelFerrariLayout = new javax.swing.GroupLayout(panelFerrari);
        panelFerrari.setLayout(panelFerrariLayout);
        panelFerrariLayout.setHorizontalGroup(
            panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFerrariLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFerrariLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFerrari2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFerrariLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFerrari1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62)
                .addGroup(panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFerrariLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFerrari4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFerrariLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFerrari3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panelFerrariLayout.setVerticalGroup(
            panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFerrariLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFerrari3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtFerrari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(38, 38, 38)
                .addGroup(panelFerrariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFerrari4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtFerrari2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        tabPanel.addTab("Ferrari", panelFerrari);

        getContentPane().add(tabPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 760, 300));

        calcular.setText("Calcular");
        calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularActionPerformed(evt);
            }
        });
        getContentPane().add(calcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 54, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularActionPerformed
        int funcion=cmbFuncion.getSelectedIndex()+1;
        switch(funcion){
            case 1:
            tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(1, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
            tabPanel.setEnabledAt(5, false);
            tabPanel.setEnabledAt(4, false);
            horner();
            muller();
            tartaglia();
            bairstow();
            
            
            break;    
            case 2:
                tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(1, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(4, false);
                horner();
                muller();
                tartaglia();
                bairstow();
            
            break; 
            case 3:
                tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(5, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
                tabPanel.setEnabledAt(4, false);
                tabPanel.setEnabledAt(1, false);
                horner();
                muller();
                bairstow();
                ferrari();
                
            break; 
            case 4:
                  tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(5, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
                tabPanel.setEnabledAt(4, false);
                tabPanel.setEnabledAt(1, false);
                horner();
                muller();
                bairstow();
                ferrari();
            break; 
            case 5:
                  tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                horner();
                muller();
                bairstow();
                secante();
                
            break; 
            case 6:
                  tabPanel.setEnabledAt(0, true);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(3, true);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                horner();
                muller();
                bairstow();
                secante();
                
            break; 
            case 7:
                 tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(0, false);
            tabPanel.setEnabledAt(3, false);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                muller();
                secante();
            break; 
            case 8:
                 tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(0, false);
            tabPanel.setEnabledAt(3, false);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                muller();
                secante();
            break; 
            case 9:
                 tabPanel.setEnabledAt(2, true);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(0, false);
            tabPanel.setEnabledAt(3, false);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                muller();
                secante();
            break; 
            case 10:
                 tabPanel.setEnabledAt(2, false);
            tabPanel.setEnabledAt(4, true);
            tabPanel.setEnabledAt(0, false);
            tabPanel.setEnabledAt(3, false);
                tabPanel.setEnabledAt(5, false);
                tabPanel.setEnabledAt(1, false);
                muller();
                secante();
            break; 
        }
        
    }//GEN-LAST:event_calcularActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcular;
    private javax.swing.JComboBox<String> cmbFuncion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBairstow;
    private javax.swing.JPanel panelFerrari;
    private javax.swing.JPanel panelHorner;
    private javax.swing.JPanel panelMuller;
    private javax.swing.JPanel panelSecante;
    private javax.swing.JPanel panelTartaglia;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextArea txtBairstow;
    private javax.swing.JTextField txtErrorHorner;
    private javax.swing.JTextField txtErrormuller;
    private javax.swing.JTextField txtErrorsecante;
    private javax.swing.JTextField txtFerrari1;
    private javax.swing.JTextField txtFerrari2;
    private javax.swing.JTextField txtFerrari3;
    private javax.swing.JTextField txtFerrari4;
    private javax.swing.JTextField txtMayor;
    private javax.swing.JTextField txtMenor;
    private javax.swing.JTextField txtRaizHorner;
    private javax.swing.JTextField txtRaizmuller;
    private javax.swing.JTextField txtRaizsecante;
    private javax.swing.JTextField txtx1Tarttaglia;
    private javax.swing.JTextField txtx2Tarttaglia;
    private javax.swing.JTextField txtx2iTarttaglia;
    private javax.swing.JTextField txtx3Tarttaglia;
    private javax.swing.JTextField txtx3iTarttaglia;
    // End of variables declaration//GEN-END:variables
}
