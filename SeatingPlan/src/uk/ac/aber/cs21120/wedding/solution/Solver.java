package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Solver implements uk.ac.aber.cs21120.wedding.interfaces.ISolver{
    String[] guests;
    IPlan p;
    IRules r;

    public Solver(String[] guests, IPlan p, IRules r) {
        this.guests = guests;
        this.p = p;
        this.r = r;
    }

    @Override
    public boolean solve() {
        List<String> guest = Arrays.asList(guests);
        for(int i = 0; i < p.getNumberOfTables(); i++) {
            if(!p.isTableFull(i)) {
                for(int j = 0; j < p.getSeatsPerTable(); j++) {
                    for(int k = 0; k < guest.size(); k++) {
                        if(!p.isGuestPlaced(guest.get(k))) {
                            p.addGuestToTable(i, guest.get(k));
                            if(r.isPlanOK(p)) {
                                boolean result = solve();
                                if(result) return true;
                            } else {
                                p.removeGuestFromTable(guest.get(k));
                            }
                        }
                    }

                }
            }
        }
        return true;
    }
}
