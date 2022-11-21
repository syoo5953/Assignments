package uk.ac.aber.cs21120.wedding.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plan implements uk.ac.aber.cs21120.wedding.interfaces.IPlan {
    private int numberOfTables;
    private int setsPerTable;
    private List<List<String>> plan;

    /**
     * Constructor for objects of class Plan
     */
    public Plan(int numberOfTables, int setsPerTable) {
        this.numberOfTables = numberOfTables;
        this.setsPerTable = setsPerTable;
        plan = new ArrayList<>(numberOfTables);
        for(int i = 0; i < numberOfTables; i++) {
            plan.add(new ArrayList<>(setsPerTable));
        }
    }

    /**
     * return the number of seats per table. All tables have the same number of seats.
     * This doesn't change once the Plan has been created.
     *
     * @return the number of seats, a positive non-zero integer
     */

    @Override
    public int getSeatsPerTable() {
        return setsPerTable;
    }

    /**
     * Return the number of tables.
     * This doesn't change once the Plan has been created.
     *
     * @return the number of tables, a positive non-zero integer
     */
    @Override
    public int getNumberOfTables() {
        return numberOfTables;
    }

    /**
     * Add a guest to a table. If the guest is already seated at any table it will
     * do nothing. If the table is already full (i.e. the number of guests at that table is
     * equal to getSeatsPerTable() it will do nothing. If the table number is less than zero,
     * or greater than getSeatsPerTable(), it will raise IndexOutOfBoundsException.
     *
     * @param table the table number
     * @param guest the name of the guest
     */

    @Override
    public void addGuestToTable(int table, String guest) {
        if (table < 0 || table >= this.numberOfTables) {
            throw new IndexOutOfBoundsException();
        }
        for(List list : this.plan){
            if(list.contains(guest)) return;
        }
        if(this.plan.get(table).size() >= this.getSeatsPerTable()) return;
        this.plan.get(table).add(guest);
    }

    /**
     * Remove a guest from any table they are sitting at.
     * If the guest is not at any table, it will do nothing.
     *
     * @param guest the name of the guest
     */
    @Override
    public void removeGuestFromTable(String guest) {
        for(List list : this.plan) {
            if(list.contains(guest)) {
                list.remove(guest);
            }
        }
    }

    /**
     * Return whether a guest is sitting at any table.
     *
     * @param guest the name of the guest
     * @return true if the guest is at a table, false otherwise
     */
    @Override
    public boolean isGuestPlaced(String guest) {
        for(List list : this.plan) {
            if (list.contains(guest)) return true;
        }
        return false;
    }

    /**
     * Return a set of the guests seated at a particular table. If the
     * table number of out of range it will raise IndexOutOfBoundsException.
     *
     * @param t the table number
     * @return a set of strings - the guests at that table
     */

    @Override
     public Set<String> getGuestsAtTable(int t) {
          if (t < 0 || t >= numberOfTables) {
                throw new IndexOutOfBoundsException();
          }
          return new HashSet<>(this.plan.get(t));
     }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<getNumberOfTables();i++){
            Set<String> t = getGuestsAtTable(i);
            sb.append('(');
            List<String> list = new ArrayList<String>(t);
            sb.append(String.join(",", list));
            sb.append(") ");
        }
        return sb.toString();
    }
}