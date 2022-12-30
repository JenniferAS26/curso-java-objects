package model;

import java.util.Date;

public interface ISchedulable {
    // firma del metodo, signature del metodo
    // valor de retorno (void en este caso)
    void schedule(Date date, String time);
}
