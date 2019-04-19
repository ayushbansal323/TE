/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import javax.ejb.Local;

/**
 *
 * @author aaditya
 */
@Local
public interface calculatorBeanLocal {

    Integer add(int a, int b);

    Integer sub(int a, int b);

    Double sine(double a);

    Double cose(double a);

    Integer mule(int a, int b);

    Integer dive(int a, int b);
    
}
