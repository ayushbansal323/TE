/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import javax.ejb.Stateless;

/**
 *
 * @author aaditya
 */
@Stateless
public class calculatorBean implements calculatorBeanLocal {

    @Override
    public Integer add(int a, int b) {
        return (a+b);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Integer sub(int a, int b) {
        return (a-b);
    }
    
     @Override
    public Integer mule(int a, int b) {
        return (a*b);
    }
    
     @Override
    public Integer dive(int a, int b) {
       if(b==0) {
        return 0;}
       return (a/b);
    }
    

    @Override
    public Double sine(double a) {
       double c;
       c= Math.toRadians(a);
       c=Math.sin(c);
        return c;
    }

    @Override
    public Double cose(double a) {
       double c;
       c= Math.toRadians(a);
       c=Math.cos(c);
        return c;
    }

   

   
    
}
