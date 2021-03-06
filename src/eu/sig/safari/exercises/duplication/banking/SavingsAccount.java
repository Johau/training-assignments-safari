/** 
 * NOTICE: 
 * - This file has been modified by the Software Improvement Group (SIG) to adapt it for training purposes
 * - The original file can be found here: https://github.com/oreillymedia/building_maintainable_software
 * 
 */
package eu.sig.safari.exercises.duplication.banking;

import eu.sig.safari.exercises.duplication.banking.stubs.Accounts;
import eu.sig.safari.exercises.duplication.banking.stubs.BusinessException;
import eu.sig.safari.exercises.duplication.banking.stubs.Money;
import eu.sig.safari.exercises.duplication.banking.stubs.Transfer;

public class SavingsAccount {
    CheckingAccount registeredCounterAccount;

    public Transfer makeTransfer(String counterAccount, Money amount) 
        throws BusinessException {
        // 1. Assuming result is 9-digit bank account number, validate 11-test:
        int sum = 0; // <1>
        for (int i = 0; i < counterAccount.length(); i++) {
            sum = sum + (9 - i) * Character.getNumericValue(
                counterAccount.charAt(i));
        }
        if (sum % 11 == 0) {
            // 2. Look up counter account and make transfer object:
            CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
            Transfer result = new Transfer(this, acct, amount); // <2>
            // 3. Check whether withdrawal is to registered counter account:
            if (result.getCounterAccount().equals(this.registeredCounterAccount)) 
            {
                return result;
            } else {
                throw new BusinessException("Counter-account not registered!");
            }
        } else {
            throw new BusinessException("Invalid account number!!");
        }
    }
}