/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateful;

/**
 *
 * @author aaditya
 */
@Stateful
public class bankbean implements bankbeanLocal {
    int CurrentBalance=10000;
    @Override
    public Integer withdraw(int amount) {
        return CurrentBalance=CurrentBalance-amount;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Integer deposit(int amount) {
        return CurrentBalance=CurrentBalance+amount;
    }
}
