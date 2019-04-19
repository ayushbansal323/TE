/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact;

import javax.ejb.Local;

/**
 *
 * @author aaditya
 */
@Local
public interface FactorialBeanLocal {

    Integer factorial(int a);
    
}
