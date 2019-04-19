/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact;

import javax.ejb.Stateless;

/**
 *
 * @author aaditya
 */
@Stateless
public class FactorialBean implements FactorialBeanLocal {

    @Override
    public Integer factorial(int a) {
        if(a<=1)
        {
            return 1;
        }
        return a*factorial(a-1);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
